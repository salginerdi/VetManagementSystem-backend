package dev.patika.vetsystem.dao;

import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Appointment;
import dev.patika.vetsystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    // 24-Randevuların girilen tarih aralığı ve doktora göre filtrelenmesi
    List<Appointment> findByAppointmentDateAndDoctorId(LocalDateTime appointmentDate, Long doctorId);

    // 23-Randevuların girilen tarih aralığı ve hayvana göre filtrelenmesi
    List<Appointment> findByAppointmentDateAndAnimalId(LocalDateTime appointmentDate, Long animalId);

    // 22-Hayvanların her türlü muayenesi için doktorlardan uygun tarihlerde ve saatlerde randevu oluşturulmalıdır.
    // Her doktor için sadece saat başı randevu oluşturulması.
    @Query(value = "SELECT * FROM appointment WHERE appointment_date = ?1 AND doctor_id = ?2", nativeQuery = true)
    Optional<Appointment> checkForConflictingAppointmentHoursByHourAndDoctorId(LocalDateTime appointment, Long doctorId);
}
