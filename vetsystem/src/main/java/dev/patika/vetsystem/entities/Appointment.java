package dev.patika.vetsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime appointmentDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Animal animal;

    @ManyToOne(fetch = FetchType.EAGER)
    private Doctor doctor;
}
