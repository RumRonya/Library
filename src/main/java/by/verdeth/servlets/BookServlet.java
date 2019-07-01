package by.verdeth.servlets;

import by.verdeth.dao.bookDao.BookDao;
import by.verdeth.dao.bookDao.BookDaoJdbcImpl;
import by.verdeth.dao.genreDao.GenreDao;
import by.verdeth.dao.genreDao.GenreDaoJdbcImpl;
import by.verdeth.helpers.CreateDataSource;
import by.verdeth.models.Book;
import by.verdeth.models.Genre;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet ("/book")
public class BookServlet extends HttpServlet {

    private GenreDao genreDao;
    private BookDao bookDao;

    @Override
    public void init() throws ServletException {

        //connect database

        DriverManagerDataSource dataSource;

        try {
            CreateDataSource createDataSource = new CreateDataSource();
            dataSource =  createDataSource.getDriverManagerDataSource();

            genreDao = new GenreDaoJdbcImpl(dataSource);
            bookDao = new BookDaoJdbcImpl(dataSource);
        }
        catch (Exception ex)
        {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //give list of genres from database
        List<Genre> genres = genreDao.findAll();

        //set attribute for jsp
        req.setAttribute("genresFromServer", genres);

        if (req.getParameter("id")==null) {
            req.getServletContext().getRequestDispatcher("/books").forward(req,resp);
        }
        Integer id = Integer.valueOf(req.getParameter("id"));
        Book book = bookDao.find(id).get();

        req.setAttribute("bookFromServer", book);

        //show jsp
        req.getServletContext().getRequestDispatcher("/jsp/book.jsp").forward(req,resp);
    }
}
