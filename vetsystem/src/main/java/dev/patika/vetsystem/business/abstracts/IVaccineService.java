package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    Vaccine save(Vaccine vaccine);

    Vaccine get(long id);

    Page<Vaccine> cursor(int page, int pageSize);

    Vaccine update(Vaccine vaccine);

    boolean delete(long id);

    // 21-Kullanıcının aşı koruyuculuk bitiş tarihi yaklaşan hayvanları listeleyebilmesi için kullanıcının
    // gireceği başlangıç ve bitiş tarihlerine göre aşı koruyuculuk tarihi bu aralıkta olan hayvanların listesini geri döndüren API end  point'ini oluşturmak.
    List<Vaccine> getVaccinesWithProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

}
