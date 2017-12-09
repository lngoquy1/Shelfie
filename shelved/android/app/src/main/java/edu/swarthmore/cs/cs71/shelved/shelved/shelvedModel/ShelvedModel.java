package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import com.android.volley.toolbox.StringRequest;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleReadingList;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleUser;
import edu.swarthmore.cs.cs71.shelved.shelved.AppSingleton;
import edu.swarthmore.cs.cs71.shelved.shelved.Continuation;
import edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel.BookAddedListener;
import edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel.GetBookFromISBNRequest;
import edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel.ShelfUpdatedListener;
import edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel.SignUpSuccessListener;

import java.util.*;

public class ShelvedModel {
    private List<SimpleBook> bookList = new ArrayList<SimpleBook>();
    private List<SimpleReadingList> readingLists = new ArrayList<SimpleReadingList>();
    public static int userID;
    public static String token;
    // listener fields
    Set<SignUpSuccessListener> signUpSuccessListeners = new HashSet<SignUpSuccessListener>();
    Set<LogInAttemptListener> logInAttemptListeners = new HashSet<LogInAttemptListener>();
    Set<LogInSuccessListener> logInSuccessListeners = new HashSet<LogInSuccessListener>();
    Set<ShelfUpdatedListener> shelfUpdatedListeners = new HashSet<ShelfUpdatedListener>();
    Set<BookAddedListener> bookAddedListeners = new HashSet<BookAddedListener>();
    Set<ListsUpdatedListener> listsUpdatedListeners = new HashSet<ListsUpdatedListener>();
    Set<ListAddedListener> listAddedListeners = new HashSet<ListAddedListener>();



    ////////////// Getter methods ///////////////////////
    public List<SimpleBook> getBookList() {
        return Collections.unmodifiableList(bookList);
    }

    public List<SimpleReadingList> getLists() {
        //return Collections.unmodifiableList(readingLists);
        return this.readingLists;
    }

    public static int getUserID() {
        return userID;
    }

    public static String getToken() {
        return token;
    }

    // TODO: Need to make a method to login
    public void signUp(String userName, String email, String password, ProgressDialog progressDialog){
        notifySignUpSuccessListeners(userName, email, password, progressDialog);
    }


    public void logIn(Context context, final Continuation<LoginInfo> success,
                      final Continuation<String> failure, String email, String password){
        Log.d("in Log in", "local success ...");
        Continuation<LoginInfo> localSuccess = new Continuation<LoginInfo>() {
            public void run(LoginInfo info) {
                ShelvedModel.userID = info.getUserID();
                ShelvedModel.token = info.getToken();
                success.run(info);
                Log.d("getting token: ", ShelvedModel.token);
                notifyLogInSuccessListener();
            }
        };
        StringRequest strReq = new UserLogInRequest(context, email, password, localSuccess, failure);
        AppSingleton.getInstance(context).addToRequestQueue(strReq, "Log In Request");

    }




    public void addBook(SimpleBook book) {
        bookList.add(book);
        notifyShelfUpdatedListeners();
        notifyBookAddedListeners(book);
    }

    public void removeBook(SimpleBook book) {
        bookList.remove(book);
        notifyShelfUpdatedListeners();
    }

    public void setShelf(List<SimpleBook> newBookList) {
        this.bookList.clear();
        this.bookList.addAll(newBookList);
        notifyShelfUpdatedListeners();
    }

    public void addList(SimpleReadingList list) {
        this.readingLists.add(list);
        notifyListUpdatedListeners();
        notifyListAddedListeners(list);
    }

    public void removeList(SimpleReadingList list) {
        readingLists.remove(list);
        notifyListUpdatedListeners();
    }

    public void setLists(List<SimpleReadingList> newReadingListList) {
        this.readingLists.clear();
        this.readingLists.addAll(newReadingListList);
        notifyListUpdatedListeners();
    }


    ///////////////// Shelf Listeners/ updaters /////////////////

    public void addShelfUpdatedListener(ShelfUpdatedListener newShelfUpdatedListener) {
        shelfUpdatedListeners.add(newShelfUpdatedListener);
    }

    private void notifyShelfUpdatedListeners() {
        for (ShelfUpdatedListener listener:this.shelfUpdatedListeners) {
            listener.shelfUpdated();
        }
    }


    ///////////////// ReadingList Listeners/ updaters /////////////////

    public void addListsUpdatedListener(ListsUpdatedListener newListsUpdatedListener) {
        listsUpdatedListeners.add(newListsUpdatedListener);
    }

    private void notifyListUpdatedListeners() {
        for (ListsUpdatedListener listener:this.listsUpdatedListeners) {
            listener.listsUpdated();
        }
    }

    public void addListAddedListener(ListAddedListener newListAddedListener) {
        listAddedListeners.add(newListAddedListener);
    }

    public void notifyListAddedListeners(SimpleReadingList list) {
        for (ListAddedListener listener:this.listAddedListeners) {
            listener.listAdded(list);
        }
    }

    ///////////////// Add book Listeners/ updaters /////////////////

    public void addBookAddedListener(BookAddedListener newBookAddedListener) {
        bookAddedListeners.add(newBookAddedListener);
    }

    private void notifyBookAddedListeners(SimpleBook book) {
        for (BookAddedListener listener:this.bookAddedListeners) {
            listener.bookAdded(book);
        }
    }

    ///////////////// Sign up success Listeners /////////////////
    public void addSignUpSuccessListeners(SignUpSuccessListener signUpSuccessListener){
        signUpSuccessListeners.add(signUpSuccessListener);
    }
    private void notifySignUpSuccessListeners(String userName, String email, String password, ProgressDialog progressDialog){
        for (SignUpSuccessListener listener:this.signUpSuccessListeners){
            listener.onSignUpSuccess(userName, email, password, progressDialog);
        }
    }
    public void removeAllSignUpSuccessListeners(){
        signUpSuccessListeners.clear();
    }


    ///////////////// Sign up attempt Listeners /////////////////
    public void addLogInSuccessListeners(LogInSuccessListener logInSuccessListener){
        logInSuccessListeners.add(logInSuccessListener);
    }
    public void notifyLogInSuccessListener(){
        for (LogInSuccessListener listener: logInSuccessListeners){
            listener.onLogInSuccess();
        }
    }
    public void removeAllLogInSuccessListeners(){ logInSuccessListeners.clear();}
    ///////////////// Scan Listeners/ updaters /////////////////








    public void searchByISBN(Context context, String ISBN, Continuation<SimpleBook> continuation) {
        StringRequest strReq = new GetBookFromISBNRequest(context, ISBN, continuation);
        // Adding request to request queue
        Log.d("testing","about to add to queue");
        AppSingleton.getInstance(context).addToRequestQueue(strReq, "addSearchByISBN");
        Log.d("testing","Finished adding to queue");
    }
}
