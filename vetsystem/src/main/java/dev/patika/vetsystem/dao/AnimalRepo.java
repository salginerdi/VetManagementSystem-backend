package dev.patika.vetsystem.dao;

import dev.patika.vetsystem.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
    // 18-Müşteriye ait tüm hayvanları getirmek için.
    @Query(value = "SELECT animal.*" +
            " FROM animal" +
            " INNER JOIN customer ON animal.customer_id = customer.id" +
            " WHERE customer.name ILIKE %?1%", nativeQuery = true)
    List<Animal> findAllByCustomerName(String customerName);

    // 16-Hayvanları isme göre filtrelemek

    List<Animal> findByNameIgnoreCaseContaining(String name);

}
