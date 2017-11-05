package edu.swarthmore.cs.cs71.shelved;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="rowShelf")
public class HibRowShelf implements RowShelf{
    @Id
    @Column(name = "rowShelf_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private List<HibShelvedBook> rowList = new ArrayList<>();

    public HibRowShelf() {
    }


    @Override
    public void addBook(ShelvedBook shelvedBook, int position) {

    }

    @Override
    public void removeBook(int position) {

    }

    @Override
    public void resetPosition(int oldPosition, int newPosition) {

    }


    @Override
    public HibShelvedBook getBook(int position) {
        return null;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<HibShelvedBook> getAllBooks() {
        return this.rowList;
    }
}
