package rocket.models;

import jakarta.persistence.*;

import java.security.PrivateKey;

@Entity
@Table(name = "keyAuthe")
public class KeyAuthe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String key;
    private String cusId;

    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}