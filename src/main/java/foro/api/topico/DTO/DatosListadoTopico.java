package foro.api.topico.DTO;

import foro.api.topico.Topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(

        Long id,

        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        Boolean status

       // Usuario autor, //many to one
       // Curso curso //many to one
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion(), topico.getStatus());
    }
}
