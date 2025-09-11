package Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(
        name = "\"ADMINISTRADOR\""
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "\"ID\""
    )
    private int id;

    @Column(
            name = "\"USERNAME\"",
            nullable = false,
            unique = true
    )
    private String username;

   
    @Column(
            name = "\"SENHA\"",
            nullable = false
    )
    private String password;

    @Column(
            name = "\"NOME\"",
            nullable = false
    )
    private String name;

    @Column(
            name = "\"CPF\"",
            nullable = false
    )
    private String cpf;

    @Column(
            name = "\"CARGO\"",
            nullable = false
    )
    private String role;

    @Column(
            name = "\"TIPO\"",
            nullable = false
    )
    private String type;

    @Column(
            name = "\"ATIVO\"",
            nullable = false
    )
    private boolean active;

    @Column(
            name = "\"DATA_CRIACAO\""
    )
    private LocalDateTime creationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getcreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
