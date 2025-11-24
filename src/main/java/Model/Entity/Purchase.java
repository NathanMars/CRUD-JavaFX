package Model.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"COMPRA\"")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ID\"")
    private int id;

    @ManyToOne
    @JoinColumn(name = "\"USUARIO_ID\"", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "\"PRODUTO_ID\"", nullable = false)
    private Product product;

    @Column(name = "\"DATA_COMPRA\"", nullable = false)
    private LocalDateTime date;

    @Column(name = "\"QUANTIDADE\"", nullable = false)
    private int quantity;

    @Column(name = "\"VALOR_TOTAL\"", nullable = false)
    private double totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
