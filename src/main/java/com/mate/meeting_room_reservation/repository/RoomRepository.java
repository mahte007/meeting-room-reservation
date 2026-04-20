package com.mate.meeting_room_reservation.repository;

import com.mate.meeting_room_reservation.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
