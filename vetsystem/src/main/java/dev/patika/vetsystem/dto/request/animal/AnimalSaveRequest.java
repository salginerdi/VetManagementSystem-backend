package dev.patika.vetsystem.dto.request.animal;

import dev.patika.vetsystem.entities.Customer;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSaveRequest {
    @NotNull(message = "Hayvan ismi alanı boş bırakılamaz!")
    private String name;
    @NotNull(message = "Hayvan türü alanı boş bırakılamaz!")
    private String species;
    @NotNull(message = "Hayvan cinsi alanı boş bırakılamaz!")
    private String breed;
    @NotNull(message = "Hayvan cinsiyeti boş bırakılamaz!")
    private String gender;
    private String colour;
    @Temporal(TemporalType.DATE)
    @Past
    private LocalDate dateOfBirth;
    @NotNull(message = "Hayvan sahibi ID alanı boş bırakılamaz!")
    private Long customerId;
}
