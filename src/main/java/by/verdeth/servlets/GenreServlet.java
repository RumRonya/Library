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

//servlet for book of one genre
@WebServlet("/genre")
public class GenreServlet extends HttpServlet {

    @Override
    public void init(){
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

    //get method url 'genre'
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //for left bar
        List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();
        req.setAttribute("genresFromServer", genres);

        //if hasn't parameter 'id'
        if (req.getParameter("id")== null)
        {
            req.getServletContext().getRequestDispatcher("/index").forward(req,resp);
        }

        //get id genre
        Integer idGenre = Integer.valueOf(req.getParameter("id"));

        //get list books of genre
        List<Book> books = BookDaoImplSingleton.getInstance().getBookDao().findAllByIdGenre(idGenre);
        req.setAttribute("booksGenreFromServer", books);

        //show jsp
        req.getServletContext().getRequestDispatcher("/jsp/genre.jsp").forward(req,resp);

    }
}
