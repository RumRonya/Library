package by.verdeth.dao.userDao;

import by.verdeth.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


//implements for UserDao which uses JdbcTemplate
public class UserDaoJdbsTemolateImpl implements UserDao {

    private JdbcTemplate template;

    public UserDaoJdbsTemolateImpl(DataSource dataSource)
    {
        this.template = new JdbcTemplate(dataSource);

    }

    //language=SQL
    private String SQL_SELECT_BY_USER_NAME_AND_PASSWORD = "SELECT * FROM users WHERE user_name = ? AND user_password = ?";


    private RowMapper<User> userRowMapper = (resultSet, i) -> {
        return new User(resultSet.getInt("user_id"),
                resultSet.getString("user_name"),
                resultSet.getString("user_password"),
                resultSet.getShort("user_role"));

    };


    @Override
    public boolean isHasUser(String nameUser, String password) {
        List<User> users = template.query(SQL_SELECT_BY_USER_NAME_AND_PASSWORD, userRowMapper, nameUser, password);
        return (users!=null);
    }

    @Override
    public boolean isHasAdminitrator(String nameUser, String password) {
        List<User> users = template.query(SQL_SELECT_BY_USER_NAME_AND_PASSWORD, userRowMapper, nameUser, password);
        return (users!=null && users.get(0).getRoleUser()==0);
    }

    //language=SQL
    private String SQL_INSERT_INTO_USER = "INSERT INTO users (user_name, user_password, user_role) VALUES (?,?, 1)";


    @Override
    public boolean addUser(String nameUser, String password) {
        int i = template.update(SQL_INSERT_INTO_USER, nameUser, password);
        return (i!=0);
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
