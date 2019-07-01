package by.verdeth.models;

import java.util.List;

public class Book {
    //simple values
    private Integer idBook;
    private String nameBook;
    private String summary;
    private Short yearBook;
    private String URLPoster;
    private Integer popular;

    //classes from this project
    private Author author;
    private Seria seria;

    //iterative values
    private List<Genre> genres;

    public Book(Integer idBook, String nameBook, String summary, Short yearBook, String URLPoster, Integer popular, Author author, Seria seria, List<Genre> genres) {
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.summary = summary;
        this.yearBook = yearBook;
        this.URLPoster = URLPoster;
        this.popular = popular;
        this.author = author;
        this.seria = seria;
        this.genres = genres;
    }

    public Seria getSeria() {
        return seria;
    }

    public void setSeria(Seria seria) {
        this.seria = seria;
    }






    public Book(Integer idBook, String nameBook, String summary, Short yearBook, String URLPoster, Author author, List<Genre> genres) {
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.summary = summary;
        this.yearBook = yearBook;
        this.URLPoster = URLPoster;
        this.author = author;
        this.genres = genres;
    }

    public Book(Integer idBook, String nameBook, String summary, Short yearBook, String URLPoster, Author author) {
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.summary = summary;
        this.yearBook = yearBook;
        this.URLPoster = URLPoster;
        this.author = author;
    }

    public Book(Integer idBook, String nameBook, String summary, Short yearBook, String URLPoster) {
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.summary = summary;
        this.yearBook = yearBook;
        this.URLPoster = URLPoster;
    }

    public Book() {
    }


    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Short getYearBook() {
        return yearBook;
    }

    public void setYearBook(Short yearBook) {
        this.yearBook = yearBook;
    }

    public String getURLPoster() {
        return URLPoster;
    }

    public void setURLPoster(String URLPoster) {
        this.URLPoster = URLPoster;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
