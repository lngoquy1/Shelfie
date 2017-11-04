package edu.swarthmore.cs.cs71.shelved;

import java.util.ArrayList;

public class WishList implements CreatedList {
    private String name = "Wishlist";
    private boolean publicStatus;
    private ArrayList<ShelvedBook> list = new ArrayList<>();

    public WishList(boolean publicStatus) {
        this.publicStatus = publicStatus;
    }

    //getters
    public String getName() {
        return name;
    }

    public boolean getPublicStatus() {
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
