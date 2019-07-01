package by.verdeth.dao.authorDao;

import by.verdeth.dao.CrudDao;
import by.verdeth.models.Author;
import by.verdeth.models.Book;

import java.util.HashMap;
import java.util.List;

public interface AuthorDao extends CrudDao<Author> {
    HashMap<String, Integer> findAllAuthorsAndCountBooks();
    List<Book> findAllBookByIdAuthor(Integer id);
    List<Author> findAllAuthorsWithBooks();
}
