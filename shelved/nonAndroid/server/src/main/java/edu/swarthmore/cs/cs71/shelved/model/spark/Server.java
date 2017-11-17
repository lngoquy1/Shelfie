package edu.swarthmore.cs.cs71.shelved.model.spark;

import spark.Service;


public class Server {

    public static void main(String[] args) {
        Service service = Service.ignite();
        service.port(34567);
        service.staticFiles.location("/");
        service.init();

    }
}
//
//public class HibTest {
//    public static void main(String[] args){
//        System.out.println("Begin session Maven + Hibernate + MySQL");
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        Session session = factory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        // Test author
//        HibAuthor author = new HibAuthor("JK Rowling");
//        session.save(author);
//
//        // Test genre
//        HibGenre genre = new HibGenre("Fiction");
//        session.save(genre);
//
//        // Test title
//        HibTitle title = new HibTitle("Harry Potter and the Deathly Hallows");
//        session.save(title);
//
//        // Test book
//        HibBook book = new HibBook();
//        book.setAuthor("Haruki Murakami");
//        book.setGenre("Fiction");
//        book.setTitle("Norweigian Wood");
//        book.setPages(296);
//        book.setPublisher("Vintage International");
//        session.save(book);
//        transaction.commit();
//        session.close();
//        System.out.println("Transaction Completed!");
//
//    }
//}


