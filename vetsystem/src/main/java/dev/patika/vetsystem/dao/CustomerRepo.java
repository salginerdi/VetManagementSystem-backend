package dev.patika.vetsystem.dao;

import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    // 17-Müşterileri isme göre filtrelemek
    @Query(value = "Select * From Customer Where Name ILIKE %?1%", nativeQuery = true)
    List<Customer> findCustomersByName(String name);

}
