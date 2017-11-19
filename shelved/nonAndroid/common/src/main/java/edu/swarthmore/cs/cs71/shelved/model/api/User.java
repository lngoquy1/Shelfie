package edu.swarthmore.cs.cs71.shelved.model.api;

public interface User {

    void setEmail(String email);
    void setPassword(String password);
    void setName(String name);
    public void setBio(String bio);
    public void setLocation(String location);
    void setSalt();
    void changePassword(String oldPassword, String newPassword);
//    void resetPassword(String newPassword);
    // getters

    String getPassword();

    String getName();

    String getBio();

    String getLocation();

    String getSalt();


}
