package foro.api.controller;

import foro.api.topico.DTO.DatosActualizarTopico;
import foro.api.topico.DTO.DatosDetalleTopico;
import foro.api.topico.DTO.DatosListadoTopico;
import foro.api.topico.DTO.DatosTopico;
import foro.api.topico.Topico;
import foro.api.topico.TopicoRepository;
import foro.api.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    TopicoService topicoService;

    @GetMapping
    public Page<DatosListadoTopico> obtenerTopicos(@PageableDefault(size = 3) Pageable paginacion) {

        return topicoRepository.findByStatusTrue(paginacion)
                .map(topico -> new DatosListadoTopico(topico));
    }

    @PostMapping
    public ResponseEntity publicarTopico(@RequestBody @Valid DatosTopico datosTopico,
                                         UriComponentsBuilder uricomponent) {

        var response = topicoService.registrarTopico(datosTopico);
        URI url = uricomponent.path("/topicos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerUnTopico(@PathVariable Long id) {

        var response = topicoService.obtenerUnTopico(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizarTopico datosActualizarTopico) {

        var response = topicoService.actualizarTopico(id, datosActualizarTopico);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {

        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
