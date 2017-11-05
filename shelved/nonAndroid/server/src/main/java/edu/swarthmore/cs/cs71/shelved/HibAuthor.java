package edu.swarthmore.cs.cs71.shelved;

import javax.persistence.*;

@Entity
@Table(name="author")
public class HibAuthor implements Author{
    @Id
    @Column(name="author_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="fullName")
    private String fullName;

    @Column(name="lastName")
    private String lastName;

    public HibAuthor(String authorName) {
        this.fullName = authorName;
        this.lastName = authorName.substring(authorName.lastIndexOf(" ") +1);
    }

    @Override
    public String getAuthorName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }
}
