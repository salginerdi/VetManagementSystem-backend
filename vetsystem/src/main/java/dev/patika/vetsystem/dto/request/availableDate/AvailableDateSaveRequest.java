package dev.patika.vetsystem.dto.request.availableDate;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {
    @NotNull(message = "Müsait zaman alanı boş bırakılamaz!")
    @Temporal(TemporalType.DATE)
    @FutureOrPresent
    private LocalDate availableDate;
    @NotNull(message = "Randevu zamanı belirlemek için doktor ID'si seçmelisiniz!")
    private Long doctorId;

}
