package dev.patika.vetsystem.api;

import dev.patika.vetsystem.business.abstracts.IAnimalService;
import dev.patika.vetsystem.business.abstracts.ICustomerService;
import dev.patika.vetsystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetsystem.core.result.Result;
import dev.patika.vetsystem.core.result.ResultData;
import dev.patika.vetsystem.core.utilies.ResultHelper;
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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final IAnimalService animalService;
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public AnimalController(IAnimalService animalService,
                            ICustomerService customerService,
                            IModelMapperService modelMapper) {
        this.animalService = animalService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);

        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());
        saveAnimal.setCustomer(customer);

        this.animalService.save(saveAnimal);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        this.animalService.update(updateAnimal);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") long id) {
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    // 20-Hayvanlara ait tüm aşıları listelemek
    @GetMapping("/{id}/vaccine")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccine(@PathVariable("id") long id) {
        Animal animal = this.animalService.get(id);
        List<Vaccine> vaccines = animal.getVaccineList();
        List<VaccineResponse> vaccineResponses = vaccines.stream().map(vaccine -> this.modelMapper.forResponse()
                .map(vaccine, VaccineResponse.class)).toList();
        return ResultHelper.success(vaccineResponses);
    }

    // 16-Hayvanları isme göre filtrelemek
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> searchAnimalsByName(@RequestParam(name = "name") String name) {
        List<Animal> searchAnimals = this.animalService.findAnimalsByName(name);
        List<AnimalResponse> animalResponse = searchAnimals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(animalResponse);
    }


    @GetMapping("/{id}/customer")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getCustomer(@PathVariable("id") long id) {
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal.getCustomer(), CustomerResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PageResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        this.animalService.delete(id);
        return ResultHelper.ok();
    }


}
