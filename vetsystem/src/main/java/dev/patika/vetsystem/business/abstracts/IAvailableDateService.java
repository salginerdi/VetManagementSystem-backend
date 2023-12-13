package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.entities.AvailableDate;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IAvailableDateService {
    AvailableDate save(AvailableDate availableDate);

    AvailableDate get(long id);

    Page<AvailableDate> cursor(int page, int pageSize);

    AvailableDate update(AvailableDate availableDate);

    // 22-Randevu kaydı oluştururken doktorun girilen tarihte müsait günü olup olmadığı, eğer ki müsait günü varsa randevu
    // kayıtlarında girilen saatte başka bir randevusu olup olmadığı kontrol edilir.
    AvailableDate findByDoctorIdAndAvailableDate(Long doctorId, LocalDate requestedDate);
    boolean delete(long id);

}
