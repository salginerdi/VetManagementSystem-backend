package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Appointment;
import dev.patika.vetsystem.entities.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    Appointment save(Appointment appointment);

    Appointment get(long id);

    Page<Appointment> cursor(int page, int pageSize);

    Appointment update(Appointment appointment);

    boolean delete(long id);

    // 24-Doktor ve tarih aralığına göre filtreleme
    List<Appointment> findByAppointmentDateAndDoctor(LocalDateTime appointmentDate, Doctor doctor);

    // 23-Hayvan ve tarih aralığına göre filtreleme
    List<Appointment> findByAppointmentDateAndAnimal(LocalDateTime appointmentDate, Animal animal);





}
