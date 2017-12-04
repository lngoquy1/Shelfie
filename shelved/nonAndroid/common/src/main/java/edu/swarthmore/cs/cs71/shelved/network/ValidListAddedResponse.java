package edu.swarthmore.cs.cs71.shelved.network;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleReadingList;

public class ValidListAddedResponse extends ResponseMessage {
    private SimpleReadingList list;
    private ValidListAddedResponse() { super(true); }
    public ValidListAddedResponse(SimpleReadingList list) {
        super(true);
        this.list = list;
    }

    public SimpleReadingList getList() { return list; }

}
