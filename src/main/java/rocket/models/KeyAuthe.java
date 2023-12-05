package rocket.models;

import jakarta.persistence.*;
@Entity
@Table(name = "keyAuthe")
public class KeyAuthe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String key;

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