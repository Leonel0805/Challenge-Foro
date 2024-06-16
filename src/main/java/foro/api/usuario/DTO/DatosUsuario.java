package foro.api.usuario.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import foro.api.curso.Curso;
import foro.api.usuario.Usuario;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosUsuario(

        @NotNull
        String username,

        @NotBlank
        String correoElectronico,

        @NotBlank
        String password

) {
}
