package dev.patika.vetsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "available_date")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column(name = "available_date", nullable = false)
    private LocalDate availableDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Override
    public String toString() {
        return "AvailableDate{" +
                "id=" + id +
                ", availableDate=" + availableDate +
                ", doctor=" + doctor +
                '}';
    }
}
