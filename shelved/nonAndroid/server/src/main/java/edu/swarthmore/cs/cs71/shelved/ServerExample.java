package edu.swarthmore.cs.cs71.shelved;


import static spark.Spark.*;

import edu.swarthmore.cs.cs71.shelved.model.HibBook;
import edu.swarthmore.cs.cs71.shelved.model.HibShelvedBook;
import edu.swarthmore.cs.cs71.shelved.model.HibUser;
import org.hibernate.SessionFactory;
import org.hibernate.boot.jaxb.SourceType;
import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManager;
import javax.servlet.MultipartConfigElement;
import java.util.List;


public class ServerExample {

    public static void main(String[] argv) {



        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        staticFiles.location("/public");


        initializeDatabase(sf);


        get("/list", (req, res) -> {
            EntityManager session = sf.createEntityManager();
            try {
                List<HibBook> books = session.createQuery("FROM HibBook").getResultList();

                StringBuilder builder = new StringBuilder();

                builder.append("<style>\n" +
                        "table {\n" +
                        "    border-collapse: collapse;\n" +
                        "    width: 50%;\n" +
                        "}" +
                        "td, th {\n" +
                        "    border: 1px solid #dddddd;\n" +
                        "    text-align: left;\n" +
                        "    padding: 8px;\n" +
                        "}\n" +
                        "</style>");


                builder.append("<table><tr><th>Id</th><th>Title</th><th>Author</th><th>Genre</th></tr>\n");
                for (HibBook book : books) {
                    builder.append("<tr><td>" + book.getId() + "</td><td>" + book.getTitle().getTitle() + "</td><td>" + book.getAuthor().getAuthorName() + "</td><td>" + book.getGenre().getGenre() + "</td></tr>\n");
                }
                builder.append("</table>\n");

                return builder.toString();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }

        });
    }

    private static void initializeDatabase(SessionFactory sf) {
        EntityManager session = sf.createEntityManager();
        try {
            System.out.println("enter try");

            HibBook book = new HibBook();
            book.setAuthor("Haruki Murakami");
            book.setGenre("Fiction");
            book.setTitle("Norweigian Wood");
            book.setPages(296);
            book.setPublisher("Vintage International");
            HibShelvedBook shelvedBook = new HibShelvedBook();
            shelvedBook.setBook(book);
            shelvedBook.setBookMark(59);
            shelvedBook.setForLend(true);
            HibUser user1  = new HibUser();
            user1.setUserName("Lan");



            session.getTransaction().begin();
            session.persist(book);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

}

