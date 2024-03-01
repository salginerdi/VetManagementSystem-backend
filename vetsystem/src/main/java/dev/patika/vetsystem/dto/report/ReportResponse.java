package dev.patika.vetsystem.dto.report;

import dev.patika.vetsystem.dto.appointment.OnlyAppointmentResponse;
import dev.patika.vetsystem.dto.vaccine.OnlyVaccineResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private Long id;
    private String title;
    private String diagnosis;
    private double price;
    private OnlyAppointmentResponse appointment;
    private List<OnlyVaccineResponse> vaccines;
}
