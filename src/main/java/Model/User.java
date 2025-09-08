package Model;

import jakarta.persistence.*;


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

    /*
    @Column(
            name = "\"NOME\"",
            nullable = false
    )
    private String name;


    @Column(
            name = "\"ULTIMO_ACESSO\""
    )
    private LocalDateTime lastAccess;
    */

    public void setId(Integer id) {
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

}
