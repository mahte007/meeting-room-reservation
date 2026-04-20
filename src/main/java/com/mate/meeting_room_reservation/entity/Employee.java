package com.mate.meeting_room_reservation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String department;
    private String role;
    private Boolean active;

    @OneToMany(mappedBy = "employee")
    private List<Reservation> reservations = new ArrayList<>();

}
