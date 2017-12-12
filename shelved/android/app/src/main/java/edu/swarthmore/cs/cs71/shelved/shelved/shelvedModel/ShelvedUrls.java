package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

import android.content.Context;
import edu.swarthmore.cs.cs71.shelved.shelved.R;

public class ShelvedUrls {
    public static final ShelvedUrls SINGLETON = new ShelvedUrls();

    public static enum Name {
        SIGN_UP("/signup"),
        LOG_IN("/login"),
        ADD_BOOK("/addBook"),
        GET_BOOK_LIST("/updateBook"),
        SEARCH_ISBN("/searchByISBN"),
        SEARCH_TITLE_AUTHOR("/searchByTitleAuthor"),
        ADD_LIST("/addList"),
        GET_LISTS("/updateList"),
        ADD_BOOK_TO_LIST("/addBookToList"),
        GET_SINGLE_LIST("/getSingleList");
        // TODO: chagne update book to say getBookList

        private String path;

        public String getPath() {
            return path;
        }

        Name(String path) {
            this.path = path;
        }
    }

    public String getUrl(Context context, Name name) {
        return "http://"+context.getResources().getString((R.string.server_url))+":4567" + name.getPath();
    }
}
