package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

public interface BookAddedListener {
    public void bookAdded(int userID, SimpleBook book);
}
