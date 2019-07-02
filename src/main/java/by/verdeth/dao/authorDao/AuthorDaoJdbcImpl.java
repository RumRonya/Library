package by.verdeth.dao.authorDao;

import by.verdeth.dao.bookDao.BookDao;
import by.verdeth.dao.bookDao.BookDaoJdbcImpl;
import by.verdeth.dao.genreDao.GenreDao;
import by.verdeth.dao.genreDao.GenreDaoJdbcImpl;
import by.verdeth.models.Author;
import by.verdeth.models.Book;
import by.verdeth.models.Genre;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


//implements AuthorDAO and uses JDBC to connect to a database
public class AuthorDaoJdbcImpl implements AuthorDao {

    private Connection connection;
    private DataSource dataSource;


    //final Strings SQL queries
    //query for select all authors with count of books
    //language=SQL
    final private String SQL_SELECT_ALL_WITH_COUNT_BOOKS = "SELECT name_author, COUNT(id_book) as count_book " +
                                                    "FROM Author LEFT JOIN Book\n" +
                                                    "ON Author.id_author = Book.id_author\n" +
                                                    "GROUP BY name_author";

    //query for select all books by id author
    //language=SQL
    final private String SQL_SELECT_ALL_BOOKS_BY_ID_AUTHOR = "SELECT author.name_author, book.* " +
                                                            "FROM author JOIN book " +
                                                            "ON author.id_author = book.id_author " +
                                                            "WHERE author.id_author = ?";

    //query for select author by id author
    //language=SQL
    final private String SQL_SELECT_BY_ID = "SELECT * FROM author " +
                                            "WHERE id_author = ?";

    //query for select all authors
    //language=SQL
    final private String SQL_SELECT_ALL = "SELECT * FROM author";


    //constructor
    //initializes private variables
    public AuthorDaoJdbcImpl (DataSource dataSource)
    {
        try {
            connection = dataSource.getConnection();
            this.dataSource = dataSource;
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    //override methods

    @Override
    public HashMap<String, Integer> findAllAuthorsAndCountBooks() {
        try {

            HashMap<String, Integer> authorsAndCountBooks = new HashMap<>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_WITH_COUNT_BOOKS);

            while (resultSet.next()) {
                Integer count = resultSet.getInt("count_book");
                String nameAuthor = resultSet.getString("name_author");

                authorsAndCountBooks.put(nameAuthor, count);
            }

            return authorsAndCountBooks;

        }
        catch (Exception ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public List<Book> findAllBookByIdAuthor(Integer id) {
        try {
            List<Book> books = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_BOOKS_BY_ID_AUTHOR);
            statement.setInt(1, id);
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

    @Override
    public List<Author> findAllAuthorsWithBooks() {
        try
        {
            List<Author> authors;
            authors = findAll();

            for (Author author : authors)
            {
                List<Book> books = findAllBookByIdAuthor(author.getIdAuthor());
                author.setBooks(books);
            }

            return authors;
        }
        catch (Exception ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public Optional<Author> find(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                String nameAuthor = resultSet.getString("name_author");

                Author author = new Author(id, nameAuthor);
                return  Optional.of(author);
            }
            return Optional.empty();
        }
        catch (Exception ex)
        {
            throw  new IllegalArgumentException(ex);
        }
    }

    @Override
    public List<Author> findAll()
    {
        try
        {
          List<Author> authors = new ArrayList<>();

          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
          while (resultSet.next())
          {
              Author author;
              Integer id = resultSet.getInt("id_author");
              String nameAuthor = resultSet.getString("name_author");

              author = new Author(id, nameAuthor);
              authors.add(author);
          }

          return  authors;
        }
        catch (Exception ex)
        {
            throw new IllegalArgumentException(ex);
        }

    }

    @Override
    public void save(Author model) {
    }

    @Override
    public void update(Author model) {
    }

    @Override
    public void delete(Integer id) {
    }
}
