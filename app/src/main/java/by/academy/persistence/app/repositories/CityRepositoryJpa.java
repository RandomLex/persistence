package by.academy.persistence.app.repositories;

import by.academy.persistence.model.City;
import by.academy.persistence.model.Department;

import javax.persistence.TypedQuery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityRepositoryJpa extends AbstractRepositoryJpa<City> implements CityRepository {
    private static volatile CityRepositoryJpa instance;

    private CityRepositoryJpa() {
    }

    public static CityRepositoryJpa getInstance() {
        if (instance == null) {
            synchronized (CityRepositoryJpa.class) {
                if (instance == null) {
                    instance = new CityRepositoryJpa();
                }
            }
        }
        return instance;
    }

    @Override
    protected TypedQuery<City> findAllQuery() {
        return helper.getEntityManager()
                .createQuery("from City", City.class);
    }

    @Override
    protected TypedQuery<City> findOneQuery() {
        return helper.getEntityManager()
                .createQuery("from City where id = :id", City.class);
    }
}
