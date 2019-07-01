package by.verdeth.models;

import java.util.List;

public class Genre {
    private Integer id;
    private String nameGenre;

    //private List<Book> books;

    public Genre(Integer id, String nameGenre) {
        this.id = id;
        this.nameGenre = nameGenre;
    }

    public Genre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public Genre() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }
}
