package by.verdeth.dao.bookDao;

import by.verdeth.dao.CrudDao;
import by.verdeth.models.Book;

import java.util.List;

public interface BookDao extends CrudDao<Book> {

    ///interface for DAOBook which extends CrudDAO and adds specific methods

    //returns all books sort by popular
    List<Book> findAllSortPopular(Integer var1, Integer var2);

    //returns all books sort by popular
    List<Book> findAllSortYear(Integer var1, Integer var2);

    //returns all books sort by popular
    List<Book> findAllSortDateAdd(Integer var1, Integer var2);

    //returns all books contain string var1 in a summary or in a name of book
    List<Book> findSubstring(String var1);

    //6 overloaded methods with default variables
    List<Book> findAllSortPopular(Integer var1);

    List<Book> findAllSortYear(Integer var1);

    List<Book> findAllSortDateAdd(Integer var1);

    List<Book> findAllSortPopular();

    List<Book> findAllSortYear();

    List<Book> findAllSortDateAdd();

    //returns books of author by its id
    List<Book> findAllByIdAuthor(Integer var1);

    //returns books of genre by its id
    List<Book> findAllByIdGenre(Integer var1);

    //returns books of seria by its id
    List<Book> findAllByIdSeria(Integer var1);

    //increment popular
    void incPopular(Integer id);

}
