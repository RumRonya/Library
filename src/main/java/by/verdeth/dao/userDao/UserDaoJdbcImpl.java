package by.verdeth.dao.userDao;

import by.verdeth.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbcImpl implements UserDao {


    private Connection connection;

    //language=SQL
    private String SQL_SELECT_ALL_WITH_COUNT_BOOKS = "SELECT name_seria";

    public UserDaoJdbcImpl (DataSource dataSource)
    {
        try {
            this.connection = dataSource.getConnection();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    //language=SQL
    private String SQL_SELECT_BY_USER_NAME_AND_PASSWORD = "SELECT * FROM users WHERE user_name = ? AND user_password = ?";

    @Override
    public boolean isHasUser(String nameUser, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_USER_NAME_AND_PASSWORD);
            statement.setString(1, nameUser);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                {
                    return true;
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isHasAdminitrator(String nameUser, String password) {
        return false;
    }

    //language=SQL
    private String SQL_INSERT_INTO_USER = "INSERT INTO users (user_name, user_password, user_role) VALUES (?,?, 1)";


    @Override
    public boolean addUser(String nameUser, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_USER);

            statement.setString(1, nameUser);
            statement.setString(2, password);

            return (statement.executeUpdate()==1);

        } catch (SQLException e) {
            e.printStackTrace();
            }
        return false;
    }

    @Override
    public Optional<User> find(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
