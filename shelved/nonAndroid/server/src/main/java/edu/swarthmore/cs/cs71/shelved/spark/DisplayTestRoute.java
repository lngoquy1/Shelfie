package edu.swarthmore.cs.cs71.shelved.spark;

import edu.swarthmore.cs.cs71.shelved.model.server.HibBook;
import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

class DisplayTestRoute implements Route {
    private final SessionFactory sf;

    public DisplayTestRoute(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Object handle(Request req, Response res) throws Exception {
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
            List<String> headerCategories = Arrays.asList("Id", "Title", "Author", "Genre");
            for (String item : headerCategories) {
                addHeaderCell(builder, item);
            }
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
}
