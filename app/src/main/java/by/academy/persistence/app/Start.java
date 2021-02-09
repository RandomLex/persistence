package by.academy.persistence.app;

import by.academy.persistence.app.repositories.EntityManagerHelper;
import by.academy.persistence.model.hierarchy.table.Animal;
import by.academy.persistence.model.hierarchy.table.Bird;
import by.academy.persistence.model.hierarchy.table.Fish;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        EntityManagerHelper helper = EntityManagerHelper.getInstance();

        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();

        List<Animal> animals = em.createQuery("from Animal ", Animal.class).getResultList();
        animals.forEach(Start::printWithKey);

//        Animal it = Animal.builder()
//                .origin("Нечто")
//                .build();
//        em.persist(it);
//        Bird eagle = Bird.builder()
//                .origin("Eagle")
//                .flyable(true)
//                .growing("Nested")
//                .build();
//
//        Fish shark = Fish.builder()
//                .origin("Shark")
//                .poison(false)
//                .skeleton("Хрящевые")
//                .build();
////
//        em.persist(eagle);
//        em.persist(shark);



//        Post marryChristmas = em.find(Post.class, 6L);
//        printWithKey(marryChristmas);

//        Post newYear = em.find(Post.class, 5L);
//        newYear.getTags().clear();

//
//        em.merge(newYear);
//
//        em.remove(newYear);


//        em.remove(em.find(Post.class, 3L));
//        printWithKey(post);

//        Post newYearPost = Post.builder()
//                .name("Happy New Year")
//                .tags(new HashSet<>())
//                .build();
//
//        Post marryChristmas = Post.builder()
//                .name("Marry Christmas")
//                .tags(new HashSet<>())
//                .build();
//
//        Tag holiday = Tag.builder()
//                .posts(new HashSet<>())
//                .name("Holyday")
//                .build();
//
//        Tag favorite = Tag.builder()
//                .name("Favorite")
//                .posts(new HashSet<>())
//                .build();
//
//        newYearPost.addTag(holiday);
//        newYearPost.addTag(favorite);
//
//        marryChristmas.addTag(holiday);
//
//        em.persist(newYearPost);
//        em.persist(marryChristmas);

//        em.remove(em.find(ProductType.class, 4L));

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
