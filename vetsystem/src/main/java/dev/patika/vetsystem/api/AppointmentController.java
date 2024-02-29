package dev.patika.vetsystem.api;

import dev.patika.vetsystem.business.abstracts.IAnimalService;
import dev.patika.vetsystem.business.abstracts.IAppointmentService;
import dev.patika.vetsystem.business.abstracts.IAvailableDateService;
import dev.patika.vetsystem.business.abstracts.IDoctorService;
import dev.patika.vetsystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetsystem.core.result.Result;
import dev.patika.vetsystem.core.result.ResultData;
import dev.patika.vetsystem.core.utilies.ResultHelper;
import dev.patika.vetsystem.dto.appointment.AppointmentSaveRequest;
import dev.patika.vetsystem.dto.PageResponse;
import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.appointment.AppointmentResponse;
import dev.patika.vetsystem.dto.doctor.DoctorResponse;
import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Appointment;
import dev.patika.vetsystem.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;
    private final IAnimalService animalService;
    private final IDoctorService doctorService;
    private final IAvailableDateService availableDateService;
    private final IModelMapperService modelMapper;

    public AppointmentController(IAppointmentService appointmentService,
                                 IAnimalService animalService,
                                 IDoctorService doctorService,
                                 IAvailableDateService availableDateService,
                                 IModelMapperService modelMapper) {
        this.appointmentService = appointmentService;
        this.animalService = animalService;
        this.doctorService = doctorService;
        this.availableDateService = availableDateService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {

        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);

        Animal animal = this.animalService.get(appointmentSaveRequest.getAnimalId());
        saveAppointment.setAnimal(animal);

        Doctor doctor = this.doctorService.get(appointmentSaveRequest.getDoctorId());
        saveAppointment.setDoctor(doctor);

        this.appointmentService.save(saveAppointment);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment, AppointmentResponse.class));
    }



    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") long id) {
        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

    @GetMapping("/{id}/animal")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getAnimal(@PathVariable("id") long id) {
        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment.getAnimal(), AnimalResponse.class));
    }

    @GetMapping("/{id}/doctor")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> getDoctor(@PathVariable("id") long id) {
        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment.getDoctor(), DoctorResponse.class));
    }

    // 24-Kullanıcı tarih aralığı ve doktora göre filtreleme
    @GetMapping("/filter/doctor")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> filterByDateAndDoctor(
            @RequestParam("appointmentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentDate,
            @RequestParam("doctorId") long doctorId
    ) {
        Doctor doctor = doctorService.get(doctorId);
        List<Appointment> appointments = appointmentService.findByAppointmentDateAndDoctor(appointmentDate, doctor);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }

    // 23-Kullanıcı tarih aralığı ve hayvana göre filtreleme
    @GetMapping("/filter/animal")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> filterByDateAndAnimal(
            @RequestParam("appointmentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentDate,
            @RequestParam("animalId") long animalId
    ) {
        Animal animal = animalService.get(animalId);
        List<Appointment> appointments = appointmentService.findByAppointmentDateAndAnimal(appointmentDate, animal);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PageResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Appointment> appointmentPage = this.appointmentService.cursor(page, pageSize);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
        return ResultHelper.cursor(appointmentResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }


}
