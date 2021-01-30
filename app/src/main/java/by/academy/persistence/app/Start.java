package by.academy.persistence.app;

import by.academy.persistence.app.repositories.EntityManagerHelper;
import by.academy.persistence.model.City;
import by.academy.persistence.model.Department;
import by.academy.persistence.model.Employee;
import by.academy.persistence.model.Title;
import by.academy.persistence.model.examples.AudioSystem;
import by.academy.persistence.model.examples.Car;
import by.academy.persistence.model.examples.Engine;
import by.academy.persistence.model.examples.Product;
import by.academy.persistence.model.examples.ProductType;
import by.academy.persistence.model.examples.Report;
import by.academy.persistence.model.examples.ReportKey;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

public class Start {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        EntityManagerHelper helper = EntityManagerHelper.getInstance();

        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();

        em.remove(em.find(ProductType.class, 4L));

//        Product apple;
//        Product asus;
//        ProductType comp = ProductType.builder()
//                .name("comp")
//                .products(Set.of(
//                        apple = Product.builder()
//                                .name("Apple")
//                                .cost(2000)
//                                .build(),
//                        asus = Product.builder()
//                                .name("Asus")
//                                .cost(1500)
//                                .build()
//                ))
//                .build();
//        apple.setProductType(comp);
//        asus.setProductType(comp);
//
//        em.persist(comp);


//
//                Car bmw = em.find(Car.class, 1L);
//        bmw.setAudioSystem(AudioSystem.builder()
//                .musicPower(300)
//                .musicName("Sony")
//                .speakers(6)
//                .build());
//        em.persist(bmw);


//        Car bmw = em.find(Car.class, 1L);
//        bmw.setEngine(Engine.DIESEL);
//        em.persist(bmw);


//        Report mfn = Report.builder()
//                .name("Налоги")
//                .type("Штраф")
//                .recipient("MFN")
//                .build();
//
//        Report gai = Report.builder()
//                .name("Оплата")
//                .type("Штраф")
//                .recipient("GAI")
//                .build();
//
//        em.persist(mfn);
//        em.persist(gai);

//        printWithKey(em.find(Report.class, new ReportKey("Налоги", "Штраф")));



//        Car bmw = Car.builder()
//                .model("BMW")
//                .releaseDate(Date.from(LocalDate.of(2000, 11, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()))
//                .build();
//
//        Car lada = Car.builder()
//                .model("Lada")
//                .releaseDate(Date.from(LocalDate.of(20010, 5, 20).atStartOfDay(ZoneId.systemDefault()).toInstant()))
//                .build();
//        em.persist(bmw);
//        em.persist(lada);




//        Employee entity = em.find(Employee.class, 1);
//        System.out.println("!!! " + employee);
//        Title entity = em.find(Title.class, 3);
//
//        Title entity = new Title()
//                .withName("Директор");

//        City entity = new City()
//                .withName("Могилёв");
//
//        em.persist(entity);
//        printWithKey(entity);

        trx.commit();
        em.close();
    }

    private static void printWithKey(Object obj) {
        System.out.println("!!!" + obj);
    }
}
