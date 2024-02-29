package dev.patika.vetsystem.dto.customer;

import dev.patika.vetsystem.dto.animal.OnlyAnimalResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<OnlyAnimalResponse> animals;
}
