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

@WebServlet ("/index")
public class IndexServlet extends HttpServlet {

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //give list of genres from database
        List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();

        //set attribute for jsp
        req.setAttribute("genresFromServer", genres);


        //get 6 the most popular books from db
        List<Book> booksPopular = BookDaoImplSingleton.getInstance().getBookDao().findAllSortPopular(0,6);
        req.setAttribute("booksPopularFromServer", booksPopular);

        //get 6 last addded books from db
        List<Book> booksAdd = BookDaoImplSingleton.getInstance().getBookDao().findAllSortDateAdd(0,6);
        req.setAttribute("booksAddFromServer", booksAdd);

        //get 6 the most newest books from db
        List<Book> booksNew = BookDaoImplSingleton.getInstance().getBookDao().findAllSortYear(0,6);
        req.setAttribute("booksNewFromServer", booksNew);

        //show jsp
        req.getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(req,resp);
    }
}
