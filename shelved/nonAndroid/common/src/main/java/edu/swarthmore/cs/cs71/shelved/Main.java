package edu.swarthmore.cs.cs71.shelved;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!!!");
        SimpleBook bookImpl = new SimpleBook("JK Rowling", "Fantasy Fiction", "Harry Potter", 200, "no idea");
        bookImpl.getRecBooks();
    }

}
