package dev.patika.vetsystem.dao;

import dev.patika.vetsystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {

    // 19-Eğer hastaya ait aynı tip aşının koruyuculuk bitiş tarihi daha gelmemiş ise sisteme yeni aşı girilememesi
    @Query(value = "Select * From vaccine Where code = ?1 AND animal_id = ?2 AND protection_finish_date > ?3", nativeQuery = true)
    Optional<Vaccine> validateVaccine(String code, Long animalId, LocalDate protectionStartDate);

    // 21-Kullanıcının aşı koruyuculuk bitiş tarihi yaklaşan hayvanları listeleyebilmesi için kullanıcının gireceği başlangıç ve bitiş tarihlerine göre aşı
    // koruyuculuk tarihi bu aralıkta olan hayvanların listesini geri döndüren API end  point'ini oluşturmak.
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
}
