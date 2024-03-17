package dev.patika.vetsystem.api;

import dev.patika.vetsystem.business.abstracts.IDoctorService;
import dev.patika.vetsystem.core.config.modelMapper.IModelMapperService;
import dev.patika.vetsystem.dto.doctor.DoctorSaveRequest;
import dev.patika.vetsystem.dto.doctor.DoctorUpdateRequest;
import dev.patika.vetsystem.dto.PageResponse;
import dev.patika.vetsystem.dto.doctor.DoctorResponse;
import dev.patika.vetsystem.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final IDoctorService doctorService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        return new ResponseEntity<>(doctorService.create(doctorSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        return new ResponseEntity<>(doctorService.update(doctorUpdateRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(doctorService.getResponseById(id), HttpStatus.OK);
    }

    // Listeleme işlemi
    @GetMapping
    public ResponseEntity getPageResponse(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return new ResponseEntity<>(doctorService.getPageResponse(page, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        doctorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
