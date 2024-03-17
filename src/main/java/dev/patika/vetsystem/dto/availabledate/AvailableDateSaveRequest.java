package dev.patika.vetsystem.dto.availabledate;

import dev.patika.vetsystem.dto.doctor.DoctorUpdateRequest;
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
    @FutureOrPresent
    private LocalDate availableDate;

    private DoctorUpdateRequest doctor;
}
