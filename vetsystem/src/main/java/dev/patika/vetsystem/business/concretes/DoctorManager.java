package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IDoctorService;
import dev.patika.vetsystem.core.exception.NotFoundException;
import dev.patika.vetsystem.core.utilies.Msg;
import dev.patika.vetsystem.dao.DoctorRepo;
import dev.patika.vetsystem.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorManager implements IDoctorService {

    private final DoctorRepo doctorRepo;

    public DoctorManager(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor get(long id) {
        return this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.doctorRepo.findAll(pageable);
    }

    @Override
    public Doctor update(Doctor doctor) {
        this.get(doctor.getId());
        return this.doctorRepo.save(doctor);
    }

    @Override
    public boolean delete(long id) {
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return true;
    }
}
