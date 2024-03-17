package dev.patika.vetsystem.dao;

import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Appointment;
import dev.patika.vetsystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    // 24-Randevuların girilen tarih aralığı ve doktora göre filtrelenmesi
    @Query(value = " Select appointment.* FROM appointment INNER JOIN doctor ON appointment.doctor_id = doctor.id" +
            " WHERE appointment.appointment_date BETWEEN ?1 AND ?2 AND doctor.name ILIKE %?3% " , nativeQuery = true)
    List<Appointment> findByAppointmentDateAndDoctorName(LocalDate startDate, LocalDate endDate, String doctorName);

    // 23-Randevuların girilen tarih aralığı ve hayvana göre filtrelenmesi
    @Query(value = "Select appointment.* FROM appointment INNER JOIN animal ON appointment.animal_id = animal.id " +
            " WHERE appointment.appointment_date BETWEEN ?1 AND ?2 AND animal.name ILIKE %?3% " , nativeQuery = true)
    List<Appointment> findByAppointmentDateAndAnimalName(LocalDate startDate, LocalDate endDate, String animalName);

    // 22-Hayvanların her türlü muayenesi için doktorlardan uygun tarihlerde ve saatlerde randevu oluşturulmalıdır.
    // Her doktor için sadece saat başı randevu oluşturulması.
    @Query(value = "SELECT * FROM appointment WHERE appointment_date = ?1 AND doctor_id = ?2", nativeQuery = true)
    Optional<Appointment> checkForConflictingAppointmentHoursByHourAndDoctorId(LocalDateTime appointment, Long doctorId);
}
