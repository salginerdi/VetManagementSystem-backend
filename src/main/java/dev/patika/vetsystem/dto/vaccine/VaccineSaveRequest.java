package dev.patika.vetsystem.dto.vaccine;

import dev.patika.vetsystem.dto.animal.AnimalUpdateRequest;
import dev.patika.vetsystem.dto.report.ReportUpdateRequest;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {
    @NotNull(message = "Aşı adı alanı boş bırakılamaz!")
    private String name;

    @NotNull(message = "Aşı kodu alanı boş bırakılamaz!")
    private String code;

    @Past
    @NotNull(message = "Aşı koruması başlangıç tarihi alanı boş bırakılamaz!")
    private LocalDate protectionStartDate;

    @FutureOrPresent
    @NotNull(message = "Aşı koruma bitiş tarihi alanı boş bırakılamaz!")
    private LocalDate protectionFinishDate;

    private AnimalUpdateRequest animal;

    private ReportUpdateRequest report;
}
