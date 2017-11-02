package edu.swarthmore.cs.cs71.shelved;

public class SimpleShelvedBook implements ShelvedBook {

    int bookMark;
    boolean forSale;
    boolean forLend;
    int position;


    public SimpleShelvedBook(int bookMark, boolean forSale, boolean forLend, int position) {
        this.bookMark = bookMark;
        this.forSale = forSale;
        this.forLend = forLend;
        this.position = position;
    }

    @Override
    public int getPosition() {
        return 0;
    }
}
