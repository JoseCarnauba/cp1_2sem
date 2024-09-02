package br.com.fiap.cp1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_usuario", unique = true, nullable = false)
    private String emailUsuario;

    @Column(name = "senha", nullable = false)
    private String senha;

    // Método para obter o nome do usuário
    @Getter
    @Column(name = "nome") // Adicionando o campo nome
    private String nome;

    // Construtor padrão
    public Usuario() {
    }

    // Construtor com parâmetros
    public Usuario(Long id, String email, String emailUsuario, String senha, String nome) {
        this.id = id;
        this.emailUsuario = emailUsuario;
        this.senha = senha;
        this.nome = nome;
    }

    // Método para retornar o login do usuário
    public String getLogin() {
        return emailUsuario;
    }

    // Método para definir o nome do usuário
    public void setNome(String nome) {
        this.nome = nome;
    }

}




