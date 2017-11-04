package edu.swarthmore.cs.cs71.shelved;

public interface ShelvedBook {
    int getBookMark();
    boolean isForSale();
    boolean isForLend();
    void setForSale(boolean option);
    void setForLend(boolean option);
    void setBookMark(int page);
    Book getBook();
}


