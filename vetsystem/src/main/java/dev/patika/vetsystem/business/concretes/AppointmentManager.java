package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IAppointmentService;
import dev.patika.vetsystem.business.abstracts.IAvailableDateService;
import dev.patika.vetsystem.core.config.modelMapper.ModelMapperService;
import dev.patika.vetsystem.dao.AppointmentRepo;
import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.appointment.AppointmentResponse;
import dev.patika.vetsystem.dto.appointment.AppointmentSaveRequest;
import dev.patika.vetsystem.dto.appointment.AppointmentUpdateRequest;
import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Appointment;
import dev.patika.vetsystem.entities.AvailableDate;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final IAvailableDateService availableDateService;
    private final ModelMapperService modelMapper;

    @Override
    public Appointment getById(Long id) {
        return appointmentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found" + id));
    }

    @Override
    public AppointmentResponse getResponseById(Long id) {
        return modelMapper.forResponse().map(getById(id), AppointmentResponse.class);
    }

    @Override
    public List<AppointmentResponse> getPageResponse(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Appointment> appointmentPage = appointmentRepo.findAll(pageable);

        return appointmentPage.stream().map(
                        appointment ->
                                modelMapper
                                        .forResponse()
                                        .map(appointment, AppointmentResponse.class))
                .toList();
    }


    @Override
    public AppointmentResponse create(AppointmentSaveRequest appointmentSaveRequest) {
        checkValidityOf(appointmentSaveRequest);

        Appointment saveAppointment = this.modelMapper
                .forRequest()
                .map(appointmentSaveRequest, Appointment.class);

        return modelMapper
                .forResponse()
                .map(appointmentRepo.save(saveAppointment), AppointmentResponse.class);
    }

    private void checkValidityOf(AppointmentSaveRequest appointmentSaveRequest) {
       Optional<AvailableDate> checkIfThereIsAvailableDoctor = this.availableDateService.findByDoctorIdAndAvailableDate(
                appointmentSaveRequest.getDoctor().getId(),
                appointmentSaveRequest.getAppointmentDate().toLocalDate()
        );
       if (checkIfThereIsAvailableDoctor.isEmpty()) {
           throw new NotFoundException("Doctor is not available at this date");
       }

        Optional<Appointment> hourConflict = this.appointmentRepo.checkForConflictingAppointmentHoursByHourAndDoctorId(
                appointmentSaveRequest.getAppointmentDate(),
                appointmentSaveRequest.getDoctor().getId()

        );

        if (hourConflict.isPresent())
            throw new NotFoundException("There is an hour conflict present");
    }

    @Override
    public AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment doesAppointmentExist = getById(appointmentUpdateRequest.getId());

        modelMapper
                .forRequest()
                .map(appointmentUpdateRequest, doesAppointmentExist);

        return modelMapper
                .forResponse()
                .map(appointmentRepo.save(doesAppointmentExist), AppointmentResponse.class);
    }

    @Override
    public void delete(Long id) {
        appointmentRepo.delete(getById(id));
    }

    @Override
    public List<Appointment> findByAppointmentDateAndDoctorId(LocalDateTime appointmentDate, Long doctorId) {
        return appointmentRepo.findByAppointmentDateAndDoctorId(appointmentDate, doctorId);
    }

    @Override
    public List<Appointment> findByAppointmentDateAndAnimalId(LocalDateTime appointmentDate, Long animalId) {
        return appointmentRepo.findByAppointmentDateAndAnimalId(appointmentDate, animalId);
    }
}
