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


@WebServlet ("/search")
public class SearchServlet extends HttpServlet {


    @Override
    public void init(){
//
//        //connect database
//
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

    //post-method url 'search'
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //give list of genres from database
        List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();

        // set attribute for jsp
        req.setAttribute("genresFromServer", genres);

        List<Book> books = null;
        //checking parameter ''search'
        if (req.getParameter("search")!=null)
        {
            String stSearch = req.getParameter("search");

            books = BookDaoImplSingleton.getInstance().getBookDao().findSubstring(stSearch);
        }
        req.setAttribute("booksFromServer", books);

        req.getServletContext().getRequestDispatcher("/jsp/search.jsp").forward(req,resp);

    }


    //get-method url 'search'
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //give list of genres from database
        List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();

        //set attribute for jsp
        req.setAttribute("genresFromServer", genres);

        req.setAttribute("booksFromServer", null);

        //show jsp
        req.getServletContext().getRequestDispatcher("/jsp/search.jsp").forward(req,resp);
    }
}
