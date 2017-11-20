package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.model.api.BookShelf;
import edu.swarthmore.cs.cs71.shelved.model.api.RowShelf;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleRowShelf;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="bookShelf")
public class HibBookShelf implements BookShelf{
    @Id
    @Column(name="bookShelf_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bookShelf")
    private ArrayList<HibRowShelf> allRows = new ArrayList<>();

    public HibBookShelf() {
    }


    public List<HibRowShelf> getAllRows() {
        return this.allRows;
    }

    @Override
    public int getNumRows() {
        return this.allRows.size();
    }

    @Override
    public void configureBookShelf(int numRows) {
        this.allRows = new ArrayList<>();
        for (int i=0;i<numRows;i++){
            this.allRows.add(new HibRowShelf());
        }
    }

    public HibRowShelf getRowShelf(int rowPosition){
        return this.allRows.get(rowPosition);
    }

    public void addRowShelf(int index, HibRowShelf rowShelf) {
        this.allRows.add(index, rowShelf);
    }

    public void removeRowShelf(HibRowShelf rowShelf) {
        this.allRows.remove(rowShelf);
    }
}
