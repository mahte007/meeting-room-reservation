package com.mate.meeting_room_reservation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateReservationRequest(
        @NotBlank String title,
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime,
        @Min(1) int attendeeCount,
        @NotNull Long userId,
        @NotNull Long roomId,
        @NotNull String status
) {}
