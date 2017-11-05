package edu.swarthmore.cs.cs71.shelved;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibTest {
    public static void main(String[] args){
        System.out.println("Begin session Maven + Hibernate + MySQL");
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
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
        session.close();
        session.flush();
        System.out.println("Transaction Completed!");
    }
}
