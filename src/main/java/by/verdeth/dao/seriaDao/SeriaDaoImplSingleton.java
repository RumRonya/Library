package by.verdeth.dao.seriaDao;

import by.verdeth.helpers.CreateDataSource;

public class SeriaDaoImplSingleton {

    private static SeriaDaoImplSingleton instance;
    private SeriaDao seriaDao;

    private SeriaDaoImplSingleton() {
        seriaDao = new SeriaDaoJdbcImpl(CreateDataSource.getInstance().getDriverManagerDataSource());
    }

    public static SeriaDaoImplSingleton getInstance() {
        if (instance==null)
        {
            instance = new SeriaDaoImplSingleton();
        }
        return instance;
    }

    public SeriaDao getSeriaDao() {
        return seriaDao;
    }
}
