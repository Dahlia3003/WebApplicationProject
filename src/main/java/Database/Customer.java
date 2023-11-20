package Database;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Customer extends Account {
    private String customerName;
    private String phoneNumber;
    private String emailAddress;
    private String deliveryAddressDefault;
    @OneToOne
    private Cart cart;
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDeliveryAddressDefault() {
        return deliveryAddressDefault;
    }

    public void setDeliveryAddressDefault(String deliveryAddressDefault) {
        this.deliveryAddressDefault = deliveryAddressDefault;
    }
}