package edu.swarthmore.cs.cs71.shelved;

import java.util.ArrayList;

public class WishList implements CreatedList {
    String name;
    boolean publicStatus;
    ArrayList<ShelvedBook> list;

    public WishList(boolean publicStatus) {
        this.name = "Wishlist";
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

    // setters
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
