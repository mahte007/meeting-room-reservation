package com.mate.meeting_room_reservation.service;

import com.mate.meeting_room_reservation.dto.CreateReservationRequest;
import com.mate.meeting_room_reservation.dto.UpdateReservationRequest;
import com.mate.meeting_room_reservation.entity.Reservation;
import com.mate.meeting_room_reservation.entity.ReservationStatus;
import com.mate.meeting_room_reservation.entity.Room;
import com.mate.meeting_room_reservation.entity.User;
import com.mate.meeting_room_reservation.exception.BadRequestException;
import com.mate.meeting_room_reservation.exception.ResourceNotFoundException;
import com.mate.meeting_room_reservation.repository.ReservationRepository;
import com.mate.meeting_room_reservation.repository.RoomRepository;
import com.mate.meeting_room_reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public Reservation create(CreateReservationRequest request) {
        validateTimeRange(request.startTime(), request.endTime());

        User user = userRepository.findById((request.userId()))
                .orElseThrow(() -> new BadRequestException("User not found."));

        Room room = roomRepository.findById((request.roomId()))
                .orElseThrow(() -> new BadRequestException("Room not found."));

        validateCapacity(request.attendeeCount(), room);
        validateNoOverlapForCreate(room.getId(), request.startTime(), request.endTime());

        Reservation reservation = Reservation.builder()
                .title(request.title())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .attendeeCount(request.attendeeCount())
                .status(ReservationStatus.PENDING)
                .user(user)
                .room(room)
                .build();

        return reservationRepository.save(reservation);
    }

    public Reservation update(Long id, UpdateReservationRequest request) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found."));

        validateTimeRange(request.startTime(), request.endTime());

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new BadRequestException("User does not exist."));

        Room room = roomRepository.findById(request.roomId())
                .orElseThrow(() -> new BadRequestException("Room does not exist."));

        validateCapacity(request.attendeeCount(), room);
        validateNoOverlapForUpdate(room.getId(), request.startTime(), request.endTime(), id);

        ReservationStatus status;
        try {
            status = ReservationStatus.valueOf(request.status().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid reservation status.");
        }

        existingReservation.setTitle(request.title());
        existingReservation.setStartTime(request.startTime());
        existingReservation.setEndTime(request.endTime());
        existingReservation.setAttendeeCount(request.attendeeCount());
        existingReservation.setStatus(status);
        existingReservation.setUser(user);
        existingReservation.setRoom(room);

        return reservationRepository.save(existingReservation);
    }

    public void delete(Long id) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found."));

        reservationRepository.delete(existingReservation);
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getByRoom(Long roomId) {
        return reservationRepository.findByRoomId(roomId);
    }

    public List<Reservation> getByUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    private void validateTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        if (!startTime.isBefore(endTime)) {
            throw new BadRequestException("Start time must be before end time");
        }
    }

    private void validateCapacity(int attendeeCount, Room room) {
        if (attendeeCount > room.getCapacity()) {
            throw new BadRequestException("Attendee count exceeds room capacity");
        }
    }

    private void validateNoOverlapForCreate(Long roomId, LocalDateTime startTime, LocalDateTime endTime) {
        boolean hasOverlap = !reservationRepository
                .findByRoomIdAndStartTimeAndEndTime(roomId, startTime, endTime)
                .isEmpty();

        if (hasOverlap) {
            throw new BadRequestException("Room is already reserved for this time range.");
        }
    }

    private void validateNoOverlapForUpdate(Long roomId, LocalDateTime startTime, LocalDateTime endTime, Long reservationId) {
        boolean hasOverlap = !reservationRepository
                .findByRoomIdAndStartTimeAndEndTimeAndIdNot(
                        roomId,
                        startTime,
                        endTime,
                        reservationId
                )
                .isEmpty();

        if (hasOverlap) {
            throw new BadRequestException("Room is already reserved in this time range.");
        }
    }

}
