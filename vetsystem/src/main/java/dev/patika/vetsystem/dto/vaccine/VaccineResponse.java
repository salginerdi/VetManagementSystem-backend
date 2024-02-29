package dev.patika.vetsystem.dto.vaccine;

import dev.patika.vetsystem.dto.animal.OnlyAnimalResponse;
import dev.patika.vetsystem.dto.doctor.OnlyDoctorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineResponse {
    private Long id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private OnlyAnimalResponse animal;
}
