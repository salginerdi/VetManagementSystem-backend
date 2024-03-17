package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.dto.appointment.AppointmentResponse;
import dev.patika.vetsystem.dto.appointment.AppointmentSaveRequest;
import dev.patika.vetsystem.dto.appointment.AppointmentUpdateRequest;
import dev.patika.vetsystem.dto.customer.CustomerResponse;
import dev.patika.vetsystem.dto.customer.CustomerSaveRequest;
import dev.patika.vetsystem.dto.customer.CustomerUpdateRequest;
import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Appointment;
import dev.patika.vetsystem.entities.Customer;
import dev.patika.vetsystem.entities.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    Appointment getById(Long id);
    AppointmentResponse getResponseById(Long id);
    List<AppointmentResponse> getPageResponse(int page, int pageSize);
    AppointmentResponse create(AppointmentSaveRequest appointmentSaveRequest);
    AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest);

    void delete(Long id);

    // 24-Doktor ve tarih aralığına göre filtreleme
    List<AppointmentResponse> findByAppointmentDateAndDoctorName(LocalDate startDate, LocalDate endDate, String doctorName);

    // 23-Hayvan ve tarih aralığına göre filtreleme
    List<AppointmentResponse> findByAppointmentDateAndAnimalName(LocalDate startDate, LocalDate endDate, String animalName);





}
