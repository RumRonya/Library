package by.verdeth.servlets;

import by.verdeth.dao.genreDao.GenreDaoImplSingleton;
import by.verdeth.dao.seriaDao.SeriaDaoImplSingleton;
import by.verdeth.models.Genre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/serias")
public class SeriasServlet extends HttpServlet {

//    private GenreDao genreDao;
//    private SeriaDao seriaDao;

    @Override
    public void init(){
//        //connect database
//
//        DriverManagerDataSource dataSource;
//
//        try {
//            //CreateDataSource createDataSource = new CreateDataSource();
//            dataSource =  CreateDataSource.getInstance().getDriverManagerDataSource();
//
//            genreDao = new GenreDaoJdbcImpl(dataSource);
//            seriaDao = new SeriaDaoJdbcImpl(dataSource);
//        }
//        catch (Exception ex)
//        {
//            throw new IllegalStateException(ex);
//        }
    }

    //get-method for url-serias
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //give list of genres from database
        List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();

        //set attribute for jsp
        req.setAttribute("genresFromServer", genres);

        HashMap<String, Integer> seriasAndBooks = SeriaDaoImplSingleton.getInstance().getSeriaDao().findAllSeriasAndCountBooks();

        req.setAttribute("seriasAndBooksFromServer", seriasAndBooks);

        //show jsp
        req.getServletContext().getRequestDispatcher("/jsp/serias.jsp").forward(req,resp);
    }



}
