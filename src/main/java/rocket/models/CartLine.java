package rocket.models;

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
    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getUniCost() {
        return uniCost;
    }

    public void setUniCost(Float uniCost) {
        this.uniCost = uniCost;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Float calcPrice()
    {
        return this.quantity*this.uniCost;
    }

    public CartLine(Long id, Product product, Float uniCost, Long quantity) {
        this.id = id;
        this.product = product;
        this.uniCost = uniCost;
        this.quantity = quantity;
    }
}