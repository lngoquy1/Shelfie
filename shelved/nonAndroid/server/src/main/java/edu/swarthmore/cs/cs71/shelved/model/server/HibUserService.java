package edu.swarthmore.cs.cs71.shelved.model.server;


import edu.swarthmore.cs.cs71.shelved.model.spark.PersistenceUtils;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class HibUserService {
    public HibUser createUser(String userName, String name, String password){
        HibUser newUser = new HibUser();
        newUser.setSalt();
        newUser.setUserName(userName);
        newUser.setName(name);
        newUser.setPassword(password);
        PersistenceUtils.ENTITY_MANAGER.get().persist(newUser);
        return newUser;
    }

    public boolean checkUserValid(SessionFactory sf, int userName_id, String password) {
        EntityManager session = sf.createEntityManager();
        String hql = "FROM shelvedUser S WHERE S.shelvedUser_username ="+String.valueOf(userName_id);
        Query query = session.createQuery(hql);
        try{
            List<HibUser> users = query.getResultList();
            if (users.size() != 1 ){
                throw new RuntimeException("Duplicate username");
            }
            String salt = users.get(0).getSalt();
            String hashedPassword = users.get(0).getPassword();
            String checkingPassword = BCrypt.hashpw(password, salt);
            return (hashedPassword.equals(checkingPassword));
        } catch (ArrayStoreException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            if (session.isOpen()){
                session.close();
            }
        }
    }

    public int getUserNameId(SessionFactory sf, String userName) {
        EntityManager session = sf.createEntityManager();
        System.out.println("line 1");
//        String hql = "SELECT userName_id FROM userName S WHERE S.userName_name ="+userName;
        String hql = "SELECT userName_id FROM userName WHERE userName_name = \""+userName+"\"";
        System.out.println("line 2");
        System.out.println(hql);
        Query query = session.createQuery(hql);
        System.out.println("line 3");
        try {
            List<Integer> userIds = query.getResultList();
            System.out.println("line 4");
            if (userIds.size() != 1){
                System.out.println("line 5");
                throw new RuntimeException("Duplicate username_id");
            }
            System.out.println("line 6");
            return userIds.get(0);
        } catch (ArrayStoreException e){
            System.out.println(e.toString());
            return -1;
        } finally {
            if (session.isOpen()){
                session.close();
            }
        }
    }

//    public HibUser getUserById(EntityManager session, int id){
//        List<HibUser> user = session.createQuery
//                ("SELECT * FROM shelvedUser WHERE shelvedUser_username ="+id)
//                .getResultList();
//        return user.get(0);
//    }

}
