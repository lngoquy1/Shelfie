package edu.swarthmore.cs.cs71.group_shelved;

import sun.security.provider.SHA;

import java.util.ArrayList;

public class WishList implements CreatedList {
    String name;
    boolean publicStatus;
    ArrayList<ShelvedBook> list;

    public WishList(String name, boolean publicStatus) {
        this.name = name;
        this.publicStatus = publicStatus;
        this.list = new ArrayList<ShelvedBook>();
    }

    public String getName() {
        return name;
    }

    public boolean isPublicStatus() {
        return publicStatus;
    }

    public ArrayList<ShelvedBook> getList() {
        return list;
    }



    //public void setName(String name) {
    //    this.name = name;
    //}

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
