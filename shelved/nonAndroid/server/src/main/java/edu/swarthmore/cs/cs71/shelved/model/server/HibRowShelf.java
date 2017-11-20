package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.model.api.RowShelf;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="rowShelf")
public class HibRowShelf implements RowShelf {
    @Id
    @Column(name = "rowShelf_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name = "shelvedBook")
    private HashMap<HibShelvedBook, Integer> rowList = new HashMap<>();

    public HibRowShelf() {
    }


    public void addBook(HibShelvedBook shelvedBook, int position) {
        this.rowList.put(shelvedBook, position);
    }


    public void removeBook(HibShelvedBook book) {
        this.rowList.remove(book);
    }

    public int getBook(HibShelvedBook book) {
        return this.rowList.get(book);
    }

    public void resetPosition(HibShelvedBook book, int newPosition) {
        this.rowList.put(book, newPosition);
    }


    public Set<HibShelvedBook> getAllBooks() {
        return this.rowList.keySet();
    }
}
