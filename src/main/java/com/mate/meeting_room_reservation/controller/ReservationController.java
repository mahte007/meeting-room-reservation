package com.mate.meeting_room_reservation.controller;

import com.mate.meeting_room_reservation.dto.CreateReservationRequest;
import com.mate.meeting_room_reservation.dto.UpdateReservationRequest;
import com.mate.meeting_room_reservation.entity.Reservation;
import com.mate.meeting_room_reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public Reservation create(@Valid @RequestBody CreateReservationRequest request) throws BadRequestException {
        return reservationService.create(request);
    }

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @GetMapping("/room/{roomId}")
    public List<Reservation> getByRoom(@PathVariable Long roomId) {
        return reservationService.getByRoom(roomId);
    }

    @GetMapping("/user/{userId}")
    public List<Reservation> getByUser(@PathVariable Long userId) {
        return reservationService.getByUser(userId);
    }

    @PutMapping("/{id}")
    public Reservation update(@PathVariable Long id, @Valid @RequestBody UpdateReservationRequest request) {
        return reservationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reservationService.delete(id);
    }
}
