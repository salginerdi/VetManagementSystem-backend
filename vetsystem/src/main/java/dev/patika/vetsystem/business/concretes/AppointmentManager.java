package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IAppointmentService;
import dev.patika.vetsystem.business.abstracts.IAvailableDateService;
import dev.patika.vetsystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetsystem.core.exception.NotFoundException;
import dev.patika.vetsystem.core.result.ResultData;
import dev.patika.vetsystem.core.utilies.Msg;
import dev.patika.vetsystem.core.utilies.ResultHelper;
import dev.patika.vetsystem.dao.AppointmentRepo;
import dev.patika.vetsystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vetsystem.dto.response.appointment.AppointmentResponse;
import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Appointment;
import dev.patika.vetsystem.entities.AvailableDate;
import dev.patika.vetsystem.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final IAvailableDateService availableDateService;
    private final IModelMapperService modelMapperService;

    public AppointmentManager(AppointmentRepo appointmentRepo,
                              IAvailableDateService availableDateService,
                              IModelMapperService modelMapperService) {
        this.appointmentRepo = appointmentRepo;
        this.availableDateService = availableDateService;
        this.modelMapperService = modelMapperService;
    }

    // 22-Randevu kaydı oluştururken doktorun girilen tarihte müsait günü olup olmadığı
    // eğer ki varsa randevu kayıtlarında girilen saatte başka bir randevusu olup olmadığı kontrol edilir.
    public ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest) {
        checkValidityOf(appointmentSaveRequest);
        Appointment appointmentToBeSaved = this.modelMapperService.forRequest().map(appointmentSaveRequest, Appointment.class);
        appointmentToBeSaved.setId(null);

        // 22-Her doktor için sadece saat başı randevu oluşturulabilir.
        LocalDateTime requestedDateTime = appointmentSaveRequest.getAppointmentDate();
        LocalDateTime truncatedDateTime = requestedDateTime.withMinute(0).withSecond(0).withNano(0);

        // 22-Doktorun mevcut randevu saatlerini kontrol edilir, sadece saat başlarında yeni randevu oluşturulmasına izin verilir.
        Optional<Appointment> hourConflict = this.appointmentRepo.checkForConflictingAppointmentHoursByDoctorAndHour(truncatedDateTime, appointmentSaveRequest.getDoctorId());
        if (hourConflict.isPresent())
            throw new NotFoundException(Msg.NOT_AVAILABLE);

        this.appointmentRepo.save(appointmentToBeSaved);
        AppointmentResponse appointmentResponse = this.modelMapperService.forResponse().map(appointmentToBeSaved, AppointmentResponse.class);
        return ResultHelper.success(appointmentResponse);

    }

    private void checkValidityOf(AppointmentSaveRequest appointmentSaveRequest) {
        AvailableDate checkIfThereIsAvailableDoctor = this.availableDateService.findByDoctorIdAndAvailableDate(
                appointmentSaveRequest.getDoctorId(),
                appointmentSaveRequest.getAppointmentDate().toLocalDate()
        );
        Optional<Appointment> hourConflict = this.appointmentRepo.checkForConflictingAppointmentHours(
                appointmentSaveRequest.getAppointmentDate()
        );
        if (hourConflict.isPresent())
            throw new NotFoundException(Msg.NOT_AVAILABLE);
    }


    @Override
    public Appointment save(Appointment appointment) {
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public Appointment get(long id) {
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.appointmentRepo.findAll(pageable);
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.get(appointment.getId());
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public boolean delete(long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }

    // 24-Doktor ve randevuya göre filtreleme
    @Override
    public List<Appointment> findByAppointmentDateAndDoctor(LocalDateTime appointmentDate, Doctor doctor) {
        return this.appointmentRepo.findByAppointmentDateAndDoctor(appointmentDate, doctor);
    }

    // 23-Hayvan ve randevuya göre filtreleme
    @Override
    public List<Appointment> findByAppointmentDateAndAnimal(LocalDateTime appointmentDate, Animal animal) {
        return this.appointmentRepo.findByAppointmentDateAndAnimal(appointmentDate, animal);
    }


}
