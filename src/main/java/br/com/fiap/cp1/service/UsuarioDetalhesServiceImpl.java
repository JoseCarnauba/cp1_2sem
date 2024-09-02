package br.com.fiap.cp1.service;

import br.com.fiap.cp1.model.Usuario;
import br.com.fiap.cp1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioDetalhesServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário pelo email (username)
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailUsuario(username);

        // Se o usuário não for encontrado, lança uma exceção
        Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Retorna o UserDetails construído a partir do usuário encontrado
        return User.builder()
                .username(usuario.getEmailUsuario())
                .password(usuario.getSenha()) // Senha já está criptografada
                .roles("USER")
                .build();
    }
}



