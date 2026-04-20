package com.mate.meeting_room_reservation.repository;

import com.mate.meeting_room_reservation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
