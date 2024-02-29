package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IDoctorService;
import dev.patika.vetsystem.business.abstracts.IDoctorService;
import dev.patika.vetsystem.core.config.modelMapper.ModelMapperService;
import dev.patika.vetsystem.dao.DoctorRepo;
import dev.patika.vetsystem.dao.DoctorRepo;
import dev.patika.vetsystem.dto.doctor.DoctorResponse;
import dev.patika.vetsystem.dto.doctor.DoctorSaveRequest;
import dev.patika.vetsystem.dto.doctor.DoctorUpdateRequest;
import dev.patika.vetsystem.dto.doctor.DoctorResponse;
import dev.patika.vetsystem.dto.doctor.DoctorSaveRequest;
import dev.patika.vetsystem.dto.doctor.DoctorUpdateRequest;
import dev.patika.vetsystem.entities.Doctor;
import dev.patika.vetsystem.entities.Doctor;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorManager implements IDoctorService {
    private final DoctorRepo doctorRepo;
    private final ModelMapperService modelMapper;


    @Override
    public Doctor getById(Long id) {
        return doctorRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Doctor not found" + id));
    }

    @Override
    public DoctorResponse getResponseById(Long id) {
        return modelMapper.forResponse().map(getById(id), DoctorResponse.class);
    }

    @Override
    public List<DoctorResponse> getPageResponse(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Doctor> doctorPage = doctorRepo.findAll(pageable);

        return doctorPage.stream().map(
                        doctor ->
                                modelMapper
                                        .forResponse()
                                        .map(doctor, DoctorResponse.class))
                .toList();
    }

    @Override
    public DoctorResponse create(DoctorSaveRequest doctorSaveRequest) {
        Doctor saveDoctor = this.modelMapper
                .forRequest()
                .map(doctorSaveRequest, Doctor.class);

        return modelMapper
                .forResponse()
                .map(doctorRepo.save(saveDoctor), DoctorResponse.class);
    }

    @Override
    public DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest) {
        Doctor doesDoctorExist = getById(doctorUpdateRequest.getId());

        modelMapper
                .forRequest()
                .map(doctorUpdateRequest, doesDoctorExist);

        return modelMapper
                .forResponse()
                .map(doctorRepo.save(doesDoctorExist), DoctorResponse.class);
    }

    @Override
    public void delete(Long id) {
        doctorRepo.delete(getById(id));
    }

}
