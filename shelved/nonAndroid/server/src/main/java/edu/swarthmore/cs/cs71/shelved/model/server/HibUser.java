package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.model.api.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="shelvedUser")
public class HibUser implements User {
    @Id
    @Column(name="shelvedUser_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = HibEmail.class)
    @JoinColumn(name = "shelvedUser_email")
    private HibEmail email;

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

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HibBookShelf> allShelves;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HibReadingList> allReadingLists;

    @Column(name="token")
    private String token;

    //TODO: Testing with making a book list for user
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HibBook> userBooks;

    public HibUser() {
        setSalt();
    }
    @Override
    public void setToken(String token){ this.token = token;}
    @Override
    public void setEmail(String email) {
        this.email = new HibEmail(email);
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
    public void setShelves(){
        this.allShelves = new ArrayList<HibBookShelf>();
    }

    public void setReadingLists(){ this.allReadingLists = new ArrayList<HibReadingList>();}
    @Override
    public void changePassword(String oldPassword, String newPassword) {
        if (BCrypt.hashpw(oldPassword, this.salt).equals(password)){
            setSalt();
            setPassword(newPassword);
        }
    }


    public int getId() {
        return id;
    }
    public String getToken(){return token;}
    public HibEmail getEmail() {
        return this.email;
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

    public List<HibBookShelf> getAllShelves() {
        return allShelves;
    }

    public void addBookShelf(HibBookShelf bookShelf){ allShelves.add(bookShelf);}

    public void addReadingList(HibReadingList readingList){
        this.allReadingLists.add(readingList);
    }

    // TODO: currently adding Book to this userBooks list
    public void setUserBooks(){ this.userBooks = new ArrayList<HibBook>(); }
    public void addBook(HibBook book){
        this.userBooks.add(book);
    }
}
