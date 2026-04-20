package com.mate.meeting_room_reservation.repository;

import com.mate.meeting_room_reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRoomId(Long roomId);

    List<Reservation> findByUserId(Long userId);

    List<Reservation> findByRoomIdAndStartTimeAndEndTime(
            Long roomId,
            LocalDateTime endTime,
            LocalDateTime startTime
    );

    List<Reservation> findByRoomIdAndStartTimeAndEndTimeAndIdNot(
            Long roomId,
            LocalDateTime endTime,
            LocalDateTime startTime,
            Long id
    );

    boolean existsByUserId(Long userId);

    boolean existsByRoomId(Long roomId);

}
