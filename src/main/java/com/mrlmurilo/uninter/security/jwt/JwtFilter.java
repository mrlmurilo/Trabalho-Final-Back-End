package com.mrlmurilo.uninter.security.jwt;

import com.mrlmurilo.uninter.security.service.UsuarioDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j // Adicione para debug
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioDetailsService userDetailsService;

    // ADICIONE ESTE MÉTODO - Ele faz o filtro não executar para rotas públicas
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        boolean skip = path.startsWith("/auth/") || path.startsWith("/profissionais");

        if (skip) {
            log.info("Ignorando JWT filter para rota pública: {}", path);
        }

        return skip;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        log.info("Processando JWT para: {}", request.getRequestURI());

        String auth = request.getHeader("Authorization");
        log.info("Authorization header: {}", auth);

        if (auth != null && auth.startsWith("Bearer ")) {
            try {
                String token = auth.substring(7);
                String login = jwtService.getUsername(token);
                log.info("Username extraído do token: {}", login);

                UserDetails user = userDetailsService.loadUserByUsername(login);
                log.info("Usuário carregado: {}", user.getUsername());

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Autenticação definida no SecurityContext");
            } catch (Exception e) {
                log.error("Erro ao processar token JWT", e);
            }
        } else {
            log.warn("Nenhum token Bearer encontrado para rota protegida");
        }

        chain.doFilter(request, response);
    }
}