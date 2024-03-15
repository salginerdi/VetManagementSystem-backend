package dev.patika.vetsystem.api;

import dev.patika.vetsystem.business.abstracts.IReportService;
import dev.patika.vetsystem.dto.report.ReportSaveRequest;
import dev.patika.vetsystem.dto.report.ReportUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final IReportService reportService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ReportSaveRequest reportSaveRequest) {
        return new ResponseEntity<>(reportService.create(reportSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid @RequestBody ReportUpdateRequest reportUpdateRequest) {
        return new ResponseEntity<>(reportService.update(reportUpdateRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(reportService.getResponseById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getPageResponse(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return new ResponseEntity<>(reportService.getPageResponse(page, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        reportService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
