package com.mate.meeting_room_reservation.dto.employee;

public record EmployeeDTO(
        Long id,
        String name,
        String email,
        String department,
        String role,
        Boolean active
) {}
