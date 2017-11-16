package edu.swarthmore.cs.cs71.shelved.server;

import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.persistence.EntityManager;
import java.util.List;


public abstract class ServerRoute implements Route{
    private final SessionFactory sf;

    public ServerRoute(SessionFactory sf) {
        this.sf = sf;
    }
    protected abstract List<Object> execute(Request request);
    @Override
    public Object handle(Request request, Response response) throws Exception {
        EntityManager session = sf.createEntityManager();
        try {
            // Do stuffs
            Object o = execute(request);
            session.getTransaction().begin();
            session.persist(o);
            session.getTransaction().commit();
            return o;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return "Error: " + e.getMessage();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }
}



