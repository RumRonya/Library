package by.verdeth.dao.genreDao;

import by.verdeth.models.Author;
import by.verdeth.models.Book;
import by.verdeth.models.Genre;

import javax.sql.DataSource;
import javax.sql.rowset.Joinable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDaoJdbcImpl implements GenreDao {

    Connection connection;
    DataSource dataSource;

    //language=SQL
    final private String SQL_SELECT_ALL = "SELECT * FROM genre ORDER BY genre ASC";

    //language=SQL
    final private String SQL_SELECT_GENRE_BY_BOOK_ID = "SELECT genre.genre, genre.id_genre as genre_id " +
                                                        "FROM genre_of_book JOIN genre " +
                                                        "ON genre_of_book.id_genre = genre.id_genre " +
                                                        "WHERE id_book = ?";

    //language=SQL
    final private String SQL_SELECT_ALL_BOOKS_BY_ID_GENRE = "SELECT author.name_author, book.*, genre_of_book.id_book as book_id " +
                                                            "FROM genre_of_book JOIN book " +
                                                            "ON genre_of_book.id_book = book.id_book JOIN author " +
                                                            "ON book.id_author = author.id_author " +
                                                            "WHERE id_genre = ?";

    public GenreDaoJdbcImpl (DataSource dataSource)
    {
        try {
            int i = 0;
            connection = dataSource.getConnection();
            this.dataSource = dataSource;
            }
            catch (SQLException e) {
            throw new IllegalStateException(e);
            }
    }



    @Override
    public Optional<Genre> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Genre model) {

    }

    @Override
    public void update(Genre model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Genre> findAll() {
        try {
            List<Genre> genres = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next())
            {
                Integer id = resultSet.getInt("id_genre");
                String nameGenre = resultSet.getString("genre");

                Genre genre = new Genre(id, nameGenre);

                genres.add(genre);
            }

            return genres;
            }
        catch (Exception ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public List<Genre> findAllByIdBook(Integer id) {
        try {
            List<Genre> genres = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_GENRE_BY_BOOK_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                Integer idGenre = resultSet.getInt("genre_id");
                String nameGenre = resultSet.getString("genre");

                Genre genre = new Genre(idGenre, nameGenre);

                genres.add(genre);
            }

            return genres;
        }
        catch (Exception ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public List<Book> findAllBooksById(Integer idGenre)
    {
        try {
            List<Book> books = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_BOOKS_BY_ID_GENRE);
            statement.setInt(1, idGenre);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                Integer idBook = resultSet.getInt("id_book");
                String nameBook = resultSet.getString("name_book");
                String summary = resultSet.getString("summary");
                String URLPoster = resultSet.getString("url_poster");
                Short year = resultSet.getShort("year_book");

                Integer idAuthor = resultSet.getInt("id_author");
                String nameAuthor = resultSet.getString("name_author");
                Author author = new Author(idAuthor, nameAuthor);

                GenreDao genreDao = new GenreDaoJdbcImpl(dataSource);
                List<Genre> genres = genreDao.findAllByIdBook(idBook);

                Book book = new Book(idBook, nameBook, summary, year, URLPoster, author, genres);

                books.add(book);
            }

            return books;
        }
        catch (Exception ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }
}
