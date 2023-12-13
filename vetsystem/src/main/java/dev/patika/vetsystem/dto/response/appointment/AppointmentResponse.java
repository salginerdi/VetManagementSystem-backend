package dev.patika.vetsystem.dto.response.appointment;

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
    private Long animalId;
    private Long doctorId;
}
