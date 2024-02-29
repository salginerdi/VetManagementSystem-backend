package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.dto.PageResponse;
import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.animal.AnimalSaveRequest;
import dev.patika.vetsystem.dto.animal.AnimalUpdateRequest;
import dev.patika.vetsystem.entities.Animal;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IAnimalService {

    Animal getById(Long id);
    AnimalResponse getResponseById(Long id);
    List<AnimalResponse> getPageResponse(int page, int pageSize);
    AnimalResponse create(AnimalSaveRequest animalSaveRequest);
    AnimalResponse update(AnimalUpdateRequest animalUpdateRequest);

    void delete(Long id);

    // 16-Hayvanları isme göre filtrelemek
    List<AnimalResponse> getAnimalsByName(String name);

}
