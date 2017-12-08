package edu.swarthmore.cs.cs71.shelved.model.server;


import edu.swarthmore.cs.cs71.shelved.spark.PersistenceUtils;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

public class HibUserService {
    public HibUser createUser(String userName, String name, String password){
        HibUser newUser = new HibUser();
        String token = UUID.randomUUID().toString();
        newUser.setEmail(userName);
        newUser.setName(name);
        newUser.setPassword(password);
        newUser.setShelves();
        newUser.setReadingLists();
        PersistenceUtils.ENTITY_MANAGER.get().persist(newUser);
        return newUser;
    }

    public int checkUserValid(SessionFactory sf, String userName, String password) {
        //returns -1 if no username found
        //returns -2 if incorrect password
        //returns -3 if ArrayStoreException: i.e. createQuery created a list
                    //of a different type than we were expecting
        EntityManager session = sf.createEntityManager();
        try {
//            String hql = "FROM shelvedUser S WHERE S.shelvedUser_username ="+String.valueOf(userName_id);
            List<HibUser> users = session.createQuery("FROM HibUser").getResultList();
            for (HibUser hibuser:users){
                System.out.println("email: " + hibuser.getEmail().getEmail());
                if (userName.equals(hibuser.getEmail().getEmail())){
                    System.out.println("line 4");
                    String salt = hibuser.getSalt();
                    String hashedPassword = hibuser.getPassword();
                    String checkingPassword = BCrypt.hashpw(password, salt);
                    if (hashedPassword.equals(checkingPassword)){
                        return hibuser.getId();
                    } else {
                        return -2;
                    }
                }
            }
            return -1;
        } catch (ArrayStoreException e) {
            System.out.println(e.toString());
            return -3;
        } finally {
            if (session.isOpen()){
                session.close();
            }
        }
    }

    public int getUserNameId(SessionFactory sf, String userName) {
        EntityManager session = sf.createEntityManager();
        try {
//            String hql = "SELECT userName_id FROM userName WHERE userName_name = \""+userName+"\"";
            System.out.println("line 1");
            Query query = session.createQuery("FROM HibUserName");
            System.out.println("line 2");
            List<HibEmail> usernames = query.getResultList();
            System.out.println("line 3");
            for (HibEmail hibusername:usernames){
                System.out.println("line LOOP");
                if (userName.equals(hibusername.getEmail())){
                    System.out.println("line 4");
                    return hibusername.getId();
                }
            }
            System.out.println("line 5");
            throw new RuntimeException("No username found");
        } catch (ArrayStoreException e){
            System.out.println(e.toString());
            return -1;
        } finally {
            if (session.isOpen()){
                session.close();
            }
        }
    }

    public void setUserLoginToken(HibUser user){
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        PersistenceUtils.ENTITY_MANAGER.get().merge(user);
    }



    public HibUser getUserByID(SessionFactory sf, Integer userID){
        EntityManager session = sf.createEntityManager();
        try {
            Query query = session.createQuery("FROM HibUser");
            List<HibUser> users = query.getResultList();
            for (HibUser user:users){
                if (user.getId()==userID){
                    return user;
                }
            }
            throw new RuntimeException("No user with such ID found");
        } catch (ArrayStoreException e){
            System.out.println(e.toString());
            return null;
        } finally {
            if (session.isOpen()){
                session.close();
            }
        }
    }

}
