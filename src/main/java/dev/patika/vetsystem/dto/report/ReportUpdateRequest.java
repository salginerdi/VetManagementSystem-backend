package dev.patika.vetsystem.dto.report;

import dev.patika.vetsystem.dto.appointment.AppointmentUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportUpdateRequest {
    private Long id;
    private String title;
    private String diagnosis;
    private double price;

    private AppointmentUpdateRequest appointment;
}
