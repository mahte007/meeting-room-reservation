package com.mate.meeting_room_reservation.dto.room;

public record RoomDTO(
        Long id,
        String name,
        Integer capacity,
        String location,
        Boolean hasProjector,
        Boolean active
) {}
