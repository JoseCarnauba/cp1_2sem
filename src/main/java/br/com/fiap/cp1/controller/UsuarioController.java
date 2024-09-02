package br.com.fiap.cp1.controller;

import br.com.fiap.cp1.dto.UsuarioDto;
import br.com.fiap.cp1.model.Usuario;
import br.com.fiap.cp1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        if (usuarioService.userExists(usuarioDto.email()))
            return ResponseEntity.badRequest().body(null); // Retorna um erro 400 se o usuário já existir

        // Converter UsuarioDto para Usuario
        var usuario = new Usuario();
        usuario.setNome(usuarioDto.nome());
        usuario.setEmailUsuario(usuarioDto.email());
        usuario.setSenha(usuarioDto.senha());

        Usuario savedUsuario = usuarioService.saveUsuario(usuario);

        // Converter Usuario para UsuarioDto
        UsuarioDto savedUsuarioDto = new UsuarioDto(
                savedUsuario.getEmailUsuario(),
                savedUsuario.getNome(),
                savedUsuario.getSenha()
        );

        return ResponseEntity.ok(savedUsuarioDto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        List<UsuarioDto> usuarioDtos = usuarios.stream()
                .map(usuario -> new UsuarioDto(
                        usuario.getEmailUsuario(),
                        usuario.getNome(),
                        usuario.getSenha()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(usuarioDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        UsuarioDto usuarioDto = new UsuarioDto(
                usuario.getEmailUsuario(),
                usuario.getNome(),
                usuario.getSenha()
        );

        return ResponseEntity.ok(usuarioDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuario.setEmailUsuario(usuarioDto.email());
        usuario.setNome(usuarioDto.nome());
        usuario.setSenha(usuarioDto.senha());

        Usuario updatedUsuario = usuarioService.saveUsuario(usuario);

        UsuarioDto updatedUsuarioDto = new UsuarioDto(
                updatedUsuario.getEmailUsuario(),
                updatedUsuario.getNome(),
                updatedUsuario.getSenha()
        );

        return ResponseEntity.ok(updatedUsuarioDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.deleteUsuario(id);

        return ResponseEntity.noContent().build();
    }
}


