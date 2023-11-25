package rocket.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "user_id", nullable = false, length = 20)
    private String userID;
    private String password;
    @Temporal(TemporalType.DATE)
    private Date registerDate;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {        this.registerDate = registerDate;
    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}