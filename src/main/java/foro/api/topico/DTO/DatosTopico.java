package foro.api.topico.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import foro.api.curso.Curso;
import foro.api.usuario.Usuario;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosTopico (

        @NotNull
        String titulo,

        @NotBlank
        String mensaje,

        @Future //mayor a las fecha actual
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime fecha_creacion,

        @NotNull
        Long autor_id,

        @NotNull
        Long curso_id
){
}
