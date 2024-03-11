package dev.patika.vetsystem.dto.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.patika.vetsystem.dto.animal.AnimalUpdateRequest;
import dev.patika.vetsystem.dto.doctor.DoctorUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    @Positive
    private Long id;

    @NotNull(message = "Randevu zamanı alanı boş bırakılamaz!")
    @FutureOrPresent
    private LocalDateTime appointmentDate;

    private AnimalUpdateRequest animal;

    private DoctorUpdateRequest doctor;
}
