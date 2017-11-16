package edu.swarthmore.cs.cs71.shelved.model.server;

public class HibUserService {
    public HibUser createUser(String userName, String password){
        HibUser newUser = new HibUser();
        newUser.setSalt();
        newUser.setUserName(userName);
        newUser.setPassword(password);
        return newUser;
    }
}
