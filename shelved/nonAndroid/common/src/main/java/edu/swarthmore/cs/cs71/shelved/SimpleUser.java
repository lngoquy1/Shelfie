package edu.swarthmore.cs.cs71.shelved;

import org.mindrot.jbcrypt.BCrypt;

public class SimpleUser implements User {
    private SimpleUserName userName;
    private String password;
    private String name;
    private String bio;
    private String location;
    private String salt;

    public SimpleUser() {
    }

//    public User(String username, String password, String name, String bio, String location, String salt) {
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.bio = bio;
//        this.location = location;
//        this.salt = salt;
//
//    }



    @Override
    public void setUserName(String userName) {
        this.userName = new SimpleUserName(userName);
    }

    public void setSalt() {
        this.salt = BCrypt.gensalt();
    }

    public void setPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, this.salt);
        this.password = hashedPassword;

    }

    public void setName(String name) {
        this.name = name;
    }



    public void changePassword(String oldPassword, String newPassword) {
        if (BCrypt.hashpw(oldPassword, this.salt).equals(password)) {

            this.setSalt();
            this.setPassword(newPassword);
        }
    }


    // getters
    public SimpleUserName getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getSalt() {
        return salt;
    }

    //setters
    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
