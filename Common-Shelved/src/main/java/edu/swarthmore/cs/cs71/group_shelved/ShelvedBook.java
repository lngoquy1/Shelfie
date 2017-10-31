package edu.swarthmore.cs.cs71.group_shelved;

public interface ShelvedBook {
//    edu.swarthmore.cs.cs71.group_shelved.Book book = new edu.swarthmore.cs.cs71.group_shelved.BookImpl(String author, String genre, String title, Integer pages, String publisher);
    int bookMark = 0;
    boolean forSale = false;
    boolean forLend = false;
    //edu.swarthmore.cs.cs71.group_shelved.Position position = new edu.swarthmore.cs.cs71.group_shelved.Position(int index);
    int getPosition();
}


