package edu.swarthmore.cs.cs71.group_shelved.iteration3;

public interface ShelvedBook {
    Book book = new BookImpl(String author, String genre, String title, Integer pages, String publisher);
    int bookMark = 0;
    boolean forSale = false;
    boolean forLend = false;
    //Position position = new Position(int index);
    int getPosition();
}

