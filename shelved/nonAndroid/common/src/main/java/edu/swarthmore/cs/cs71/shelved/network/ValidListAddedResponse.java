package edu.swarthmore.cs.cs71.shelved.network;

import edu.swarthmore.cs.cs71.shelved.model.simple.ReadingList;

public class ValidListAddedResponse extends ResponseMessage {
    private ReadingList list;
    private ValidListAddedResponse() { super(true); }
    public ValidListAddedResponse(ReadingList list) {
        super(true);
        this.list = list;
    }

    public ReadingList getList() { return list; }

}
