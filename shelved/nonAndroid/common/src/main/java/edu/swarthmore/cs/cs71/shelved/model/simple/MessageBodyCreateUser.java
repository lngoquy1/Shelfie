package edu.swarthmore.cs.cs71.shelved.model.simple;

import com.google.gson.Gson;
import edu.swarthmore.cs.cs71.shelved.model.api.MessageBody;
import edu.swarthmore.cs.cs71.shelved.model.api.User;

public class MessageBodyCreateUser implements MessageBody {
    private User user;
    public MessageBodyCreateUser(){

    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String createJSON() {
        Gson gson = new Gson();
        Appendable output = new StringBuffer();
        gson.toJson(this.user, output);
        return output.toString();
    }
}
