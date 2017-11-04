package edu.swarthmore.cs.cs71.shelved;

public class SimpleShelvedBook implements ShelvedBook {

    private int bookMark;
    private boolean forSale;
    private boolean forLend;
    private Book book;

    public SimpleShelvedBook(Book book, int bookMark, boolean forSale, boolean forLend, int position) {
        this.bookMark = bookMark;
        this.forSale = forSale;
        this.forLend = forLend;
        this.book = book;
    }

    public int getBookMark() {
        return bookMark;
    }

    public boolean isForSale() {
        return forSale;
    }

    public boolean isForLend() {
        return forLend;
    }


    public void setForSale(boolean option) {
        this.forSale = option;
    }

    public void setForLend(boolean option) {
        this.forLend = option;
    }

    public void setBookMark(int page) {
        this.bookMark = page;
    }

    public Book getBook() {
        return this.book;
    }
}
