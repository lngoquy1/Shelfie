package edu.swarthmore.cs.cs71.shelved.model;

import edu.swarthmore.cs.cs71.shelved.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

public class HibUser implements User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = HibAuthor.class)
    @JoinTable(name = "user_userName", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "userName_id") })
    private HibUserName userName;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="bio")
    private String bio;

    @Column(name="location")
    private String location;

    @Column(name="salt")
    private String salt;

    public HibUser() {
    }

    @Override
    public void setUserName(String userName) {
        this.userName = new HibUserName(userName);
    }

    @Override
    public void setPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, this.salt);
        this.password = hashedPassword;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setSalt() {
        this.salt = BCrypt.gensalt();
    }


    @Override
    public void changePassword(String oldPassword, String newPassword) {
        if (BCrypt.hashpw(oldPassword, this.salt)==this.password){
            setSalt();
            setPassword(newPassword);
        }
    }


    public HibUserName getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getBio() {
        return this.bio;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public String getSalt() {
        return this.salt;
    }
}
