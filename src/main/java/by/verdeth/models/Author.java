package by.verdeth.models;

import java.util.List;

public class Author {
    private Integer idAuthor;
    private String nameAuthor;

    private List<Book> books;


    public Author(Integer idAuthor, String nameAutor) {
        this.idAuthor = idAuthor;
        this.nameAuthor = nameAutor;
    }

    public Author(Integer idAuthor, String nameAuthor, List<Book> books) {
        this.idAuthor = idAuthor;
        this.nameAuthor = nameAuthor;
        this.books = books;
    }

    public Author(String nameAutor) {
        this.nameAuthor = nameAutor;
    }

    public Author() {
    }

    public Integer getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAutor) {
        this.nameAuthor = nameAutor;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getCountBooks()
    {
        return books.size();
    }
}
