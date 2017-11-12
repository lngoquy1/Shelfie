package edu.swarthmore.cs.cs71.shelved.simple;

import edu.swarthmore.cs.cs71.shelved.api.CreatedList;
import edu.swarthmore.cs.cs71.shelved.api.ShelvedBook;

import java.util.ArrayList;
import java.util.List;

public class WishList implements CreatedList {
    private String name = "Wishlist";
    private boolean publicStatus;
    private List<ShelvedBook> list = new ArrayList<>();

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

    public List<ShelvedBook> getList() {
        return list;
    }

    // setters
    public void setPublicStatus(boolean publicStatus) {
        this.publicStatus = publicStatus;
    }

    @Override
    public void addBook(SimpleShelvedBook shelvedBook) {
        this.list.add(shelvedBook);
    }

    @Override
    public void removeBook(SimpleShelvedBook shelvedBook) {
        this.list.remove(shelvedBook);
    }
}
