package foro.api.topico.DTO;

import foro.api.topico.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        Boolean status,
        Long autor_id,
        Long curso_id
) {

    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_creacion(),
                topico.getStatus(),
                topico.getAutor().getId(),
                topico.getCurso().getId()
        );
    }
}
