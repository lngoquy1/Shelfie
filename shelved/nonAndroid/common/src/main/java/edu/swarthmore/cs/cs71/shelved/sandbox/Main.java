package edu.swarthmore.cs.cs71.shelved.sandbox;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleUser;
import edu.swarthmore.cs.cs71.shelved.network.CreateUserResponse;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;

import java.lang.reflect.Type;

public class Main {
    public static void main(String[] args) {
        SimpleUser user = new SimpleUser();
        user.setUserName("lan");
        ResponseMessage message = new CreateUserResponse(user);
        Gson gson = new GsonUtils().makeMessageGson();
        String s = gson.toJson(message);
        Type listType = new TypeToken<ResponseMessage>(){}.getType();
        ResponseMessage deserialized = gson.fromJson(s, listType);
        System.out.println(deserialized);
    }
}
