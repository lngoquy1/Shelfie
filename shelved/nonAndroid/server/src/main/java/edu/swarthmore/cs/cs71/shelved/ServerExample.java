package edu.swarthmore.cs.cs71.shelved;


import static spark.Spark.*;

import edu.swarthmore.cs.cs71.shelved.model.HibBook;
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

                //create header
                addTable(builder);
                addRow(builder);
                // get strings as variables
                addHeaderCell(builder,"Id");
                addHeaderCell(builder,"Title");
                addHeaderCell(builder,"Author");
                addHeaderCell(builder,"Genre");
                closeRow(builder);
                newLine(builder);
                //fill in contents
                for (HibBook book : books) {
                    addRow(builder);
                    addCell(builder, String.valueOf(book.getId()));
                    addCell(builder, book.getTitle().getTitle());
                    addCell(builder, book.getAuthor().getAuthorName());
                    addCell(builder, book.getGenre().getGenre());
                    closeRow(builder);
                }

                closeTable(builder);
                newLine(builder);

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

    private static void addTable(StringBuilder builder) {
        builder.append("<table>");
    }

    private static void closeTable(StringBuilder builder) {
        builder.append("</table>");
    }

    private static void addHeaderCell(StringBuilder builder, String contents) {
        builder.append("<th>"+contents+"</th>");
    }

    private static void newLine(StringBuilder builder) {
        builder.append("\n");
    }

    private static void addRow(StringBuilder builder){
        builder.append("<tr>");
    }
    private static void closeRow(StringBuilder builder){
        builder.append("</tr>");
    }
    private static void addCell(StringBuilder builder, String contents){
        builder.append("<td>"+contents+"</td>");
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

