package edu.swarthmore.cs.cs71.shelved;

import org.mindrot.jbcrypt.BCrypt;

public class Login {

    public void login(String username, String password) {
        // if username in database {
        //     go to user in database
        //     lookup salt associated with user
        //     hash given password+salt
        //     check against hashed password stored in database
        //     if match {
        //         login (return user data to have locally?)
        //     if not match {
        //         "invalid username or password
        //     }
        // } else {
        //     createUser(username, password);
    }

    public User createUser(String username, String password, String name, String bio, String location) {
        // if username in database {
        // println("This username is already in use");
        // } else {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);
        User user = new User(username, hashedPassword, name, bio, location, salt);
        // add user to database
        return user;
    }

}
