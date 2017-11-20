package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleAuthor;
import edu.swarthmore.cs.cs71.shelved.model.spark.PersistenceUtils;

public class HibBookService {
    public HibBook createBook(String author, String title, String genre, int pages, String publisher){
        HibBook newBook = new HibBook();
        newBook.setAuthor(author);
        newBook.setTitle(title);
        newBook.setGenre(genre);
        newBook.setPages(pages);
        newBook.setPublisher(publisher);
        PersistenceUtils.ENTITY_MANAGER.get().persist(newBook);
        return newBook;
    }

}
