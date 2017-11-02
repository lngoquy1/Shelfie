package edu.swarthmore.cs.cs71.shelved;

import edu.swarthmore.cs.cs71.group_shelved.common.CreatedList;
import edu.swarthmore.cs.cs71.group_shelved.common.ShelvedBook;

import java.util.ArrayList;

public class ReadingList implements CreatedList {
    String name;
    boolean publicStatus;
    ArrayList<ShelvedBook> list;

    public ReadingList(String name, boolean publicStatus) {
        this.name = name;
        this.publicStatus = publicStatus;
        this.list = new ArrayList<>();
    }

    //getters
    public String getName() {
        return name;
    }

    public boolean isPublicStatus() {
        return publicStatus;
    }

    public ArrayList<ShelvedBook> getList() {
        return list;
    }

    //setters
    public void setName(String name) {
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
