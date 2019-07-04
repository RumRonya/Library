package by.verdeth.servlets;

import by.verdeth.dao.genreDao.GenreDaoImplSingleton;
import by.verdeth.models.Genre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//servlet for begin page, if user log in
@WebServlet ("/about")
public class AboutServlet extends HttpServlet {

    @Override
    public void init(){

        //connect database
        //DriverManagerDataSource dataSource;

       // try {
            //get datasource //MAKE THIS SINGLETON!
            //CreateDataSource createDataSource = new CreateDataSource();
            //dataSource =  CreateDataSource.getInstance().getDriverManagerDataSource();

            //creaate impl of genredao
            //genreDao = new GenreDaoJdbcImpl(dataSource);
        //}
       // catch (Exception ex)
       // {
       //     throw new IllegalStateException(ex);
       // }

        //it's old unit, part of this is class CreateDataSource

//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        Properties properties = new Properties();
//
//        try {
//            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
//
//            String dbUrl = properties.getProperty("db.url");
//            String dbUser = properties.getProperty("db.user");
//            String dbPassword = properties.getProperty("db.password");
//            String dbDriverClassName = properties.getProperty("db.driverClassName");
//
//            dataSource.setUrl(dbUrl);
//            dataSource.setUsername(dbUser);
//            dataSource.setPassword(dbPassword);
//            dataSource.setDriverClassName(dbDriverClassName);
//
//            genreDao = new GenreDaoJdbcImpl(dataSource);
//        }
//        catch (Exception ex)
//        {
//            throw new IllegalStateException(ex);
//        }
    }

    //doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get list of genres from database
        List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();

        //set attribute for jsp
        req.setAttribute("genresFromServer", genres);

        //show jsp
        req.getServletContext().getRequestDispatcher("/jsp/about.jsp").forward(req,resp);
    }
}
