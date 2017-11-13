package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.model.api.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

@Entity
@Table(name="shelvedUser")
public class HibUser implements User {
    @Id
    @Column(name="shelvedUser_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = HibUserName.class)
    @JoinColumn(name = "shelvedUser_username")
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
        if (BCrypt.hashpw(oldPassword, this.salt).equals(password)){
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
