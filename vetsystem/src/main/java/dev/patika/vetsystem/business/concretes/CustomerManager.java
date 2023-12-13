package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.ICustomerService;
import dev.patika.vetsystem.core.exception.NotFoundException;
import dev.patika.vetsystem.core.utilies.Msg;
import dev.patika.vetsystem.dao.AnimalRepo;
import dev.patika.vetsystem.dao.CustomerRepo;
import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;
    private final AnimalRepo animalRepo;

    public CustomerManager(CustomerRepo customerRepo, AnimalRepo animalRepo) {
        this.customerRepo = customerRepo;
        this.animalRepo = animalRepo;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer get(long id) {
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.customerRepo.findAll(pageable);
    }

    @Override
    public Customer update(Customer customer) {
        this.get(customer.getId());
        return this.customerRepo.save(customer);
    }



    @Override
    public boolean delete(long id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        return customerRepo.findCustomersByName(name);
    }

}
