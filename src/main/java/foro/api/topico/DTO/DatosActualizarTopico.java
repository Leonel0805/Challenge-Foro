package foro.api.topico.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopico(

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @Future //mayor a las fecha actual
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime fecha_creacion
) {
}
