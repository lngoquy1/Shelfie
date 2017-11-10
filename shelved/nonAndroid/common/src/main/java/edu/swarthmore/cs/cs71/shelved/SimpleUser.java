package edu.swarthmore.cs.cs71.shelved;

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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void changePassword(String oldPassword, String newPassword) {
        // if hash(oldPassword) = this.password {
        //      this.password = hash(newPassword)
        // } else {
        //      notify user to try again
        // }
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
