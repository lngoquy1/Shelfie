package edu.swarthmore.cs.cs71.shelved.network.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import edu.swarthmore.cs.cs71.shelved.network.*;

public class GsonUtils {
    public static Gson makeMessageGson(){
        RuntimeTypeAdapterFactory<ResponseMessage> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(ResponseMessage.class, "type")
                .registerSubtype(CreateUserResponse.class, "createUserResponse")
                .registerSubtype(FailureResponse.class, "failureResponse")
                .registerSubtype(ValidLoginUserResponse.class, "validLoginUserResponse")
                .registerSubtype(InvalidLoginUserResponse.class, "invalidLoginUserResponse")
                .registerSubtype(ValidBookAddedResponse.class, "validBookAddedResponse")
                .registerSubtype(InvalidBookAddedResponse.class, "invalidBookAddedResponse");
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
        return gson;
    }

}
