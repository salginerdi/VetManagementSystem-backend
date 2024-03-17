package dev.patika.vetsystem.business.abstracts;

import dev.patika.vetsystem.dto.report.ReportResponse;
import dev.patika.vetsystem.dto.report.ReportSaveRequest;
import dev.patika.vetsystem.dto.report.ReportUpdateRequest;
import dev.patika.vetsystem.entities.Report;

import java.util.List;

public interface IReportService {
    Report getById(Long id);
    ReportResponse getResponseById(Long id);
    List<ReportResponse> getPageResponse(int page, int pageSize);
    ReportResponse create(ReportSaveRequest reportSaveRequest);
    ReportResponse update(ReportUpdateRequest reportUpdateRequest);

    void delete(Long id);
}
