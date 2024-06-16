package foro.api.usuario.DTO;

import foro.api.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosListadoUsuario (

        Long id,

        String username,

        String password

){

}
