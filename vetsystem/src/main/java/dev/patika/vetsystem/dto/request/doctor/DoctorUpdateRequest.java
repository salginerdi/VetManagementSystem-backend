package dev.patika.vetsystem.dto.request.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateRequest {
    @Positive
    private Long id;
    @NotNull(message = "Doktorun adı alanı boşbırakılamaz!")
    private String name;
    @NotNull(message = "Doktorun telefon numarası alanı boş bırakılamaz!")
    private  String phone;
    @Email
    private String mail;
    private String address;
    private String city;
}
