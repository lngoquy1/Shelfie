package edu.swarthmore.cs.cs71.shelved.model.simple;

import edu.swarthmore.cs.cs71.shelved.model.api.CreatedList;
import edu.swarthmore.cs.cs71.shelved.model.api.ShelvedBook;

import java.util.ArrayList;
import java.util.List;

public class SimpleReadingList implements CreatedList {
    private String name = "Untitled";
    private boolean publicStatus = false;
    private List<SimpleShelvedBook> list = new ArrayList<>();
    public String header = this.getClass().getSimpleName();

    public SimpleReadingList(String name, boolean publicStatus) {
        this.name = name;
        this.publicStatus = publicStatus;
    }


    //getters
    public String getName() {
        return name;
    }

    public boolean isPublicStatus() {
        return publicStatus;
    }


    //setters
    public void resetName(String name) {
        this.name = name;
    }

    public void setPublicStatus(boolean publicStatus) {
        this.publicStatus = publicStatus;
    }


    public void addBook(SimpleShelvedBook shelvedBook) {
        this.list.add(shelvedBook);
    }


    public void removeBook(SimpleShelvedBook shelvedBook) {
        this.list.remove(shelvedBook);
    }

}
