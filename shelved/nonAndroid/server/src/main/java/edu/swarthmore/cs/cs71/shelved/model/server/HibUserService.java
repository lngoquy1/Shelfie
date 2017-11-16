package edu.swarthmore.cs.cs71.shelved.model.server;

public class HibUserService {
    public HibUser createUser(String userName, String name, String password){
        HibUser newUser = new HibUser();
        newUser.setSalt();
        newUser.setUserName(userName);
        newUser.setName(name);
        newUser.setPassword(password);
        return newUser;
    }
}
