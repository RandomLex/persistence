package by.academy.persistence.app;

import by.academy.persistence.app.repositories.EntityManagerHelper;
import by.academy.persistence.model.City;
import by.academy.persistence.model.Department;
import by.academy.persistence.model.Employee;
import by.academy.persistence.model.Title;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Start {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        EntityManagerHelper helper = EntityManagerHelper.getInstance();

        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();

//        Employee employee = em.find(Employee.class, 1);
//        System.out.println("!!! " + employee);
//        Title entity = em.find(Title.class, 3);

        Title entity = new Title()
                .withName("Директор");

//        City entity = new City()
//                .withName("Могилёв");
//
        em.persist(entity);
        printWithKey(entity);

        trx.commit();
        em.close();
    }

    private static void printWithKey(Object obj) {
        System.out.println("!!!" + obj);
    }
}
