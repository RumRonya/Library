package by.verdeth.helpers;

import by.verdeth.dao.genreDao.GenreDaoJdbcImpl;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//class for create datasource on based properties file
public class CreateDataSource {

    private DriverManagerDataSource driverManagerDataSource;

    //property get
    public DriverManagerDataSource getDriverManagerDataSource() {
        return driverManagerDataSource;
    }

    public CreateDataSource()
    {
        this.driverManagerDataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\MSI\\IdeaProjects\\Library\\target\\classes\\db.properties"));
           // properties.load(new FileInputStream("/target/Library-0.1/WEB-INF/classes/db.properties"));
            //target/Library-0.1/WEB-INF/classes/db.properties

            //get parameters of connection from properties file
            String dbUrl = properties.getProperty("db.url");
            String dbUser = properties.getProperty("db.user");
            String dbPassword = properties.getProperty("db.password");
            String dbDriverClassName = properties.getProperty("db.driverClassName");

            //set fields driverManagerDataSource
            this.driverManagerDataSource.setUrl(dbUrl);
            this.driverManagerDataSource.setUsername(dbUser);
            this.driverManagerDataSource.setPassword(dbPassword);
            this.driverManagerDataSource.setDriverClassName(dbDriverClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
