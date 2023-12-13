package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {
    Customer save(Customer customer);

    Customer get(long id);

    Page<Customer> cursor(int page, int pageSize);

    Customer update(Customer customer);

    boolean delete(long id);

    // 17-Müşterileri isme göre filtrelemek
    List<Customer> findCustomersByName(String name);
}
