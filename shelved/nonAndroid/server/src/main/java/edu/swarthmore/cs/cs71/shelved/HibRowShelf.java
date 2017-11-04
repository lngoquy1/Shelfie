package edu.swarthmore.cs.cs71.shelved;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="rowShelf")
public class HibRowShelf implements RowShelf{
    private int id;
    private ArrayList<HibShelvedBook> rowList = new ArrayList<>();

    public HibRowShelf() {
    }
    @Id
    @Column(name = "rowShelf_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return 0;
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
    public ShelvedBook getBook(int position) {
        return null;
    }
}
