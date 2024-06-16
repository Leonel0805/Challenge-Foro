package foro.api.usuario.DTO;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticationUser(

        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
