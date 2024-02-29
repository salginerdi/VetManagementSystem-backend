package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.doctor.DoctorResponse;
import dev.patika.vetsystem.dto.doctor.DoctorSaveRequest;
import dev.patika.vetsystem.dto.doctor.DoctorUpdateRequest;
import dev.patika.vetsystem.dto.vaccine.VaccineResponse;
import dev.patika.vetsystem.dto.vaccine.VaccineSaveRequest;
import dev.patika.vetsystem.dto.vaccine.VaccineUpdateRequest;
import dev.patika.vetsystem.entities.Doctor;
import dev.patika.vetsystem.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    Vaccine getById(Long id);
    VaccineResponse getResponseById(Long id);
    List<VaccineResponse> getPageResponse(int page, int pageSize);
    VaccineResponse create(VaccineSaveRequest vaccineSaveRequest);
    VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest);

    void delete(Long id);

    // 21-Kullanıcının aşı koruyuculuk bitiş tarihi yaklaşan hayvanları listeleyebilmesi için kullanıcının
    // gireceği başlangıç ve bitiş tarihlerine göre aşı koruyuculuk tarihi bu aralıkta olan hayvanların listesini geri döndüren API end  point'ini oluşturmak.
    List<Vaccine> getVaccinesWithProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

    AnimalResponse getAnimalResponse(Long id);
}
