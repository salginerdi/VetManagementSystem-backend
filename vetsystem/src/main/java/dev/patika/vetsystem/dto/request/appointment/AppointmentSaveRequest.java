package dev.patika.vetsystem.dto.request.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {
    @NotNull(message = "Randevu zamanı alanı boş bırakılamaz!")
    @FutureOrPresent
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm")
    private LocalDateTime appointmentDate;
    @NotNull(message = "Randevu işlemleri için ilgili hayvanın ID'sini seçmelisiniz!")
    private Long animalId;
    @NotNull(message = "Randevu işlemleri için doktor ID'si seçmelisiniz!")
    private Long doctorId;
}
