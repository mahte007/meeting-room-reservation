package com.mate.meeting_room_reservation.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    @Min(1)
    private int capacity;

    @NotBlank
    private String location;

    private boolean hasProjector;

}
