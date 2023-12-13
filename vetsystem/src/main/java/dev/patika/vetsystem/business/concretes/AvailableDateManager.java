package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IAvailableDateService;
import dev.patika.vetsystem.core.exception.NotFoundException;
import dev.patika.vetsystem.core.utilies.Msg;
import dev.patika.vetsystem.dao.AvailableDateRepo;
import dev.patika.vetsystem.entities.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AvailableDateManager implements IAvailableDateService {
    private final AvailableDateRepo availableDateRepo;

    public AvailableDateManager(AvailableDateRepo availableDateRepo) {
        this.availableDateRepo = availableDateRepo;
    }


    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(long id) {
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.availableDateRepo.findAll(pageable);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        this.get(availableDate.getId());
        return this.availableDateRepo.save(availableDate);
    }

    // 22-Randevu kaydı oluştururken doktorun girilen tarihte müsait günü olup olmadığı, eğer ki müsait günü varsa randevu
    // kayıtlarında girilen saatte başka bir randevusu olup olmadığı kontrol edilir.
    @Override
    public AvailableDate findByDoctorIdAndAvailableDate(Long doctorId, LocalDate requestedDate) {
        return this.availableDateRepo.findByDoctorIdAndAvailableDate(doctorId, requestedDate).
                orElseThrow(() -> new NotFoundException(Msg.NOT_AVAILABLE));
    }

    @Override
    public boolean delete(long id) {
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return true;
    }
}
