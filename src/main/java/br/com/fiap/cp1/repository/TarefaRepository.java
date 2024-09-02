package br.com.fiap.cp1.repository;

import br.com.fiap.cp1.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // Encontrar tarefas pelo email do usuário
    List<Tarefa> findByEmailUsuario(String email);

    // Encontrar tarefa por ID e email do usuário
    Tarefa findByIdAndEmailUsuario(Long id, String email);

    // Encontrar tarefa por ID e descrição
    Optional<Tarefa> findByIdAndDescricao(Long id, String descricao);
}
