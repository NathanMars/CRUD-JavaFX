package Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(
        name = "\"CLIENTE\""
)
public class Client {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "\"ID\""
    )
    private int id;

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
            name = "\"EMAIL\"",
            nullable = false
    )
    private String email;

    @Column(
            name = "\"TELEFONE\"",
            nullable = false
    )
    private String phone;

    @Column(
            name = "\"ENDERECO\"",
            nullable = false
    )
    private String address;

    @Column(
            name = "\"TIPO\"",
            nullable = false
    )
    private String type;

    @Column(
            name = "\"NASCIMENTO\""
    )
    private LocalDate birthdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
