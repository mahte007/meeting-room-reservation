package com.mate.meeting_room_reservation.dto.room;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveRoomDTO(
        @NotBlank String name,
        @NotNull @Min(1) Integer capacity,
        @NotBlank String location,
        @NotNull Boolean hasProjector
) {}
