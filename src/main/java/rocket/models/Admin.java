package rocket.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
public class Admin extends Account {
    public Admin(String userID, String password, Date registerDate) {
        super(userID, password, registerDate);
    }

    public Admin() {

    }
}