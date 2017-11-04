package edu.swarthmore.cs.cs71.shelved;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateTest {
    public static void main(String[] args){
        // Create model object
        System.out.println("Begin session Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        HibBook book = new HibBook();

        session.getTransaction().commit();
        session.close();

    }
}
