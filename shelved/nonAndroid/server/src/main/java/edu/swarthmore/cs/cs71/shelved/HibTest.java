package edu.swarthmore.cs.cs71.shelved;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibTest {
    public static void main(String[] args){
        System.out.println("Begin session Maven + Hibernate + MySQL");
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(builder.build());
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        HibBook book = new HibBook();
        book.setAuthor("Haruki Murakami");
        book.setGenre("Fiction");
        book.setTitle("Norweigian Wood");
        book.setPages(296);
        book.setPublisher("Vintage International");
        session.save(book);
        transaction.commit();
        session.flush();
        session.close();
        System.out.println("Transaction Completed!");
    }
}
