package edu.swarthmore.cs.cs71.shelved;


import static spark.Spark.*;

import edu.swarthmore.cs.cs71.shelved.model.HibBook;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManager;
import javax.servlet.MultipartConfigElement;
import java.util.List;


public class ServerExample {

    public static void main(String[] argv) {

        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        staticFiles.location("/public");

        post("/api/book", (req, res) -> {

            EntityManager session = sf.createEntityManager();
            try {
                System.out.println("enter try");
                req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement(""));


                Integer id = Integer.parseInt(req.queryParams("id"));
                String author = req.queryParams("Haruki Murakami");

                HibBook book = new HibBook();
                book.setId(id);
                book.setAuthor("Haruki Murakami");
//                book.setGenre("Fiction");
//                book.setTitle("Norweigian Wood");
//                book.setPages(296);
//                book.setPublisher("Vintage International");



                session.getTransaction().begin();
                session.persist(book);

                session.getTransaction().commit();
                res.redirect("/list");
                return "";
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return "Error: " + e.getMessage();
            } finally {
                if (session.isOpen()) {
                    //System.out.println("Error1");
                    session.close();
                }
                //System.out.println("Error2");
            }
        });


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


                builder.append("<table><tr><th>HibBook Id</th><th>HibBook Title</th></tr>\n");
                for (HibBook book : books) {
                    builder.append("<tr><td>" + book.getId() + "</td><td>" + book.getTitle() + "</td></tr> +\n");
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

}

