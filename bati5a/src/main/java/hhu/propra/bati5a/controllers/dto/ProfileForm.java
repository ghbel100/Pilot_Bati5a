package hhu.propra.bati5a.controllers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ProfileForm(
        @NotBlank(message = "Name darf nicht leer sein") String name,

        @NotBlank(message = "Email darf nicht leer sein") @Email(message = "Ungültige Email-Adresse") String email,

        @NotBlank(message = "Bereich darf nicht leer sein") String branch,

        @NotNull(message = "Geburtsdatum darf nicht leer sein") @Past(message = "Geburtsdatum muss in der Vergangenheit liegen") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,

        @NotBlank(message = "Rolle muss ausgewählt werden") String role) {
}
