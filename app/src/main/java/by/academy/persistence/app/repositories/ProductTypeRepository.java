package by.academy.persistence.app.repositories;

import by.academy.persistence.model.examples.Product;
import by.academy.persistence.model.examples.ProductType;

import javax.persistence.EntityManager;
import java.util.Set;

public class ProductTypeRepository {
    EntityManagerHelper helper = EntityManagerHelper.getInstance();



    public ProductType find(long id) {
        ProductType result = null;
        EntityManager em = helper.getEntityManager();
        em.getTransaction().begin();

        result = em.find(ProductType.class, id);

        em.getTransaction().commit();
        em.close();
        return result;
    }

}
