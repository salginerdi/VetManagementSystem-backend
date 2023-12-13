package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.entities.Animal;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IAnimalService {
    Animal save(Animal animal);
    Animal get(long id);
    Page<Animal> cursor(int page, int pageSize);
    Animal update(Animal animal);
    boolean delete(long id);

    // 16-Hayvanları isme göre filtrelemek
    List<Animal> findAnimalsByName(String name);

}
