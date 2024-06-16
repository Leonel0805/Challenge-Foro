package foro.api.controller;

import foro.api.security.DatosJWTtoken;
import foro.api.usuario.DTO.DatosAutenticationUser;
import foro.api.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import foro.api.security.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    //    nos hace la autentication, espera un token
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticationUser(@RequestBody @Valid DatosAutenticationUser datosAutenticationUser) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                datosAutenticationUser.username(),
                datosAutenticationUser.password()
        );

        var usuarioAutenticado = authenticationManager.authenticate(token);

        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }
}
