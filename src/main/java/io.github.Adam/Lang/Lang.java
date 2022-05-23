package io.github.Adam.Lang;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


//first ORM implementing
@Entity
@Table(name = "languages")
public class Lang {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")

    private Integer id;
    private String welcomeMsg;
    private String code;

    /**
     * Hibernate need it
     */
    public Lang() {

    }

    public Lang(Integer id, String welcomeMsg, String code) {
        this.id = id;
        this.welcomeMsg = welcomeMsg;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
