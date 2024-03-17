package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.dto.availabledate.AvailableDateResponse;
import dev.patika.vetsystem.dto.availabledate.AvailableDateSaveRequest;
import dev.patika.vetsystem.dto.availabledate.AvailableDateUpdateRequest;
import dev.patika.vetsystem.dto.customer.CustomerResponse;
import dev.patika.vetsystem.dto.customer.CustomerSaveRequest;
import dev.patika.vetsystem.dto.customer.CustomerUpdateRequest;
import dev.patika.vetsystem.entities.AvailableDate;
import dev.patika.vetsystem.entities.Customer;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IAvailableDateService {
    AvailableDate getById(Long id);
    AvailableDateResponse getResponseById(Long id);
    List<AvailableDateResponse> getPageResponse(int page, int pageSize);
    AvailableDateResponse create(AvailableDateSaveRequest availableDateSaveRequest);
    AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest);

    void delete(Long id);
    // 22-Randevu kaydı oluştururken doktorun girilen tarihte müsait günü olup olmadığı, eğer ki müsait günü varsa randevu
    // kayıtlarında girilen saatte başka bir randevusu olup olmadığı kontrol edilir.
    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate requestedDate);

}
