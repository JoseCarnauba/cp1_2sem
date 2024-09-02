package br.com.fiap.cp1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @Email(message = "Email deve ser um endereço de e-mail válido")
        @NotBlank(message = "Email não pode estar vazio")
        String email,

        @NotBlank(message = "Senha não pode estar vazia")
        @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
        String senha
) {
}

