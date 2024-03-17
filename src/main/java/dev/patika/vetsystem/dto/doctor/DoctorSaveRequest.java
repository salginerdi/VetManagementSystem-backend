package dev.patika.vetsystem.dto.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {
    @NotNull(message = "Doktorun adı alanı boşbırakılamaz!")
    private String name;
    @NotNull(message = "Doktorun telefon numarası alanı boş bırakılamaz!")
    private  String phone;
    @Email
    private String mail;
    private String address;
    private String city;
}
