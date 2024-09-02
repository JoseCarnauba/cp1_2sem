package br.com.fiap.cp1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "email_usuario", nullable = false)
    private String emailUsuario;

    // Construtor padrão
    public Tarefa() {
    }

    // Construtor com parâmetros
    public Tarefa(Long id, String descricao, String emailUsuario) {
        this.id = id;
        this.descricao = descricao;
        this.emailUsuario = emailUsuario;
    }
}
