package br.com.fiap.cp1.dto;

import java.time.LocalDate;

public record TarefaDto(
        String titulo,
        String descricao,
        LocalDate data,
        String status
) {
}


