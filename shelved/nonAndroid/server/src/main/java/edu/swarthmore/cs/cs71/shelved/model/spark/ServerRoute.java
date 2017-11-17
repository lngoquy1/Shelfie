package edu.swarthmore.cs.cs71.shelved.model.spark;

import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.persistence.EntityManager;


public abstract class ServerRoute implements Route{
    private final SessionFactory sf;

    public ServerRoute(SessionFactory sf) {
        this.sf = sf;
    }
    protected abstract Object execute(Request request, Response response);
    @Override
    public Object handle(Request request, Response response) throws Exception {
        EntityManager session = sf.createEntityManager();
        PersistenceUtils.ENTITY_MANAGER.set(session);
        session.getTransaction().begin();
        try {
            // Do stuffs
            Object o = execute(request, response);
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



