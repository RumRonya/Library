package by.verdeth.dao.genreDao;

import by.verdeth.dao.CrudDao;
import by.verdeth.models.Book;
import by.verdeth.models.Genre;

import java.util.List;

public interface GenreDao extends CrudDao<Genre> {
    List<Genre> findAllByIdBook(Integer id);
    List<Book> findAllBooksById(Integer idGenre);

}
