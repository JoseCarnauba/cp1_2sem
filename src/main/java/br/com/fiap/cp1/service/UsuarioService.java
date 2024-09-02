package br.com.fiap.cp1.service;

import br.com.fiap.cp1.model.Usuario;
import br.com.fiap.cp1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder senhaEncoder;

    public Usuario saveUsuario(Usuario usuario) {
        // Codifica a senha antes de salvar
        usuario.setSenha(senhaEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public boolean userExists(String email) {
        // Verifica se o usuário existe usando Optional
        return usuarioRepository.findByEmailUsuario(email).isPresent();
    }

    public void deleteUsuario(Long id) {
        // Remove o usuário pelo ID
        usuarioRepository.deleteById(id);
    }

    public Usuario getUsuarioById(Long id) {
        // Retorna o usuário se encontrado, caso contrário, retorna null
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> getAllUsuarios() {
        // Retorna a lista de todos os usuários
        return usuarioRepository.findAll();
    }
}
