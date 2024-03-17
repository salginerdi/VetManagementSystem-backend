package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IVaccineService;
import dev.patika.vetsystem.core.config.modelMapper.ModelMapperService;
import dev.patika.vetsystem.dao.VaccineRepo;
import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.vaccine.VaccineResponse;
import dev.patika.vetsystem.dto.vaccine.VaccineSaveRequest;
import dev.patika.vetsystem.dto.vaccine.VaccineUpdateRequest;
import dev.patika.vetsystem.entities.Vaccine;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineManager implements IVaccineService {
    private final VaccineRepo vaccineRepo;
    private final ModelMapperService modelMapper;

    @Override
    public Vaccine getById(Long id) {
        return vaccineRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Vaccine not found" + id));
    }

    @Override
    public VaccineResponse getResponseById(Long id) {
        return modelMapper.forResponse().map(getById(id), VaccineResponse.class);
    }

    @Override
    public List<VaccineResponse> getPageResponse(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Vaccine> vaccinePage = vaccineRepo.findAll(pageable);

        return vaccinePage.stream().map(
                        vaccine ->
                                modelMapper
                                        .forResponse()
                                        .map(vaccine, VaccineResponse.class))
                .toList();
    }

    @Override
    public VaccineResponse create(VaccineSaveRequest vaccineSaveRequest) {
        validateVaccine(vaccineSaveRequest);

        Vaccine saveVaccine = this.modelMapper
                .forRequest()
                .map(vaccineSaveRequest, Vaccine.class);

        return modelMapper
                .forResponse()
                .map(vaccineRepo.save(saveVaccine), VaccineResponse.class);
    }

    private void validateVaccine(VaccineSaveRequest vaccineSaveRequest) {
        Optional<Vaccine> checkVaccine = this.vaccineRepo.validateVaccine(
                vaccineSaveRequest.getCode(),
                vaccineSaveRequest.getAnimal().getId(),
                vaccineSaveRequest.getProtectionStartDate()
        );
        if (checkVaccine.isPresent()) {
            throw new RuntimeException("Vaccine is still in effect");
        }
    }

    @Override
    public VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest) {
        Vaccine doesVaccineExist = getById(vaccineUpdateRequest.getId());

        Vaccine updateVaccine =  modelMapper
                .forRequest()
                .map(vaccineUpdateRequest, Vaccine.class);

        modelMapper
                .forRequest()
                .map(updateVaccine, doesVaccineExist);

        return modelMapper
                .forResponse()
                .map(vaccineRepo.save(doesVaccineExist), VaccineResponse.class);
    }

    @Override
    public void delete(Long id) {
        vaccineRepo.delete(getById(id));
    }

    @Override
    public List<VaccineResponse> getVaccinesWithProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate) {
        return vaccineRepo.findByProtectionFinishDateBetween(startDate, endDate)
                .stream().map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .toList();
    }

    @Override
    public AnimalResponse getAnimalResponse(Long id) {
        return modelMapper
                .forResponse()
                .map(getById(id).getAnimal(), AnimalResponse.class);
    }

    @Override
    public List<VaccineResponse> searchByAnimalName(String animalName) {
        return vaccineRepo.findByAnimalName(animalName)
                .stream().map(vaccine -> modelMapper
                        .forResponse()
                        .map(vaccine, VaccineResponse.class))
                .toList();
    }
}
