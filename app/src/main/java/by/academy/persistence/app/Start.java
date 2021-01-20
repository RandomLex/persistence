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
//        Configuration cfg = new Configuration().configure();
//        SessionFactory sessionFactory = cfg.buildSessionFactory();

        EntityManagerHelper helper = EntityManagerHelper.getInstance();

        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();

        Employee employee = em.find(Employee.class, 1);
        System.out.println("!!! " + employee);
//        Title manager = em.find(Title.class, 3);
//        Title manager = new Title()
//                .withId(11)
//                .withName("Менеджер");
//        em.persist(manager);
//
//        System.out.println(manager);

        trx.commit();
        em.close();
    }
}
