package edu.swarthmore.cs.cs71.shelved.shelved;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel.*;
import android.app.ProgressDialog;

import java.util.HashSet;
import java.util.Set;

public class AppSingleton {
    private static AppSingleton mAppSingletonInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;
    private ShelvedModel model;

    private AppSingleton(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized AppSingleton getInstance(Context context) {
        if (mAppSingletonInstance == null) {
            mAppSingletonInstance = new AppSingleton(context);
        }
        return mAppSingletonInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public ShelvedModel getModel(Context context) {
        if (model == null) {
            model = new ShelvedModel();
            addBookNetworkListeners(context, model);
//            addScanNetworkListeners(context, model);
        }
        return model;
    }

    public SearchViewModel setupSearchViewModel(Context context, SearchViewModel searchViewModel) {
        addScanNetworkListeners(context, searchViewModel);
        return searchViewModel;
    }

    private void addBookNetworkListeners(final Context context, final ShelvedModel shelvedModel) {
        shelvedModel.addBookAddedListener(new BookAddedListener() {
            @Override
            public void bookAdded(final SimpleBook book) {
                StringRequest strReq = new AddBookStringRequest(context, shelvedModel, book);
                // Adding request to request queue
                AppSingleton.getInstance(context).addToRequestQueue(strReq, "addBook");
            }
        });
    }


    public void addScanNetworkListeners(final Context context, final SearchViewModel searchViewModel) {
        searchViewModel.addScanListener(new ScanAddedListener() {
            @Override
            public void scanAdded(String ISBN) {
                StringRequest strReq = new GetBookFromISBNRequest(context, ISBN, searchViewModel);
                // Adding request to request queue
                AppSingleton.getInstance(context).addToRequestQueue(strReq, "addScan");
            }
        });
    }


    ///////////////// Sign up success Listeners /////////////////

    public void addSignUpSuccessNetworkListeners(final Context context, final ShelvedModel shelvedModel){
        shelvedModel.addSignUpSuccessListeners(new SignUpSuccessListener() {
            @Override
            public void onSignUpSucceed(String userName, String email, String password, ProgressDialog progressDialog) {
                StringRequest strReq = new UserSignUpRequest(context, shelvedModel, userName, email, password, progressDialog);
                AppSingleton.getInstance(context).addToRequestQueue(strReq, "signUp");
                Intent intent = new Intent(
                        context,
                        LoginActivity.class);
                context.startActivity(intent);
            }
        });
    }
    //////////////// Hide progress dialog /////////
    public void hideDialog(ProgressDialog progressDialog) {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
