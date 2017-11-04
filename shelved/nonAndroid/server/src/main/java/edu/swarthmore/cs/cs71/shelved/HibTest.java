package edu.swarthmore.cs.cs71.shelved;


import org.hibernate.Session;

public class HibTest {
    public static void main(String[] args){
        // Create model object
        System.out.println("Begin session Maven + Hibernate + MySQL");
        Session session = HibUtil.getSessionFactory().openSession();

        session.beginTransaction();
        HibBook book = new HibBook();

        session.getTransaction().commit();
        session.close();

    }
}
