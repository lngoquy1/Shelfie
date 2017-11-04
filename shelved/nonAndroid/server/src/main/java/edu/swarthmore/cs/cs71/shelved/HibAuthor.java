package edu.swarthmore.cs.cs71.shelved;

import javax.persistence.*;

@Entity
@Table(name="author")
public class HibAuthor implements Author{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String lastName;

    public HibAuthor(String authorName) {
        this.fullName = authorName;
        this.lastName = authorName.substring(authorName.lastIndexOf(" ") +1);
    }
//    @Id
//    @Column(name="author_id")
//    @GeneratedValue()
//    public int getId(){
//        return this.id;
//    }
//    public void setId(int id){
//        this.id = id;
//    }
    @Override
    public String getAuthorName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }
}
