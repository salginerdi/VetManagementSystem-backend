package dev.patika.vetsystem.dto.response.animal;

import dev.patika.vetsystem.dto.response.vaccine.VaccineResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private Long customerId;
    // private List<VaccineResponse> vaccines;
}
