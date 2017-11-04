package edu.swarthmore.cs.cs71.shelved;

import java.util.ArrayList;
import java.util.List;

public class ReadingList implements CreatedList {
    private String name = "Untitled";
    private boolean publicStatus = false;
    private List<ShelvedBook> list = new ArrayList<>();

    public ReadingList(String name, boolean publicStatus) {
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

    public List<ShelvedBook> getList() {
        return list;
    }

    //setters
    public void resetName(String name) {
        this.name = name;
    }

    public void setPublicStatus(boolean publicStatus) {
        this.publicStatus = publicStatus;
    }

    @Override
    public void addBook(ShelvedBook shelvedBook) {
        this.list.add(shelvedBook);
    }

    @Override
    public void removeBook(ShelvedBook shelvedBook) {
        this.list.remove(shelvedBook);
    }
    
}
