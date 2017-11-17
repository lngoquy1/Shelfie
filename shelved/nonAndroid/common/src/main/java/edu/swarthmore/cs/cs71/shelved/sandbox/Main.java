package edu.swarthmore.cs.cs71.shelved.sandbox;

import com.google.gson.Gson;
import edu.swarthmore.cs.cs71.shelved.model.api.Genre;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleGenre;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleUser;
import edu.swarthmore.cs.cs71.shelved.network.CreateUserResponse;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;

public class Main {
    public static void main(String[] args) {
        SimpleUser user = new SimpleUser();
        user.setUserName("lan");
        ResponseMessage message = new CreateUserResponse(user);
        String s = new Gson().toJson(message);
        System.out.println(s);
        ResponseMessage deserialized = new Gson().fromJson(s, ResponseMessage.class);
        System.out.println(deserialized);
    }
}
