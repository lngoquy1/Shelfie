package edu.swarthmore.cs.cs71.shelved.model.server;


import edu.swarthmore.cs.cs71.shelved.model.spark.PersistenceUtils;

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
}
