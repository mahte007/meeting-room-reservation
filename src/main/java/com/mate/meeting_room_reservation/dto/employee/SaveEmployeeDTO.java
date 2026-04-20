package com.mate.meeting_room_reservation.dto.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SaveEmployeeDTO(
        @NotBlank String name,
        @Email @NotBlank String email,
        @NotBlank String department,
        @NotBlank String role
) {}
