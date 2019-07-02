package by.verdeth.servlets;

import by.verdeth.dao.genreDao.GenreDao;
import by.verdeth.dao.genreDao.GenreDaoJdbcImpl;
import by.verdeth.dao.seriaDao.SeriaDao;
import by.verdeth.dao.seriaDao.SeriaDaoJdbcImpl;
import by.verdeth.helpers.CreateDataSource;
import by.verdeth.models.Genre;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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

    private GenreDao genreDao;
    private SeriaDao seriaDao;

    @Override
    public void init() throws ServletException {
        //connect database

        DriverManagerDataSource dataSource;

        try {
            //CreateDataSource createDataSource = new CreateDataSource();
            dataSource =  CreateDataSource.getInstance().getDriverManagerDataSource();

            genreDao = new GenreDaoJdbcImpl(dataSource);
            seriaDao = new SeriaDaoJdbcImpl(dataSource);
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

        HashMap<String, Integer> seriasAndBooks = seriaDao.findAllSeriasAndCountBooks();

        req.setAttribute("seriasAndBooksFromServer", seriasAndBooks);

        //show jsp
        req.getServletContext().getRequestDispatcher("/jsp/serias.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
