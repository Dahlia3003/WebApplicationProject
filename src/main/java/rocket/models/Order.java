package rocket.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartLine> productList;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOrder;
    @ManyToOne
    private Customer customer;
    private String status;
    private String paymentMethod;
    private Float totalCost;
    public List<CartLine> getProductList() {
        return productList;
    }

    public Order(Long id, List<CartLine> productList, Date dateOrder, Customer customer, String status, String paymentMethod) {
        this.id = id;
        this.productList = productList;
        this.dateOrder = dateOrder;
        this.customer = customer;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public void setProductList(List<CartLine> productList) {
        this.productList = productList;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}