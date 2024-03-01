package dev.patika.vetsystem.business.concretes;

import dev.patika.vetsystem.business.abstracts.IReportService;
import dev.patika.vetsystem.core.config.modelMapper.ModelMapperService;
import dev.patika.vetsystem.dao.ReportRepo;
import dev.patika.vetsystem.dto.report.ReportResponse;
import dev.patika.vetsystem.dto.report.ReportSaveRequest;
import dev.patika.vetsystem.dto.report.ReportUpdateRequest;
import dev.patika.vetsystem.entities.Report;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportManager implements IReportService {
    private final ReportRepo reportRepo;
    private final ModelMapperService modelMapper;

    @Override
    public Report getById(Long id) {
        return reportRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report with ID " + id + " not found"));
    }

    @Override
    public ReportResponse getResponseById(Long id) {
        return modelMapper
                .forResponse()
                .map(getById(id), ReportResponse.class);
    }

    @Override
    public List<ReportResponse> getPageResponse(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Report> reportPage = reportRepo.findAll(pageable);

        return reportPage.stream().map(
                report -> modelMapper.forResponse().map(report, ReportResponse.class)
        ).toList();
    }


    @Override
    public ReportResponse create(ReportSaveRequest reportSaveRequest) {
        Report saveReport = modelMapper
                .forRequest()
                .map(reportSaveRequest, Report.class);

        return modelMapper
                .forResponse()
                .map(reportRepo.save(saveReport), ReportResponse.class);
    }

    @Override
    public ReportResponse update(ReportUpdateRequest reportUpdateRequest) {
        Report doesReportExist = getById(reportUpdateRequest.getId());

        modelMapper
                .forRequest()
                .map(reportUpdateRequest, doesReportExist);

        return modelMapper
                .forResponse()
                .map(reportRepo.save(doesReportExist), ReportResponse.class);
    }

    @Override
    public void delete(Long id) {
        reportRepo.delete(getById(id));
    }
}
