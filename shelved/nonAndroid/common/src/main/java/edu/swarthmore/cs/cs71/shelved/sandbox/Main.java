package edu.swarthmore.cs.cs71.shelved.sandbox;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleUser;
import edu.swarthmore.cs.cs71.shelved.network.CreateUserResponse;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;

public class Main {
    public static void main(String[] args) {
        SimpleUser user = new SimpleUser();
        user.setEmail("lan");
        ResponseMessage message = new CreateUserResponse(user);
        String s = new GsonUtils().makeMessageGson().toJson(message);
        System.out.println(s);
//        Type listType = new TypeToken<ResponseMessage>(){}.getType();
        ResponseMessage deserialized = new GsonUtils().makeMessageGson().fromJson(s, ResponseMessage.class);
        String serialized = new GsonUtils().makeMessageGson().toJson(deserialized);
        System.out.println(serialized);
    }
}
