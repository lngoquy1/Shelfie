package edu.swarthmore.cs.cs71.shelved.network.ReadingListsUpdate;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleReadingList;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;

import java.util.List;

public class ValidReadingListsUpdateResponse extends ResponseMessage {
    private List<SimpleReadingList> readingLists;
    private ValidReadingListsUpdateResponse(){super(true);}

    public ValidReadingListsUpdateResponse(List<SimpleReadingList> reaadingLists) {
        super(true);
        this.readingLists = reaadingLists;
    }

    public List<SimpleReadingList> getReadingLists() { return readingLists; }
}
