package dev.patika.vetsystem.dto.request.customer;

import dev.patika.vetsystem.entities.Animal;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    @Positive
    private Long id;
    @NotNull(message = "Müşteri adı alanı boş bırakılamaz!")
    private String name;
    @NotNull(message = "Müşteri telefon numarası alanı boş bırakılamaz!")
    private String phone;
    @Email
    private String mail;
    private String address;
    private String city;
    private List<Animal> animals;
}
