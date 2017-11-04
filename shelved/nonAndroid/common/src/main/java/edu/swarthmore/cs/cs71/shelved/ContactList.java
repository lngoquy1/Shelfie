package edu.swarthmore.cs.cs71.shelved;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactList {
    // List or array list???
    private Set<User> contactList;

    public ContactList() {
        this.contactList = new HashSet<>();
    }

    public void addContact(User user) {
        contactList.add(user);
    }

    public Set<User> getContactList() {
        return contactList;
    }
}
