package dev.patika.vetsystem.dto.response.availableDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {
    private Long id;
    private LocalDate availableDate;
    private Long doctorId;
}
