package edu.swarthmore.cs.cs71.shelved.model.simple;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.swarthmore.cs.cs71.shelved.model.api.MessageBody;
import edu.swarthmore.cs.cs71.shelved.model.api.User;

import java.lang.reflect.Type;

public class SimpleMessageBody implements MessageBody {
    //private User user;
    public SimpleMessageBody(){

    }

    //public void setUser(User user) {
    //    this.user = user;
    //}

    @Override
    public String createJSON(Object object) {
        Gson gson = new Gson();
        Appendable output = new StringBuffer();
        gson.toJson(object, output);
        return output.toString();
    }

    @Override
    public Object objectFromJSON(String json, Type typeOfT) {
        Gson gson = new Gson();
        String userJsonString = gson.toJson(json);
        return gson.fromJson(userJsonString, typeOfT);
    }

}
