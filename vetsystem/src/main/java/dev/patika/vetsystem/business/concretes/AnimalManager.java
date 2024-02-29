package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IAnimalService;
import dev.patika.vetsystem.core.config.modelMapper.ModelMapperService;
import dev.patika.vetsystem.dao.AnimalRepo;
import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.animal.AnimalSaveRequest;
import dev.patika.vetsystem.dto.animal.AnimalUpdateRequest;
import dev.patika.vetsystem.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalManager implements IAnimalService {
    private final AnimalRepo animalRepo;

    public AnimalManager(AnimalRepo animalRepo) {
        this.animalRepo = animalRepo;
    }

    @Override
    public Animal save(Animal animal) {
        return this.animalRepo.save(animal);
    }

    @Override
    public Animal get(long id) {
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.animalRepo.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        this.get(animal.getId());
        return this.animalRepo.save(animal);
    }


    @Override
    public boolean delete(long id) {
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }

    // 16-Hayvanları isme göre filtrelemek
    @Override
    public List<Animal> findAnimalsByName(String name) {
        return animalRepo.findAnimalsByName(name);
    }

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
