package Model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "\"PRODUTO\"")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ID\"")
    private int id;

    @Column(name = "\"NOME\"", nullable = false)
    private String name;

    @Column(name = "\"PRECO\"", nullable = false)
    private double price;

    @Column(name = "\"QUANTIDADE\"", nullable = false)
    private int quantity;

    @Column(name = "\"DESCRICAO\"")
    private String description;

    @Column(name = "\"ATIVO\"", nullable = false)
    private boolean active;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
