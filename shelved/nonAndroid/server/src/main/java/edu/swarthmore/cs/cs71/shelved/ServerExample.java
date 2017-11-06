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
                req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement(""));


                Integer id = Integer.parseInt(req.queryParams("id"));
                String name = req.queryParams("name");

                HibBook book = new HibBook();
                book.setId(id);
                //book.setName(name);

                session.getTransaction().begin();
                session.persist(book);
                session.getTransaction().commit();

                res.redirect("/list");
                return "";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
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


                builder.append("<table><tr><th>HibBook Id</th><th>HibBook Name</th></tr>\n");
                for (HibBook book : books) {
                    builder.append("<tr><td>" + book.getId() + "</td><td>" + book.getName() + "</td></tr>\n");
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

