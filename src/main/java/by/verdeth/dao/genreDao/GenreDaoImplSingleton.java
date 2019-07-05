package by.verdeth.dao.genreDao;

import by.verdeth.helpers.CreateDataSource;

public class GenreDaoImplSingleton {

    private static GenreDaoImplSingleton instance;
    private GenreDao genreDao;

    private GenreDaoImplSingleton()
    {
        genreDao = new GenreDaoJdbcImpl(CreateDataSource.getInstance().getDriverManagerDataSource());
    }

    public static GenreDaoImplSingleton getInstance() {
        int i =0;
        if (instance==null)
        {
            int j = 0;
            instance = new GenreDaoImplSingleton();
        }
        return instance;
    }

    public GenreDao getGenreDao() {
        return genreDao;
    }
}
