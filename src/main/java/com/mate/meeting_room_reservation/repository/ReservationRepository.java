package com.mate.meeting_room_reservation.repository;

import com.mate.meeting_room_reservation.entity.Reservation;
import com.mate.meeting_room_reservation.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByArchivedFalse();

    List<Reservation> findByRoomIdAndArchivedFalse(Long roomId);

    List<Reservation> findByEmployeeIdAndArchivedFalse(Long employeeId);

    boolean existsByEmployeeIdAndArchivedFalse(Long employeeId);

    boolean existsByRoomIdAndArchivedFalse(Long roomId);

    List<Reservation> findByRoomIdAndArchivedFalseAndStatusNotAndStartTimeLessThanAndEndTimeGreaterThan(
            Long roomId,
            ReservationStatus status,
            LocalDateTime endTime,
            LocalDateTime startTime
    );

    List<Reservation> findByRoomIdAndArchivedFalseAndStatusNotAndStartTimeLessThanAndEndTimeGreaterThanAndIdNot(
            Long RoomId,
            ReservationStatus status,
            LocalDateTime endTime,
            LocalDateTime startTime,
            Long id
    );
}
