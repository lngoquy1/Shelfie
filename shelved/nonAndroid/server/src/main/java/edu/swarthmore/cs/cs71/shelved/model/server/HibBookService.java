package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.spark.PersistenceUtils;

public class HibBookService {
    public HibBook createBook(String title, String author){ //, String genre, int pages, String publisher
        HibBook newBook = new HibBook();
        newBook.setAuthor(author);
        newBook.setTitle(title);
//        newBook.setGenre(genre);
//        newBook.setPages(pages);
//        newBook.setPublisher(publisher);
        System.out.println("start");
        PersistenceUtils.ENTITY_MANAGER.get().persist(newBook);
        return newBook;
    }

}
