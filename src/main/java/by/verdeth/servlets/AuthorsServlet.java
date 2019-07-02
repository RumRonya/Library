package by.verdeth.servlets;

import by.verdeth.dao.authorDao.AuthorDaoImplSingleton;
import by.verdeth.dao.authorDao.AuthorDaoJdbcImpl;
import by.verdeth.dao.authorDao.AuthorDao;
import by.verdeth.dao.genreDao.GenreDao;
import by.verdeth.dao.genreDao.GenreDaoImplSingleton;
import by.verdeth.dao.genreDao.GenreDaoJdbcImpl;
import by.verdeth.helpers.CreateDataSource;
import by.verdeth.models.Author;
import by.verdeth.models.Genre;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

@WebServlet ("/authors")
public class AuthorsServlet extends HttpServlet {

    //private GenreDao genreDao;
    //private AuthorDao authorDao;

    @Override
    public void init() throws ServletException {

        //connect database

        //DriverManagerDataSource dataSource;

       // try {
            //CreateDataSource createDataSource = new CreateDataSource();
            //dataSource =  CreateDataSource.getInstance().getDriverManagerDataSource();

            //genreDao = new GenreDaoJdbcImpl(dataSource);
            //authorDao = new AuthorDaoJdbcImpl(dataSource);
       // }
       // catch (Exception ex)
       // {
       //     throw new IllegalStateException(ex);
       // }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //give list of genres from database
        List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();
        //        genreDao.findAll();

        //set attribute for jsp
        req.setAttribute("genresFromServer", genres);

        List<Author> authorsWithBooks = AuthorDaoImplSingleton.getInstance().getAuthorDao().findAllAuthorsWithBooks();

        //authorDao.findAllAuthorsWithBooks();

        req.setAttribute("authorsWithBooksFromServer", authorsWithBooks);

        //show jsp
        req.getServletContext().getRequestDispatcher("/jsp/authors.jsp").forward(req,resp);
    }
}
