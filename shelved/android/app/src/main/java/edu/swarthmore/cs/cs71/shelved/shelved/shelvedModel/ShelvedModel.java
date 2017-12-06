package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

import android.app.ProgressDialog;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleUser;

import java.util.*;

public class ShelvedModel {
    private List<SimpleBook> bookList = new ArrayList<SimpleBook>();
    private Integer userID = null;
    private String token = null;
    // listener fields
    Set<SignUpSuccessListener> signUpSuccessListeners = new HashSet<SignUpSuccessListener>();
    Set<ShelfUpdatedListener> shelfUpdatedListeners = new HashSet<ShelfUpdatedListener>();
    Set<BookAddedListener> bookAddedListeners = new HashSet<BookAddedListener>();

    // TODO: Need to make a method to login
    public void signUp(String userName, String email, String password, ProgressDialog progressDialog){
        notifySignUpSuccessListeners(userName, email, password, progressDialog);
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



    ///////////////// Shelf Listeners/ updaters /////////////////

    public void addShelfUpdatedListener(ShelfUpdatedListener newShelfUpdatedListener) {
        shelfUpdatedListeners.add(newShelfUpdatedListener);
    }

    private void notifyShelfUpdatedListeners() {
        for (ShelfUpdatedListener listener:this.shelfUpdatedListeners) {
            listener.shelfUpdated();
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
            listener.onSignUpSucceed(userName, email, password, progressDialog);
        }
    }
    public void removeAllSignUpSuccessListeners(){
        signUpSuccessListeners.clear();
    }


    ///////////////// Scan Listeners/ updaters /////////////////




    // getters

    public List<SimpleBook> getBookList() {
        return Collections.unmodifiableList(bookList);
    }

    public Integer getUserID() {
        return userID;
    }
}
