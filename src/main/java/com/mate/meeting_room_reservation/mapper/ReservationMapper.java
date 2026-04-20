package com.mate.meeting_room_reservation.mapper;

import com.mate.meeting_room_reservation.dto.reservation.ReservationDTO;
import com.mate.meeting_room_reservation.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "status", source = "status")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "employeeName", source = "employee.name")
    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "roomName", source = "room.name")
    ReservationDTO toDto(Reservation reservation);
}
