package dev.patika.vetsystem.api;

import dev.patika.vetsystem.business.abstracts.IAvailableDateService;
import dev.patika.vetsystem.business.abstracts.IDoctorService;
import dev.patika.vetsystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetsystem.dto.availabledate.AvailableDateSaveRequest;
import dev.patika.vetsystem.dto.availabledate.AvailableDateUpdateRequest;
import dev.patika.vetsystem.dto.PageResponse;
import dev.patika.vetsystem.dto.doctor.DoctorResponse;
import dev.patika.vetsystem.dto.availabledate.AvailableDateResponse;
import dev.patika.vetsystem.entities.Doctor;
import dev.patika.vetsystem.entities.AvailableDate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/dates")
@RequiredArgsConstructor
public class AvailableDateController {
    private final IAvailableDateService availableDateService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        return new ResponseEntity<>(availableDateService.create(availableDateSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        return new ResponseEntity<>(availableDateService.update(availableDateUpdateRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(availableDateService.getResponseById(id), HttpStatus.OK);
    }

    @GetMapping("doctor-id/{doctorId}/available-date/{availableDate}")
    public ResponseEntity<?> getByDoctorIdAndAvailableDate(
            @PathVariable("id") Long doctorId,
            @PathVariable("availableDate")LocalDate availableDate
    ) {
        return new ResponseEntity<>(availableDateService.findByDoctorIdAndAvailableDate(doctorId, availableDate), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getPageResponse(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return new ResponseEntity<>(availableDateService.getPageResponse(page, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
       availableDateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
