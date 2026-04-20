package com.mate.meeting_room_reservation.mapper;

import com.mate.meeting_room_reservation.dto.room.RoomDTO;
import com.mate.meeting_room_reservation.dto.room.SaveRoomDTO;
import com.mate.meeting_room_reservation.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomDTO toDto(Room room);

    Room toEntity(SaveRoomDTO dto);

    void updateEntityFromDto(SaveRoomDTO dto, @MappingTarget Room room);
}
