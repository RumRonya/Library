package by.verdeth.dao.authorDao;

import by.verdeth.dao.CrudDao;
import by.verdeth.models.Author;
import by.verdeth.models.Book;

import java.util.HashMap;
import java.util.List;

//interface for DAOAuthor which extends CrudDAO and adds specific methods
public interface AuthorDao extends CrudDao<Author> {

    //it's old methods, it was replaced the method findAllAuthorsWithBooks
    HashMap<String, Integer> findAllAuthorsAndCountBooks();

    //return all books by author id
    List<Book> findAllBookByIdAuthor(Integer id);

    //return all authors with all books
    List<Author> findAllAuthorsWithBooks();
}
