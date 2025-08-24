package Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(
        name = "\"ADMINISTRADOR\""
)
public class Admin {
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
            name = "\"SENHA\""
    )
    private String password;

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
