package by.verdeth.models;

import java.util.List;

public class Seria {

    private Integer id;
    private String nameSeria;

    //private List<Book> books;


    public Seria(Integer id, String nameSeria) {
        this.id = id;
        this.nameSeria = nameSeria;
    }

    public Seria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameSeria() {
        return nameSeria;
    }

    public void setNameSeria(String nameSeria) {
        this.nameSeria = nameSeria;
    }
}
