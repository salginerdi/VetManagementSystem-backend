package dev.patika.vetsystem.dto.report;

public class ReportResponse {
    private Long id;
    private String title;
    private String diagnosis;
    private double price;
    private OnlyAppointmentResponse appointment;
    private List<OnlyVaccineResponse> vaccines;
}
