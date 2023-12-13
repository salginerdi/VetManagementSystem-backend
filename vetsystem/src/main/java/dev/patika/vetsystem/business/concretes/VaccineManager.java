package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IVaccineService;
import dev.patika.vetsystem.core.exception.NotFoundException;
import dev.patika.vetsystem.core.utilies.Msg;
import dev.patika.vetsystem.dao.VaccineRepo;
import dev.patika.vetsystem.entities.Vaccine;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineManager implements IVaccineService {
    private final VaccineRepo vaccineRepo;

    public VaccineManager(VaccineRepo vaccineRepo) {
        this.vaccineRepo = vaccineRepo;
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        validateVaccine(vaccine);
        return this.vaccineRepo.save(vaccine);
    }

    // Eğer hastaya ait aynı tip aşının koruyuculuk bitiş tarihi daha gelmemiş ise sisteme yeni aşı girilememelidir.
    private void validateVaccine(Vaccine vaccine) {
        Optional<Vaccine> checkVaccine = this.vaccineRepo.validateVaccine(
                vaccine.getCode(),
                vaccine.getAnimal().getId(),
                vaccine.getProtectionStartDate()
        );
        if (checkVaccine.isPresent()) {
            throw new NotFoundException(Msg.ADD_ERROR);
        }
    }

    @Override
    public Vaccine get(long id) {
        return this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.vaccineRepo.findAll(pageable);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId());
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public boolean delete(long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    // 21-Kullanıcının aşı koruyuculuk bitiş tarihi yaklaşan hayvanları listeleyebilmesi için kullanıcının gireceği başlangıç ve bitiş tarihlerine göre
    // aşı koruyuculuk tarihi bu aralıkta olan hayvanların listesini geri döndüren API end  point'ini oluşturmak.
    @Override
    public List<Vaccine> getVaccinesWithProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate) {
        return vaccineRepo.findByProtectionFinishDateBetween(startDate, endDate);
    }

}
