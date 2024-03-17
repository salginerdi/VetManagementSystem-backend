package dev.patika.vetsystem.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OnlyAppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
}
