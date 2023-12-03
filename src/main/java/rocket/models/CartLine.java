package rocket.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_line")
public class CartLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)

    private Integer id;
    @ManyToOne
    private Product product;
    private Float uniCost;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float calcPrice()
    {
        return this.quantity*this.uniCost;
    }

    public CartLine() {

    }

    public CartLine(Product product, Float uniCost, Integer quantity) {
        this.product = product;
        this.uniCost = uniCost;
        this.quantity = quantity;
    }
}