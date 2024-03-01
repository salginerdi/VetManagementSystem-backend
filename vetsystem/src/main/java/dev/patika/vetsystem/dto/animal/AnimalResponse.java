package dev.patika.vetsystem.dto.animal;

import dev.patika.vetsystem.dto.customer.OnlyCustomerResponse;

import dev.patika.vetsystem.dto.vaccine.OnlyVaccineResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private Long customerId;
    private OnlyCustomerResponse customer;
    private List<OnlyVaccineResponse> vaccines;
}
