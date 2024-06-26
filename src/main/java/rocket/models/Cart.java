package rocket.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<CartLine> cartList;

    public List<CartLine> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartLine> cartList) {
        this.cartList = cartList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cart(List<CartLine> cartList) {
        this.cartList = cartList;
    }

    public Cart() {
    }

    public void checkOut(){
        Order order = new Order();
        order.setProductList(this.cartList);
    }

    public List<CartLine> viewDetails(){
        return this.cartList;
    }

    public Integer calcTotal() {
        Integer totalUnitCost = 0;

        for (CartLine cartLine : this.cartList) {
            totalUnitCost += cartLine.getUniCost();
        }
        return totalUnitCost;
    }


}