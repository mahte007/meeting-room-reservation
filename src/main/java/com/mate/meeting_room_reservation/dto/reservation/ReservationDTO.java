package com.mate.meeting_room_reservation.dto.reservation;

import java.time.LocalDateTime;

public record ReservationDTO(
        Long id,
        String title,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Integer attendeeCount,
        String status,
        Boolean archived,
        Long employeeId,
        String employeeName,
        Long roomId,
        String roomName
) {}
