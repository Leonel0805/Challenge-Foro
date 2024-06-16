package foro.api.security;

import foro.api.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //component es como Service y Controller pero más atrás de jerarquia
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("El filtro esta siendo llamado");
        var auth_Header = request.getHeader("Authorization");

        System.out.println(auth_Header);
        if (auth_Header != null) {
            var token = auth_Header.replace("Bearer ", "");
            System.out.println(token);
            System.out.println(tokenService.getSubject(token));
            String subject = tokenService.getSubject(token);
            if (subject != null) {
//                usuario ya validado por tener el subject
                UserDetails usuario = usuarioRepository.findByUsername(subject);

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);



    }
}
