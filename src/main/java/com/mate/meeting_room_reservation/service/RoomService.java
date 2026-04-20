package com.mate.meeting_room_reservation.service;

import com.mate.meeting_room_reservation.entity.Room;
import com.mate.meeting_room_reservation.exception.BadRequestException;
import com.mate.meeting_room_reservation.exception.ResourceNotFoundException;
import com.mate.meeting_room_reservation.repository.ReservationRepository;
import com.mate.meeting_room_reservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public Room create(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Room getById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found."));
    }

    public Room update(Long id, Room updatedRoom) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found."));

        existingRoom.setName(updatedRoom.getName());
        existingRoom.setCapacity(updatedRoom.getCapacity());
        existingRoom.setLocation(updatedRoom.getLocation());
        existingRoom.setHasProjector(updatedRoom.isHasProjector());

        return roomRepository.save(existingRoom);
    }

    public void delete(Long id) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found."));

        if (reservationRepository.existsByRoomId(id)) {
            throw new BadRequestException("Room cannot be deleted because it has reservations.");
        }

        roomRepository.delete(existingRoom);
    }

}
