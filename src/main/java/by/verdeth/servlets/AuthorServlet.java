package by.verdeth.servlets;

import by.verdeth.dao.authorDao.AuthorDao;
import by.verdeth.dao.authorDao.AuthorDaoJdbcImpl;
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
import java.util.List;


@WebServlet ("/author")
public class AuthorServlet extends HttpServlet {

    //private GenreDao genreDao;
    //private AuthorDao authorDao;
    //private BookDao bookDao;


    @Override
    public void init() throws ServletException {

     try
     {
        //DriverManagerDataSource dataSource;

         //CreateDataSource createDataSource = new CreateDataSource();
        // dataSource =  CreateDataSource.getInstance().getDriverManagerDataSource();

        //authorDao = new AuthorDaoJdbcImpl(dataSource);
       // genreDao = new GenreDaoJdbcImpl(dataSource);
       // bookDao = new BookDaoJdbcImpl(dataSource);
    }
    catch (Exception ex)
    {
        throw new IllegalStateException(ex);
    }
}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id")== null)
        {
            req.getServletContext().getRequestDispatcher("/about").forward(req,resp);
        }
        else
        {
            Integer idAuthor = Integer.valueOf(req.getParameter("id"));

            List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();
//                    genreDao.findAll();
            req.setAttribute("genresFromServer", genres);

            List<Book> books = BookDaoImplSingleton.getInstance().getBookDao().findAllByIdAuthor(idAuthor);
               //     bookDao.findAllByIdAuthor(idAuthor);

            req.setAttribute("booksAuthorFromServer", books);

            req.getServletContext().getRequestDispatcher("/jsp/author.jsp").forward(req,resp);

        }
    }

}
