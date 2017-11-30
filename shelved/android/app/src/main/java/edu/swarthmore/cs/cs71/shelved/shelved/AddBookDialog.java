
package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import edu.swarthmore.cs.cs71.shelved.model.api.Book;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.ValidBookAddedResponse;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;


public class AddBookDialog extends AlertDialog.Builder {
    private static final String TAG = "AddBookDialog";
    public AddBookDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public AddBookDialog(Context context, final Continuation<SimpleBook> positiveContinuation) {
        super(context);

        this.setTitle("Add Book");
        Log.d(TAG, "inside newInstance");


        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleBox = new EditText(context);
        titleBox.setHint("Title");
        layout.addView(titleBox);

        final EditText authorBox = new EditText(context);
        authorBox.setHint("Author");
        layout.addView(authorBox);

        this.setView(layout);

        this.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.v("title", titleBox.getText().toString());
                Log.v("author", authorBox.getText().toString());

                String cancel_req_tag = "addBook";
                String titleString = titleBox.getText().toString();
                String authorString = authorBox.getText().toString();
                Log.d(TAG, getAddBookUrl());
                // TODO: This StringRequest is still under construction
                StringRequest strReq = new StringRequest(Request.Method.POST, getAddBookUrl(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        Log.d(TAG, "Add book response: " + response);

                        ResponseMessage message = GsonUtils.makeMessageGson().fromJson(response, ResponseMessage.class);
                        if (message.isResult()){
                            ValidBookAddedResponse bookAddedResponse = (ValidBookAddedResponse) message;
                        }
                        try {
                            Log.d(TAG, response);
                            JSONObject jObj = new JSONObject(response);
                            boolean error = !jObj.getBoolean("result");


                            if (!error) {
                                Log.d(TAG, "no error");
                                String bookTitle = jObj.getJSONObject("book").getJSONObject("title").getString("title");
                                Toast.makeText(getApplicationContext(), "You successfully added " + bookTitle, Toast.LENGTH_SHORT).show();


                            } else {
                                Log.d(TAG, "error");
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("Error case");
                        }

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Add book error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
//                        hideDialog(progressDialog);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("title", titleBox.getText().toString());
                        params.put("author", authorBox.getText().toString());
                        return params;
                    }
                };
                // Adding request to request queue
                // TODO: Context is wrong, strReq never gets accessed
                AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
                SimpleBook newBook = new SimpleBook();
                newBook.setAuthor(authorString);
                newBook.setTitle(titleString);
                positiveContinuation.run(newBook);
            }
        });


        this.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

    }
    private String getAddBookUrl() {
        //AppCompatActivity act = new AppCompatActivity();
        return "http://"+getApplicationContext().getResources().getString((R.string.server_url))+":4567/addBook";


    }


<<<<<<< HEAD



=======
>>>>>>> dd037bd24a5cca45d17dcba1b1b3de654a1a3e8d
}

