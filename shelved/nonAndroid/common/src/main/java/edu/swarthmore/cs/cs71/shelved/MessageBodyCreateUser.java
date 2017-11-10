package edu.swarthmore.cs.cs71.shelved;

import com.google.gson.Gson;

public class MessageBodyCreateUser implements MessageBody {
    public static final MessageBodyCreateUser SINGLETON = new MessageBodyCreateUser();
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
