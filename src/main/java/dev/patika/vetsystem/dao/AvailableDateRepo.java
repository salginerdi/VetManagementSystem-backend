package dev.patika.vetsystem.dao;

import dev.patika.vetsystem.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {

    // 22-Randevu kaydı oluştururken doktorun girilen tarihte müsait günü olup olmadığı
    // eğer ki varsa randevu kayıtlarında girilen saatte başka bir randevusu olup olmadığı kontrol edilir.
    @Query(value = "SELECT * FROM available_date WHERE doctor_id = ?1 AND available_date = ?2", nativeQuery = true)
    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long doctor_id, LocalDate availableDate);
}
