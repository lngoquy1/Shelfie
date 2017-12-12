package edu.swarthmore.cs.cs71.shelved.network.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import edu.swarthmore.cs.cs71.shelved.network.*;
import edu.swarthmore.cs.cs71.shelved.network.AddBookToReadingList.InvalidBookAddedToListResponse;
import edu.swarthmore.cs.cs71.shelved.network.AddBookToReadingList.ValidBookAddedToListResponse;
import edu.swarthmore.cs.cs71.shelved.network.BookAddedToShelf.InvalidBookAddedResponse;
import edu.swarthmore.cs.cs71.shelved.network.BookAddedToShelf.ValidBookAddedResponse;
import edu.swarthmore.cs.cs71.shelved.network.BookListUpdate.ValidBookListUpdateResponse;
import edu.swarthmore.cs.cs71.shelved.network.ListAdded.InvalidListAddedResponse;
import edu.swarthmore.cs.cs71.shelved.network.ListAdded.ValidListAddedResponse;
import edu.swarthmore.cs.cs71.shelved.network.Login.InvalidLoginUserResponse;
import edu.swarthmore.cs.cs71.shelved.network.Login.ValidLoginUserResponse;
import edu.swarthmore.cs.cs71.shelved.network.ReadingListsUpdate.InvalidReadingListsUpdatedResponse;
import edu.swarthmore.cs.cs71.shelved.network.ReadingListsUpdate.ValidReadingListsUpdateResponse;

public class GsonUtils {
    public static Gson makeMessageGson(){
        RuntimeTypeAdapterFactory<ResponseMessage> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(ResponseMessage.class, "type")
                .registerSubtype(CreateUserResponse.class, "createUserResponse")
                .registerSubtype(FailureResponse.class, "failureResponse")
                .registerSubtype(ValidLoginUserResponse.class, "validLoginUserResponse")
                .registerSubtype(InvalidLoginUserResponse.class, "invalidLoginUserResponse")
                .registerSubtype(ValidBookAddedResponse.class, "validBookAddedResponse")
                .registerSubtype(InvalidBookAddedResponse.class, "invalidBookAddedResponse")
                .registerSubtype(ValidBookListUpdateResponse.class, "validBookListUpdateResponse")
                .registerSubtype(ValidListAddedResponse.class, "validListAddedResponse")
                .registerSubtype(InvalidListAddedResponse.class, "invalidListAddedResponse")
                .registerSubtype(ValidSearchResponseTitleAuthor.class, "validSearchResponseTitleAuthor")
                .registerSubtype(ValidSearchResponseISBN.class, "validSearchResponseISBN")
                .registerSubtype(InvalidSearchResponse.class, "invalidSearchResponse")
                .registerSubtype(ValidReadingListsUpdateResponse.class, "validReadingListsUpdateResponse")
                .registerSubtype(InvalidReadingListsUpdatedResponse.class, "invalidReadingListUpdatedResponse")
                .registerSubtype(ValidBookAddedToListResponse.class, "validBookAddedToListResponse")
                .registerSubtype(InvalidBookAddedToListResponse.class, "invalidBookAddedToReadingListResponse");

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
        System.out.println("CREATING GSON");
        return gson;
    }

}
