package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.model.api.Email;

import javax.persistence.*;

@Entity
@Table(name="email")
public class HibEmail implements Email {
    @Id
    @Column(name="email_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="email_email")
    private String email;

    public HibEmail() {
    }

    public int getId() {
        return id;
    }

    public HibEmail(String email) {
        this.email = email;
    }


    @Override
    public String getEmail() {
        return null;
    }
}
