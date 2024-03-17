package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.ICustomerService;
import dev.patika.vetsystem.core.config.modelMapper.ModelMapperService;
import dev.patika.vetsystem.dao.CustomerRepo;
import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.customer.CustomerResponse;
import dev.patika.vetsystem.dto.customer.CustomerSaveRequest;
import dev.patika.vetsystem.dto.customer.CustomerUpdateRequest;
import dev.patika.vetsystem.entities.Customer;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerManager implements ICustomerService {
    private final CustomerRepo customerRepo;
    private final ModelMapperService modelMapper;


    @Override
    public Customer getById(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Customer not found" + id));
    }

    @Override
    public CustomerResponse getResponseById(Long id) {
        return modelMapper.forResponse().map(getById(id), CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> getPageResponse(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Customer> customerPage = customerRepo.findAll(pageable);

        return customerPage.stream().map(
                customer ->
                    modelMapper
                            .forResponse()
                            .map(customer, CustomerResponse.class))
                    .toList();
    }

    @Override
    public CustomerResponse create(CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapper
                .forRequest()
                .map(customerSaveRequest, Customer.class);

        return modelMapper
                .forResponse()
                .map(customerRepo.save(saveCustomer), CustomerResponse.class);
    }

    @Override
    public CustomerResponse update(CustomerUpdateRequest customerUpdateRequest) {
        Customer doesCustomerExist = getById(customerUpdateRequest.getId());

        modelMapper
                .forRequest()
                .map(customerUpdateRequest, doesCustomerExist);

        return modelMapper
                .forResponse()
                .map(customerRepo.save(doesCustomerExist), CustomerResponse.class);
    }

    @Override
    public void delete(Long id) {
        customerRepo.delete(getById(id));
    }

    @Override
    public List<CustomerResponse> searchCustomersByName(String name) {
        return customerRepo.searchCustomersByName(name)
                .stream().map(customer ->
                        modelMapper
                                .forResponse()
                                .map(customer, CustomerResponse.class))
                .toList();
    }

    @Override
    public List<AnimalResponse> getCustomerAnimals(Long id) {
        return getById(id).getAnimals()
                .stream().map(
                        animal -> modelMapper
                                .forResponse()
                                .map(animal, AnimalResponse.class)).toList();
    }
}
