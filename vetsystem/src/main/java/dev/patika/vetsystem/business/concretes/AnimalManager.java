package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IAnimalService;
import dev.patika.vetsystem.core.exception.NotFoundException;
import dev.patika.vetsystem.core.utilies.Msg;
import dev.patika.vetsystem.dao.AnimalRepo;
import dev.patika.vetsystem.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

}
