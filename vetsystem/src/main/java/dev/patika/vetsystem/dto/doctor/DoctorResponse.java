package dev.patika.vetsystem.dto.doctor;

import dev.patika.vetsystem.dto.appointment.OnlyAppointmentResponse;
import dev.patika.vetsystem.dto.availabledate.OnlyAvailableDateResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private Long id;
    private String name;
    private  String phone;
    private String mail;
    private String address;
    private String city;
    private List<OnlyAvailableDateResponse> availableDates;
    private List<OnlyAppointmentResponse> appointments;
}
