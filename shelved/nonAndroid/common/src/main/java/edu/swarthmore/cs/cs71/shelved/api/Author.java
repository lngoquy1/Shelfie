package edu.swarthmore.cs.cs71.shelved.api;

public interface Author {


    String getAuthorName();
    String getLastName();

    boolean equals(Object o);
    int hashCode();

}
