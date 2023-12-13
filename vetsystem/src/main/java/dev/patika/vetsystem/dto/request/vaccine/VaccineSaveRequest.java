package dev.patika.vetsystem.dto.request.vaccine;

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
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Aşı koruması başlangıç tarihi alanı boş bırakılamaz!")
    private LocalDate protectionStartDate;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Aşı koruma bitiş tarihi alanı boş bırakılamaz!")
    private LocalDate protectionFinishDate;
    @NotNull(message = "Aşı yapılacak hayvan ID alanı boş bırakılamaz!")
    private Long animalId;
}
