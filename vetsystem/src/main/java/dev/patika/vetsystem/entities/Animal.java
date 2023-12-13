package dev.patika.vetsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "species", nullable = false)
    private String species;

    @Column(name = "breed", nullable = false)
    private String breed;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "colour", nullable = false)
    private String colour;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "animal", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Vaccine> vaccineList;

    @OneToMany(mappedBy = "animal", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_customer_id", referencedColumnName = "id")
    private Customer customer;

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", colour='" + colour + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                //", vaccineList=" + vaccineList +
                //", appointmentList=" + appointmentList +
                ", customer=" + customer +
                '}';
    }
}
