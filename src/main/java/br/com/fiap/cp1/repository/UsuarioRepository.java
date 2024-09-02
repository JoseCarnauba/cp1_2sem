package br.com.fiap.cp1.repository;

import br.com.fiap.cp1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Encontra um usuário pelo campo 'emailUsuario'
    Optional<Usuario> findByEmailUsuario(String emailUsuario);
}
