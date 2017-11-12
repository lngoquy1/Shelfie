package edu.swarthmore.cs.cs71.shelved.model;

import edu.swarthmore.cs.cs71.shelved.model.api.RowShelf;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="rowShelf")
public class HibRowShelf implements RowShelf {
    @Id
    @Column(name = "rowShelf_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rowShelf")
    private List<HibShelvedBook> rowList = new ArrayList<>();

    public HibRowShelf() {
    }

    public void addBook(HibShelvedBook shelvedBook, int position) {
        this.rowList.add(position, shelvedBook);
    }

    @Override
    public void removeBook(int position) {
        this.rowList.remove(position);
    }

    @Override
    public void resetPosition(int oldPosition, int newPosition) {
        HibShelvedBook book = this.rowList.get(oldPosition);
        this.rowList.remove(oldPosition);
        this.rowList.add(newPosition, book);
    }


    public HibShelvedBook getBook(int position) {
        return this.rowList.get(position);
    }


    public List<HibShelvedBook> getAllBooks() {
        return this.rowList;
    }
}
