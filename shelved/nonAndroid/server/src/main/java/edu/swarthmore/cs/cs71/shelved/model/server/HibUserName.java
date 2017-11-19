package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.model.api.UserName;

import javax.persistence.*;

@Entity
@Table(name="userName")
public class HibUserName implements UserName{
    @Id
    @Column(name="userName_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="userName_name")
    private String userName;

    public HibUserName() {
    }

    public int getId() {
        return id;
    }

    public HibUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String getUserName() {
        return null;
    }
}
