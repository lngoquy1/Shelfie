package edu.swarthmore.cs.cs71.shelved;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactList {
    // List or array list???
    private Set<User> contactList = new HashSet<>();

    public ContactList() {
    }

    public void addContact(User user) {
        this.contactList.add(user);
    }

    public void removeContact(User user) {
        this.contactList.remove(user);
    }

    public Set<User> getContactList() {
        return contactList;
    }
}
