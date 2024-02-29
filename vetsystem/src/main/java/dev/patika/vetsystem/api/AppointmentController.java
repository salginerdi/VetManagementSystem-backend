package dev.patika.vetsystem.api;

import dev.patika.vetsystem.business.abstracts.IAnimalService;
import dev.patika.vetsystem.business.abstracts.IAppointmentService;
import dev.patika.vetsystem.business.abstracts.IAvailableDateService;
import dev.patika.vetsystem.business.abstracts.IDoctorService;
import dev.patika.vetsystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetsystem.dto.appointment.AppointmentSaveRequest;
import dev.patika.vetsystem.dto.PageResponse;
import dev.patika.vetsystem.dto.animal.AnimalResponse;
import dev.patika.vetsystem.dto.appointment.AppointmentResponse;
import dev.patika.vetsystem.dto.appointment.AppointmentUpdateRequest;
import dev.patika.vetsystem.dto.doctor.DoctorResponse;
import dev.patika.vetsystem.entities.Animal;
import dev.patika.vetsystem.entities.Appointment;
import dev.patika.vetsystem.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final IAppointmentService appointmentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(appointmentService.getResponseById(id), HttpStatus.OK);
    }

    // 24-Kullanıcı tarih aralığı ve doktora göre filtreleme
    @GetMapping("/filter/doctor")
    public ResponseEntity<?> filterByDateAndDoctor(
            @RequestParam("appointmentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentDate,
            @RequestParam("doctorId") Long doctorId
    ) {
        return new ResponseEntity<>(appointmentService.findByAppointmentDateAndDoctorId(appointmentDate, doctorId), HttpStatus.OK);
    }

    // 23-Kullanıcı tarih aralığı ve hayvana göre filtreleme
    @GetMapping("/filter/animal")
    public ResponseEntity<?> filterByDateAndAnimal(
            @RequestParam("appointmentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentDate,
            @RequestParam("animalId") Long animalId
    ) {
        return new ResponseEntity<>(appointmentService.findByAppointmentDateAndAnimalId(appointmentDate, animalId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getPageResponse(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return new ResponseEntity<>(appointmentService.getPageResponse(page, pageSize), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return new ResponseEntity<>(appointmentService.create(appointmentSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        return new ResponseEntity<>(appointmentService.update(appointmentUpdateRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
       appointmentService.delete(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
