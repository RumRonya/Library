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


@WebServlet ("/change")
public class ChangeServlet extends HttpServlet {

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

        int i = 0;

        //show jsp
        req.getServletContext().getRequestDispatcher("/jsp/change.jsp").forward(req,resp);
    }
}
