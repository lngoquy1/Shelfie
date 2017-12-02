package edu.swarthmore.cs.cs71.shelved.shelved;

public interface Continuation<T> {
    void run(T t);
}
