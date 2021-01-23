package by.academy.persistence.app.repositories;

import by.academy.persistence.model.AbstractEntity;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbstractRepositoryJpa<T extends AbstractEntity> implements Repository<T> {
    private static final String ID = "id";
    protected final EntityManagerHelper helper = EntityManagerHelper.getInstance();

    protected abstract TypedQuery<T> findAllQuery();

    protected abstract TypedQuery<T> findOneQuery();

    @Override
    public List<T> findAll() {
        List<T> result;
        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        result = findAllQuery().getResultList();
        trx.commit();
        em.close();
        return result;
    }

    @Override
    public Optional<T> find(Integer id) {
        Optional<T> result;
        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        result = Optional.ofNullable(findOneQuery()
                .setParameter(ID, id)
                .getSingleResult());
        trx.commit();
        em.close();
        return result;
    }

    @Override
    public T save(T entity) {
        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        em.persist(entity);
        trx.commit();
        em.close();
        return entity;
    }

    @Override
    public Optional<T> remove(Integer id) {
        Optional<T> result;
        EntityManager em = helper.getEntityManager();
        em.getTransaction().begin();
        result = find(id);
        result.ifPresent(em::remove);
        em.getTransaction().commit();
        em.close();
        return result;
    }
}
