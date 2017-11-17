package edu.swarthmore.cs.cs71.shelved.network;

public abstract class ResponseMessage {
    private boolean result;
    private ResponseMessage(){}
    public ResponseMessage(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}
