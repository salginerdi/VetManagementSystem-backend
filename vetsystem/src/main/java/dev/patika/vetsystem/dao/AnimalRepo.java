package dev.patika.vetsystem.dao;

import dev.patika.vetsystem.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
    // 18-Müşteriye ait tüm hayvanları getirmek için.
    List<Animal> findAllByCustomerId(Long customerId);

    // 16-Hayvanları isme göre filtrelemek

    Optional<Animal> findByNameIgnoreCaseContaining(String name);

}
