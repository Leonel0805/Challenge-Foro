package foro.api.topico;

import foro.api.curso.Curso;
import foro.api.topico.DTO.DatosActualizarTopico;
import foro.api.topico.DTO.DatosTopico;
import foro.api.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topico")
@Getter
@NoArgsConstructor //generamos el constructor vacio
@AllArgsConstructor //
@EqualsAndHashCode(of="id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    private Boolean status;

    @ManyToOne
    private Usuario autor; //many to one

   @ManyToOne
    private Curso curso; //many to one
    //falta respuestas respuestas; //oneToMany


    public Topico(DatosTopico datosTopico, Usuario usuario, Curso curso) {
        this.titulo = datosTopico.titulo();
        this.mensaje = datosTopico.mensaje();
        this.fecha_creacion = datosTopico.fecha_creacion();
        this.status = true;
        this.autor = usuario;
        this.curso = curso;
    }

    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
        this.titulo = datosActualizarTopico.titulo();
        this.mensaje = datosActualizarTopico.mensaje();
        this.fecha_creacion = datosActualizarTopico.fecha_creacion();
    }

    public void eliminarTopico() {
        this.status = false;
    }


}
