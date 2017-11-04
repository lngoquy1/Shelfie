package edu.swarthmore.cs.cs71.shelved;


import java.util.ArrayList;

public class ReadingList implements CreatedList {
    private String name;
    private boolean publicStatus;
    private ArrayList<SimpleShelvedBook> list;

    public ReadingList(String name, boolean publicStatus) {
        this.name = name;
        this.publicStatus = publicStatus;
        this.list = new ArrayList<SimpleShelvedBook>();
    }

    //getters
    public String getName() {
        return name;
    }

    public boolean isPublicStatus() {
        return publicStatus;
    }

    public ArrayList<SimpleShelvedBook> getList() {
        return list;
    }

    //setters
    public void setName(String name) {
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
