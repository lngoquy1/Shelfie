package edu.swarthmore.cs.cs71.shelved.network.ListAdded;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleReadingList;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;

public class ValidListAddedResponse extends ResponseMessage {
    private SimpleReadingList list;
    private ValidListAddedResponse() { super(true); }
    public ValidListAddedResponse(SimpleReadingList list) {
        super(true);
        this.list = list;
    }

    public SimpleReadingList getList() { return list; }

}
