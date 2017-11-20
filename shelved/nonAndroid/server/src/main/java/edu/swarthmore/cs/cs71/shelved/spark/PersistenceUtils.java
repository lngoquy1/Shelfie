package edu.swarthmore.cs.cs71.shelved.spark;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class PersistenceUtils {
    protected static String PERSISTENCE_UNIT_KEY = "persistence_unit_name";
    protected  EntityManagerFactory entityManagerFactory ;
    public static final ThreadLocal<EntityManager> ENTITY_MANAGER = new ThreadLocal<EntityManager>();

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return ENTITY_MANAGER.get();
    }

    public  void closeEntityManager() {
        EntityManager em = ENTITY_MANAGER.get();
        ENTITY_MANAGER.set(null);
        if (em != null && em.isOpen()) {
            em.close();
            em = null;
        }
    }
}
