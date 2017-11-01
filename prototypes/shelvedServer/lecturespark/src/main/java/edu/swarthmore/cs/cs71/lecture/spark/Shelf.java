package edu.swarthmore.cs.cs71.lecture.spark;

public class Shelf {
    private int numBooks;
    private String book1;
    private String book2;
    private String book3;

    public Shelf() {
        this.numBooks = 3;
        this.book1 = "1Q84";
        this.book2 = "Norwegian Wood";
        this.book3 = "The Book Thief";
    }

    public int getNumBooks() {
        return numBooks;
    }

    public String getBook1() {
        return book1;
    }

    public String getBook2() {
        return book2;
    }

    public String getBook3() {
        return book3;
    }
}