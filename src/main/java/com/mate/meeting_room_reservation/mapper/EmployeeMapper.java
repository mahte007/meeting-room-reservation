package com.mate.meeting_room_reservation.mapper;

import com.mate.meeting_room_reservation.dto.employee.EmployeeDTO;
import com.mate.meeting_room_reservation.dto.employee.SaveEmployeeDTO;
import com.mate.meeting_room_reservation.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toDto(Employee employee);

    Employee toEntity(SaveEmployeeDTO dto);

    void updateEntityFromDto(SaveEmployeeDTO fto, @MappingTarget Employee employee);

}
