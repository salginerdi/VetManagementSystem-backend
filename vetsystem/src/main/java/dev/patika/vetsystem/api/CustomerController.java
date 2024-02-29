package dev.patika.vetsystem.api;

import dev.patika.vetsystem.business.abstracts.IAnimalService;
import dev.patika.vetsystem.business.abstracts.IAvailableDateService;
import dev.patika.vetsystem.business.abstracts.ICustomerService;
import dev.patika.vetsystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetsystem.dto.customer.CustomerSaveRequest;
import dev.patika.vetsystem.dto.customer.CustomerUpdateRequest;
import dev.patika.vetsystem.dto.PageResponse;
import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.customer.CustomerResponse;
import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;


    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        return new ResponseEntity<>(customerService.create(customerSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        return new ResponseEntity<>(customerService.update(customerUpdateRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.getResponseById(id),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getPageResponse(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return new ResponseEntity<>(customerService.getPageResponse(page, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 18-Müşteriye ait tüm hayvanları listeler
    @GetMapping("/customer-animals/{id}")
    public ResponseEntity<?> getCustomerAnimals(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.getCustomerAnimals(id), HttpStatus.OK);
    }

    // 17-Müşterileri isme göre filtrelemek
    @GetMapping("/search-by-name")
    public ResponseEntity<?> searchCustomersByName(@RequestParam(name = "name") String name) {
        return new ResponseEntity<>(customerService.searchCustomersByName(name), HttpStatus.OK);
    }


}

