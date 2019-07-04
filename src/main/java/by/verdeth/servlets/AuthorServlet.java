package by.verdeth.servlets;

import by.verdeth.dao.bookDao.BookDaoImplSingleton;
import by.verdeth.dao.genreDao.GenreDaoImplSingleton;
import by.verdeth.models.Book;
import by.verdeth.models.Genre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet ("/author")
public class AuthorServlet extends HttpServlet {

    @Override
    public void init(){

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

        Integer idAuthor = Integer.valueOf(req.getParameter("id"));

        List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();
        req.setAttribute("genresFromServer", genres);

        List<Book> books = BookDaoImplSingleton.getInstance().getBookDao().findAllByIdAuthor(idAuthor);
        req.setAttribute("booksAuthorFromServer", books);

        req.getServletContext().getRequestDispatcher("/jsp/author.jsp").forward(req,resp);

    }

}
