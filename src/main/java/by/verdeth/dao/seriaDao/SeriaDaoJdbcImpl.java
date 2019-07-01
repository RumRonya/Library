package by.verdeth.dao.seriaDao;

import by.verdeth.models.Seria;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class SeriaDaoJdbcImpl implements SeriaDao {

    private Connection connection;

    //language=SQL
    private String SQL_SELECT_ALL_WITH_COUNT_BOOKS = "SELECT name_seria, COUNT(id_book) as count_book " +
            "FROM seria LEFT JOIN Book\n" +
            "ON seria.id_seria = Book.id_seria\n" +
            "GROUP BY name_seria";

    public SeriaDaoJdbcImpl (DataSource dataSource)
    {
        try {
            connection = dataSource.getConnection();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public HashMap<String, Integer> findAllSeriasAndCountBooks() {
        try {

            HashMap<String, Integer> seriasAndCountBooks = new HashMap<>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_WITH_COUNT_BOOKS);

            while (resultSet.next()) {
                Integer count = resultSet.getInt("count_book");
                String nameSeria = resultSet.getString("name_seria");

                seriasAndCountBooks.put(nameSeria, count);
            }

            return seriasAndCountBooks;

        }
        catch (Exception ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }

    //language=SQL
    final private String SQL_SELECT_BY_ID = "SELECT * FROM seria " +
                                            "WHERE id_seria = ?";

    @Override
    public Optional<Seria> find(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                String nameSeria = resultSet.getString("name_seria");

                Seria seria = new Seria(id, nameSeria);
                return  Optional.of(seria);
            }
            return Optional.empty();
        }
        catch (Exception ex)
        {
            throw  new IllegalArgumentException(ex);
        }
    }
    @Override
    public void save(Seria model) {

    }

    @Override
    public void update(Seria model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Seria> findAll() {
        return null;
    }
}
