package edu.swarthmore.cs.cs71.shelved.model.server;


import edu.swarthmore.cs.cs71.shelved.model.api.CreatedList;
import edu.swarthmore.cs.cs71.shelved.model.api.ShelvedBook;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="readingList")
public class HibReadingList implements CreatedList {
    @Id
    @Column(name="readingList_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HibShelvedBook> list = new ArrayList<HibShelvedBook>();

    @Column(name="listName")
    private String listName;

    @Column(name="publicStatus")
    private boolean publicStatus;

    public HibReadingList() {
    }

    // TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO
    // @ Lan these methods need to override the methods in the CreatedList interface
    // can the list in this hibernate class be a list of ShelvedBook instead of
    // HibShelvedBook?? because your code in HibRowShelf has list containing
    // HibShelvedBook, but our RowShelf interface is literally empty so im guessing
    // you didnt encounter this mismatched type problem like I am????


    public void addBook(HibShelvedBook shelvedBook) { this.list.add(shelvedBook); }


    public void removeBook(HibShelvedBook shelvedBook) { this.list.remove(shelvedBook); }


    // setters

    public void setId(int id) { this.id = id; }

    public void resetName(String listName) { this.listName = listName; }

    public void setPublicStatus(boolean publicStatus) { this.publicStatus = publicStatus; }


    //getters

    public int getId() { return this.id; }

    public String getName() { return this.listName; }

    public boolean isPublicStatus() { return this.publicStatus; }

}
