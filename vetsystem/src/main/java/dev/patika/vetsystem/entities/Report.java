package dev.patika.vetsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String diagnosis;
    private double price;

    @OneToOne(fetch = FetchType.EAGER)
    private Appointment appointment;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "report", cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccines;
}
