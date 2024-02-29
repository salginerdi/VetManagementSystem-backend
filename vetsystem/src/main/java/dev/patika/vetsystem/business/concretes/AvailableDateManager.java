package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IAvailableDateService;
import dev.patika.vetsystem.core.config.modelMapper.ModelMapperService;
import dev.patika.vetsystem.dao.AvailableDateRepo;
import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.availabledate.AvailableDateResponse;
import dev.patika.vetsystem.dto.availabledate.AvailableDateSaveRequest;
import dev.patika.vetsystem.dto.availabledate.AvailableDateUpdateRequest;
import dev.patika.vetsystem.dto.customer.CustomerResponse;
import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.AvailableDate;
import dev.patika.vetsystem.entities.Customer;
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
public class AvailableDateManager implements IAvailableDateService {
    private final AvailableDateRepo availableDateRepo;
    private final ModelMapperService modelMapper;

    @Override
    public AvailableDate getById(Long id) {
        return availableDateRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Available Date " + id + " not found"));
    }

    @Override
    public AvailableDateResponse getResponseById(Long id) {
        return modelMapper
                .forResponse()
                .map(getById(id), AvailableDateResponse.class);
    }

    @Override
    public List<AvailableDateResponse> getPageResponse(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<AvailableDate> availableDatePage = availableDateRepo.findAll(pageable);

        return availableDatePage.stream().map(availableDate ->
                        modelMapper
                                .forResponse()
                                .map(availableDate, AvailableDateResponse.class))
                .toList();
    }

    @Override
    public AvailableDateResponse create(AvailableDateSaveRequest availableDateSaveRequest) {
        AvailableDate saveAvailableDate = this.modelMapper
                .forRequest()
                .map(availableDateSaveRequest, AvailableDate.class);

        return modelMapper
                .forResponse()
                .map(availableDateRepo.save(saveAvailableDate), AvailableDateResponse.class);
    }

    @Override
    public AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        AvailableDate doesAvailableDateExist = getById(availableDateUpdateRequest.getId());

        modelMapper
                .forRequest()
                .map(availableDateUpdateRequest, doesAvailableDateExist);

        return modelMapper
                .forResponse()
                .map(availableDateRepo.save(doesAvailableDateExist), AvailableDateResponse.class);
    }

    @Override
    public void delete(Long id) {
        availableDateRepo.delete(getById(id));
    }

    @Override
    public Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate requestedDate) {
        return availableDateRepo.findByDoctorIdAndAvailableDate(doctorId, requestedDate);
    }
}
