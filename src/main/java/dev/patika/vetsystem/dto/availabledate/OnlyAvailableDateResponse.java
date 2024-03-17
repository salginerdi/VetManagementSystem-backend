package dev.patika.vetsystem.dto.availabledate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OnlyAvailableDateResponse {
    private Long id;
    private LocalDate availableDate;
}
