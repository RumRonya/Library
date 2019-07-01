package by.verdeth.dao.bookDao;

import by.verdeth.dao.CrudDao;
import by.verdeth.models.Book;

import java.util.List;

public interface BookDao extends CrudDao<Book> {
    List<Book> findAllSortPopular(Integer var1, Integer var2);

    List<Book> findAllSortYear(Integer var1, Integer var2);

    List<Book> findAllSortDateAdd(Integer var1, Integer var2);

    List<Book> findSubstring(String var1);

    List<Book> findAllSortPopular(Integer var1);

    List<Book> findAllSortYear(Integer var1);

    List<Book> findAllSortDateAdd(Integer var1);

    List<Book> findAllSortPopular();

    List<Book> findAllSortYear();

    List<Book> findAllSortDateAdd();

    List<Book> findAllByIdAuthor(Integer var1);

    List<Book> findAllByIdGenre(Integer var1);

    List<Book> findAllByIdSeria(Integer var1);

}
