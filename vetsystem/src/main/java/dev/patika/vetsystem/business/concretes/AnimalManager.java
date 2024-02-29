package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IAnimalService;
import dev.patika.vetsystem.core.config.modelMapper.ModelMapperService;
import dev.patika.vetsystem.dao.AnimalRepo;
import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.animal.AnimalSaveRequest;
import dev.patika.vetsystem.dto.animal.AnimalUpdateRequest;
import dev.patika.vetsystem.entities.Animal;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalManager implements IAnimalService {
    private final AnimalRepo animalRepo;
    private final ModelMapperService modelMapper;

    @Override
    public Animal getById(Long id) {
        return animalRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Animal with ID " + id + " not found"));
    }

    @Override
    public AnimalResponse getResponseById(Long id) {
        return modelMapper
                .forResponse()
                .map(getById(id), AnimalResponse.class);
    }

    @Override
    public List<AnimalResponse> getPageResponse(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Animal> animalPage = animalRepo.findAll(pageable);

        return animalPage.stream().map(animal ->
                        modelMapper
                        .forResponse()
                        .map(animal, AnimalResponse.class))
                .toList();
    }

    @Override
    public AnimalResponse create(AnimalSaveRequest animalSaveRequest) {
        Animal saveAnimal = this.modelMapper
                .forRequest()
                .map(animalSaveRequest, Animal.class);

        return modelMapper
                .forResponse()
                .map(animalRepo.save(saveAnimal), AnimalResponse.class);
    }

    @Override
    public AnimalResponse update(AnimalUpdateRequest animalUpdateRequest) {
        Animal doesAnimalExist = getById(animalUpdateRequest.getId());

        modelMapper
                .forRequest()
                .map(animalUpdateRequest, doesAnimalExist);

        return modelMapper
                .forResponse()
                .map(animalRepo.save(doesAnimalExist), AnimalResponse.class);
    }

    @Override
    public void delete(Long id) {
        animalRepo.delete(getById(id));
    }

    @Override
    public List<AnimalResponse> getAnimalsByName(String name) {
        return animalRepo.findByName(name)
                .stream().map(animal ->
                        modelMapper
                        .forResponse()
                        .map(animal, AnimalResponse.class))
                .toList();
    }
}
