package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.animal.AnimalSaveRequest;
import dev.patika.vetsystem.dto.animal.AnimalUpdateRequest;
import dev.patika.vetsystem.dto.customer.CustomerResponse;
import dev.patika.vetsystem.dto.customer.CustomerSaveRequest;
import dev.patika.vetsystem.dto.customer.CustomerUpdateRequest;
import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {
    Customer getById(Long id);
    CustomerResponse getResponseById(Long id);
    List<CustomerResponse> getPageResponse(int page, int pageSize);
    CustomerResponse create(CustomerSaveRequest customerSaveRequest);
    CustomerResponse update(CustomerUpdateRequest customerUpdateRequest);

    void delete(Long id);

    List<CustomerResponse> searchCustomersByName(String name);

    List<AnimalResponse> getCustomerAnimals(Long id);
}
