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


@WebServlet ("/book")
public class BookServlet extends HttpServlet {

    @Override
    public void init(){
        //connect database

        //DriverManagerDataSource dataSource;

        //try {
            //CreateDataSource createDataSource = new CreateDataSource();
         //   dataSource =  CreateDataSource.getInstance().getDriverManagerDataSource();

            //genreDao = new GenreDaoJdbcImpl(dataSource);
            //bookDao = new BookDaoJdbcImpl(dataSource);
        //}
        //catch (Exception ex)
        //{
        //    throw new IllegalStateException(ex);
        //}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //give list of genres from database
        List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();
        //set attribute for jsp
        req.setAttribute("genresFromServer", genres);

        //if hasn't parameter 'id'
        if (req.getParameter("id")==null)
        {
            req.getServletContext().getRequestDispatcher("/books").forward(req,resp);
        }

        //get book by id from db
        Integer id = Integer.valueOf(req.getParameter("id"));
        Book book = BookDaoImplSingleton.getInstance().getBookDao().find(id).get();
        req.setAttribute("bookFromServer", book);

        //show jsp
        req.getServletContext().getRequestDispatcher("/jsp/book.jsp").forward(req,resp);
    }
}
