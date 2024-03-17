package dev.patika.vetsystem.dto.appointment;

import dev.patika.vetsystem.dto.animal.OnlyAnimalResponse;
import dev.patika.vetsystem.dto.doctor.OnlyDoctorResponse;
import dev.patika.vetsystem.dto.report.OnlyReportResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
    private OnlyAnimalResponse animal;
    private OnlyDoctorResponse doctor;
    private OnlyReportResponse report;

}
