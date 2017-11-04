package edu.swarthmore.cs.cs71.shelved;


import org.hibernate.Session;

public class HibTest {
    public static void main(String[] args){
        // Create model object
        System.out.println("Begin session Maven + Hibernate + MySQL");
        Session session = HibUtil.getSessionFactory().openSession();

        session.beginTransaction();
        HibBook book = new HibBook();
        book.setAuthor("Haruki Murakami");
        book.setGenre("Fiction");
        book.setTitle("Norweigian Wood");
        book.setPages(296);
        book.setPublisher("Vintage International");
        session.save(book);

        session.getTransaction().commit();
        session.close();

    }
}
