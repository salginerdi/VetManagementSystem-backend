package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.dto.customer.CustomerResponse;
import dev.patika.vetsystem.dto.customer.CustomerSaveRequest;
import dev.patika.vetsystem.dto.customer.CustomerUpdateRequest;
import dev.patika.vetsystem.dto.doctor.DoctorResponse;
import dev.patika.vetsystem.dto.doctor.DoctorSaveRequest;
import dev.patika.vetsystem.dto.doctor.DoctorUpdateRequest;
import dev.patika.vetsystem.entities.Customer;
import dev.patika.vetsystem.entities.Doctor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDoctorService {
    Doctor getById(Long id);
    DoctorResponse getResponseById(Long id);
    List<DoctorResponse> getPageResponse(int page, int pageSize);
    DoctorResponse create(DoctorSaveRequest doctorSaveRequest);
    DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest);

    void delete(Long id);
}
