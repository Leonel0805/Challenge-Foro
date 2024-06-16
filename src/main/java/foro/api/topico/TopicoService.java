package foro.api.topico;

import foro.api.curso.Curso;
import foro.api.curso.CursoRepository;
import foro.api.topico.DTO.DatosActualizarTopico;
import foro.api.topico.DTO.DatosDetalleTopico;
import foro.api.topico.DTO.DatosTopico;
import foro.api.usuario.Usuario;
import foro.api.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    TopicoRepository topicoRepository;

//    Registrar topico
    public DatosDetalleTopico registrarTopico(DatosTopico datosTopico) {

        if (datosTopico.autor_id() == null) {
            System.out.println("autor_id null");
        }

        if (datosTopico.curso_id() == null) {
            System.out.println("curso_id null");

        }

        //validaciones

        //registrar topico
        Usuario usuario = usuarioRepository.findById(datosTopico.autor_id()).get();
        Curso curso = cursoRepository.findById(datosTopico.curso_id()).get();
        Topico topico = new Topico(datosTopico, usuario, curso);

        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }

//    Obtener un solo topico
    public DatosDetalleTopico obtenerUnTopico(Long id) {

        Optional<Topico> topico_encontrado = topicoRepository.findById(id);

        if (topico_encontrado.isPresent()) {
            Topico topico = topico_encontrado.get();
            return new DatosDetalleTopico(topico);
        }else {
            System.out.println("Error topico no encontrado");
            return null;
        }

    }

//    Actualizar registro
    public DatosDetalleTopico actualizarTopico(Long id, DatosActualizarTopico datosActualizarTopico) {
        Optional<Topico> topico_encontrado = topicoRepository.findById(id);

        if (topico_encontrado.isEmpty()) {
            System.out.println("Topico a actualizar no encontrado");
            return null;
        }
        Topico topico = topico_encontrado.get();
        topico.actualizarTopico(datosActualizarTopico);
        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);

    }

    public void eliminarTopico(Long id) {
        Optional<Topico> topico_encontrado = topicoRepository.findById(id);

        if (topico_encontrado.isEmpty()) {
            System.out.println("Topico a eliminar no encontrado");
        }
        Topico topico = topico_encontrado.get();
        topico.eliminarTopico();
        topicoRepository.save(topico);

    }

}
