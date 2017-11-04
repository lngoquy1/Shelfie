package edu.swarthmore.cs.cs71.shelved;

import edu.swarthmore.cs.cs71.shelved.common.*;

import javax.persistence.*;
import java.util.Dictionary;
import java.util.List;

@Entity
@Table(name="shelvedBook")
public class HibShelvedBook implements ShelvedBook {

    private int id;
    private int bookMark;
    private boolean forSale;
    private boolean forLend;
    private Book book;

    public HibShelvedBook(int bookMark, boolean forSale, boolean forLend, Book book) {
        this.bookMark = bookMark;
        this.forSale = forSale;
        this.forLend = forLend;
        this.book = book;
    }
    @Id
    @Column(name="shelvedBook_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public int getBookMark() {
        return this.bookMark;
    }

    @Override
    public boolean isForSale() {
        return this.forSale;
    }

    @Override
    public boolean isForLend() {
        return this.forLend;
    }

    @Override
    public void setForSale(boolean option) {
        this.forSale = option;
    }

    @Override
    public void setForLend(boolean option) {
        this.forLend = option;
    }

    @Override
    public void setBookMark(int page) {
        this.bookMark = page;
    }

    @Override
    public Book getBook() {
        return this.book;
    }
}


