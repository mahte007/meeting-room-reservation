package com.mate.meeting_room_reservation.service;


import com.mate.meeting_room_reservation.entity.User;
import com.mate.meeting_room_reservation.exception.BadRequestException;
import com.mate.meeting_room_reservation.exception.ResourceNotFoundException;
import com.mate.meeting_room_reservation.repository.ReservationRepository;
import com.mate.meeting_room_reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists.");
        }
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    public User update(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        if (!existingUser.getEmail().equals(updatedUser.getEmail())
                && userRepository.existsByEmail(updatedUser.getEmail())) {
            throw new BadRequestException("Email already exists.");
        }

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        if (reservationRepository.existsByUserId(id)) {
            throw new BadRequestException("User cannot be deleted because it has reservations");
        }

        userRepository.delete(existingUser);
    }

}
