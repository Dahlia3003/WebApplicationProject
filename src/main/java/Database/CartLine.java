package Database;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_line")
public class CartLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    private Product product;
    private Float uniCost;
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getUniCost() {
        return uniCost;
    }

    public void setUniCost(float uniCost) {
        this.uniCost = uniCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}