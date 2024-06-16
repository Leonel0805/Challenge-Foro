package foro.api.controller;

import foro.api.topico.DTO.DatosListadoTopico;
import foro.api.topico.DTO.DatosTopico;
import foro.api.topico.TopicoRepository;
import foro.api.usuario.DTO.DatosListadoUsuario;
import foro.api.usuario.DTO.DatosUsuario;
import foro.api.usuario.Usuario;
import foro.api.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController{

    @Autowired
    UsuarioRepository usuarioRepository;


    @PostMapping
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosUsuario datosUsuario) {

        Usuario usuario = new Usuario(datosUsuario);

        usuarioRepository.save(usuario);


        return ResponseEntity.ok(datosUsuario);
    }
}


