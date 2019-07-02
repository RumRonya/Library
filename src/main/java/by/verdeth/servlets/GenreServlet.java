package by.verdeth.servlets;

import by.verdeth.dao.bookDao.BookDao;
import by.verdeth.dao.bookDao.BookDaoImplSingleton;
import by.verdeth.dao.bookDao.BookDaoJdbcImpl;
import by.verdeth.dao.genreDao.GenreDao;
import by.verdeth.dao.genreDao.GenreDaoImplSingleton;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/genre")
public class GenreServlet extends HttpServlet {


//    private BookDao bookDao;
//    private GenreDao genreDao;

    @Override
    public void init() throws ServletException {

        //connect database
//        DriverManagerDataSource dataSource;
//
//        try {
//            //CreateDataSource createDataSource = new CreateDataSource();
//            dataSource =  CreateDataSource.getInstance().getDriverManagerDataSource();
//
//            genreDao = new GenreDaoJdbcImpl(dataSource);
//            bookDao = new BookDaoJdbcImpl(dataSource);
//        }
//        catch (Exception ex)
//        {
//            throw new IllegalStateException(ex);
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id")== null)
        {
            req.getServletContext().getRequestDispatcher("/about").forward(req,resp);
        }
        else
        {
            Integer idGenre = Integer.valueOf(req.getParameter("id"));

            List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();
            //        genreDao.findAll();
            req.setAttribute("genresFromServer", genres);

            List<Book> books = BookDaoImplSingleton.getInstance().getBookDao().findAllByIdGenre(idGenre);
                    //bookDao.findAllByIdGenre(idGenre);
            req.setAttribute("booksGenreFromServer", books);

            req.getServletContext().getRequestDispatcher("/jsp/genre.jsp").forward(req,resp);

        }


    }
}
