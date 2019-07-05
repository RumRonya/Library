package by.verdeth.dao.bookDao;

import by.verdeth.dao.authorDao.AuthorDao;
import by.verdeth.dao.authorDao.AuthorDaoImplSingleton;
import by.verdeth.dao.authorDao.AuthorDaoJdbcImpl;
import by.verdeth.dao.genreDao.GenreDao;
import by.verdeth.dao.genreDao.GenreDaoImplSingleton;
import by.verdeth.dao.genreDao.GenreDaoJdbcImpl;
import by.verdeth.dao.seriaDao.SeriaDao;
import by.verdeth.dao.seriaDao.SeriaDaoImplSingleton;
import by.verdeth.dao.seriaDao.SeriaDaoJdbcImpl;
import by.verdeth.models.Author;
import by.verdeth.models.Book;
import by.verdeth.models.Genre;
import by.verdeth.models.Seria;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoJdbcImpl implements BookDao {
    Connection connection;
    DataSource dataSource;
    private final String SQL_SELECT_ALL = "SELECT * FROM book";
    private final String SQL_SELECT_ALL_FULL = "SELECT book.*, author.id_author as author_id, author.name_author FROM book JOIN author ON book.id_author = author.id_author";
    private final String SQL_SELECT_BY_ID_1 = "SELECT book.*, author.id_author as author_id, author.name_author FROM book JOIN author ON book.id_author = author.id_author WHERE id_book =  ?";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM book WHERE id_book = ?";
    private final String SQL_SELECT_ALL_SORT_BY_POPULAR = "SELECT * FROM book ORDER BY popular DESC LIMIT ? OFFSET ?";
    private final String SQL_SELECT_ALL_SORT_BY_YEAR = "SELECT * FROM book ORDER BY year_book DESC LIMIT ? OFFSET ?";
    private final String SQL_SELECT_ALL_SORT_BY_ADD = "SELECT * FROM book ORDER BY id_book DESC LIMIT ? OFFSET ?";
    private final String SQL_ALL_BY_STRING_SEARCH = "SELECT * FROM book WHERE LOWER(name_book) LIKE '%'?'%' OR LOWER(summary) LIKE '%'?'%'";
    private final String SQL_SELECT_ALL_BY_ID_AUTHOR = "SELECT * FROM book WHERE id_author = ?";
    private final String SQL_SELECT_ALL_BY_ID_GENRE = "SELECT book.*, genre_of_book.id_book AS book_id FROM book JOIN genre_of_book ON book.id_book = genre_of_book.id_book WHERE genre_of_book.id_genre = ?";
    private final String SQL_SELECT_ALL_BY_ID_SERIA = "SELECT * FROM book WHERE id_seria = ?";
    private final String SQL_SET_POPULAR = "UPDATE book SET popular = popular + 1 WHERE id_book = ?";

    public BookDaoJdbcImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
            this.dataSource = dataSource;
        } catch (Exception var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public List<Book> findAllSortPopular(Integer begin, Integer size) {
        try {
            List<Book> books = new ArrayList();
            PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ALL_SORT_BY_POPULAR);
            statement.setInt(1, size);
            statement.setInt(2, begin);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Book book = this.convertRowResultSetToBook(resultSet);
                books.add(book);
            }

            return books;
        } catch (Exception var7) {
            throw new IllegalArgumentException(var7);
        }
    }

    public List<Book> findAllSortYear(Integer begin, Integer size) {
        try {
            List<Book> books = new ArrayList();
            PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ALL_SORT_BY_YEAR);
            statement.setInt(1, size);
            statement.setInt(2, begin);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Book book = this.convertRowResultSetToBook(resultSet);
                books.add(book);
            }

            return books;
        } catch (Exception var7) {
            throw new IllegalArgumentException(var7);
        }
    }

    public List<Book> findAllSortDateAdd(Integer begin, Integer size) {
        try {
            List<Book> books = new ArrayList();
            PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ALL_SORT_BY_ADD);
            statement.setInt(1, size);
            statement.setInt(2, begin);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Book book = this.convertRowResultSetToBook(resultSet);
                books.add(book);
            }

            return books;
        } catch (Exception var7) {
            throw new IllegalArgumentException(var7);
        }
    }

    public List<Book> findSubstring(String search) {
        try {
            List<Book> books = new ArrayList();
            Statement statement = this.connection.createStatement();
            String SQL_String = "SELECT * FROM book WHERE LOWER(name_book) LIKE '%" + search + "%' OR LOWER(summary) LIKE '%" + search + "%'";
            PreparedStatement statement_old = this.connection.prepareStatement("SELECT * FROM book WHERE LOWER(name_book) LIKE '%'?'%' OR LOWER(summary) LIKE '%'?'%'");
            ResultSet resultSet = statement.executeQuery(SQL_String);

            while(resultSet.next()) {
                Book book = this.convertRowResultSetToBook(resultSet);
                books.add(book);
            }

            return books;
        } catch (Exception var9) {
            throw new IllegalArgumentException(var9);
        }
    }

    public List<Book> findAllSortPopular(Integer size) {
        return this.findAllSortPopular(0, size);
    }

    public List<Book> findAllSortYear(Integer size) {
        return this.findAllSortYear(0, size);
    }

    public List<Book> findAllSortDateAdd(Integer size) {
        return this.findAllSortDateAdd(0, size);
    }

    public List<Book> findAllSortPopular() {
        return this.findAllSortPopular(0, 50);
    }

    public List<Book> findAllSortYear() {
        return this.findAllSortYear(0, 50);
    }

    public List<Book> findAllSortDateAdd() {
        return this.findAllSortPopular(0, 50);
    }

    private Book convertRowResultSetToBook(ResultSet resultSet) {
        try {
            Book book = null;
            Integer idBook = resultSet.getInt("id_book");
            String nameBook = resultSet.getString("name_book");
            String summary = resultSet.getString("summary");
            String URLPoster = resultSet.getString("url_poster");
            Short year = resultSet.getShort("year_book");
            Integer popular = resultSet.getInt("popular");
            Integer idAuthor = resultSet.getInt("id_author");
            Optional<Author> author = AuthorDaoImplSingleton.getInstance().getAuthorDao().find(idAuthor);
            Integer idSeria = resultSet.getInt("id_seria");
            Optional<Seria> seria = SeriaDaoImplSingleton.getInstance().getSeriaDao().find(idSeria);
            List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAllByIdBook(idBook);
            book = new Book(idBook, nameBook, summary, year, URLPoster, popular, author.isPresent() ? author.get() : null, seria.isPresent() ? seria.get() : null, genres);
            return book;
        } catch (Exception var18) {
            throw new IllegalArgumentException(var18);
        }
    }

    public List<Book> findAllByIdAuthor(Integer idAuthor) {
        try {
            List<Book> books = new ArrayList();
            PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ALL_BY_ID_AUTHOR);
            statement.setInt(1, idAuthor);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Book book = this.convertRowResultSetToBook(resultSet);
                books.add(book);
            }

            return books;
        } catch (Exception var6) {
            throw new IllegalArgumentException(var6);
        }
    }

    public List<Book> findAllByIdGenre(Integer idGenre) {
        try {
            List<Book> books = new ArrayList();
            PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ALL_BY_ID_GENRE);
            statement.setInt(1, idGenre);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Book book = this.convertRowResultSetToBook(resultSet);
                books.add(book);
            }

            return books;
        } catch (Exception var6) {
            throw new IllegalArgumentException(var6);
        }
    }

    public List<Book> findAllByIdSeria(Integer idSeria) {
        try {
            List<Book> books = new ArrayList();
            PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ALL_BY_ID_SERIA);
            statement.setInt(1, idSeria);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Book book = this.convertRowResultSetToBook(resultSet);
                books.add(book);
            }

            return books;
        } catch (Exception var6) {
            throw new IllegalArgumentException(var6);
        }
    }

    @Override
    public void incPopular(Integer id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL_SET_POPULAR);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (Exception ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }

    public Optional<Book> find(Integer id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = this.convertRowResultSetToBook(resultSet);
                return Optional.of(book);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return Optional.empty();
    }

    public void save(Book model) {
    }

    public void update(Book model) {
    }

    public void delete(Integer id) {
    }

    public List<Book> findAll() {
        try {
            List<Book> books = new ArrayList();
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while(resultSet.next()) {
                Book book = this.convertRowResultSetToBook(resultSet);
                books.add(book);
            }

            return books;
        } catch (Exception var5) {
            throw new IllegalArgumentException(var5);
        }
    }
}
