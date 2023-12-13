package dev.patika.vetsystem.dao;

import dev.patika.vetsystem.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
    // 18-Müşteriye ait tüm hayvanları getirmek için.
    List<Animal> findAllByCustomerId(long customerId);

    // 16-Hayvanları isme göre filtrelemek
    @Query(value = "Select * From Animal Where Name ILIKE %?1%", nativeQuery = true)
    List<Animal> findAnimalsByName(String name);

}
