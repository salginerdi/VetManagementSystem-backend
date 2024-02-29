package dev.patika.vetsystem.api;

import com.sun.net.httpserver.HttpsServer;
import dev.patika.vetsystem.business.abstracts.IAnimalService;
import dev.patika.vetsystem.business.abstracts.ICustomerService;
import dev.patika.vetsystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetsystem.dto.animal.AnimalSaveRequest;
import dev.patika.vetsystem.dto.animal.AnimalUpdateRequest;
import dev.patika.vetsystem.dto.PageResponse;
import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.customer.CustomerResponse;
import dev.patika.vetsystem.dto.vaccine.VaccineResponse;
import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Customer;
import dev.patika.vetsystem.entities.Vaccine;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final IAnimalService animalService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        return new ResponseEntity<>(animalService.create(animalSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        return new ResponseEntity<>(animalService.update(animalUpdateRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(animalService.getById(id), HttpStatus.OK);
    }

    // 16-Hayvanları isme göre filtrelemek
    @GetMapping("/search-by-name")
    public ResponseEntity<?> searchAnimalsByName(@RequestParam(name = "name") String name) {
        return new ResponseEntity<>(animalService.getAnimalsByName(name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getPageResponse(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return new ResponseEntity<>(animalService.getPageResponse(page, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        animalService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
