package edu.swarthmore.cs.cs71.shelved;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private String username;
    private String password;
    private String name;
    private String bio;
    private String location;
    private String salt;

    public User(String username, String password, String name, String bio, String location) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.bio = bio;
        this.location = location;
        this.salt = BCrypt.gensalt();

    }

    public void changePassword(String oldPassword, String newPassword) {
        // if hash(oldPassword) = this.password {
        //      this.password = hash(newPassword)
        // } else {
        //      notify user to try again
        // }
    }
}
