package br.com.fiap.cp1.dto;

public record UsuarioDto(
        String email,
        String nome,
        String senha
) {
}
