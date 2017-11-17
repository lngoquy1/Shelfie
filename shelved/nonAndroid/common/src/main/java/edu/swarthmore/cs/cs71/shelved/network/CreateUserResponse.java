package edu.swarthmore.cs.cs71.shelved.network;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleUser;

public class CreateUserResponse extends ResponseMessage{
    private SimpleUser user;
    private CreateUserResponse(){
        super(true);
    }
    public CreateUserResponse(SimpleUser user) {
        super(true);
        this.user = user;
    }

    public SimpleUser getUser() {
        return user;
    }
}
