package edu.swarthmore.cs.cs71.group_shelved.common;

public class ShelvedBookImpl implements ShelvedBook {

    int bookMark;
    boolean forSale;
    boolean forLend;
    int position;


    public ShelvedBookImpl(int bookMark, boolean forSale, boolean forLend, int position) {
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
