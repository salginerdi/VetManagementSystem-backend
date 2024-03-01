package dev.patika.vetsystem.dto.report;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OnlyReportResponse {
    private Long id;
    private String title;
    private String diagnosis;
    private double price;
}
