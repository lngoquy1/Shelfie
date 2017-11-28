
package edu.swarthmore.cs.cs71.shelved.shelved;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import edu.swarthmore.cs.cs71.shelved.model.api.Book;
import edu.swarthmore.cs.cs71.shelved.network.ValidBookAddedResponse;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;


public class AddBookDialog extends AlertDialog.Builder {
    private static final String TAG = "AddBookDialog";

    private String getAddBookUrl() {
        AppCompatActivity act = new AppCompatActivity();
        return "http://"+act.getString((R.string.server_url))+":4567/addBook";

    }

    public AddBookDialog(Context context) {
        super(context);
    }

    public AddBookDialog newInstance() {
        Context context = getContext();
        AddBookDialog alert = new AddBookDialog(context);
        alert.setTitle("Add Book");


        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleBox = new EditText(context);
        titleBox.setHint("Title");
        layout.addView(titleBox);

        final EditText authorBox = new EditText(context);
        authorBox.setHint("Author");
        layout.addView(authorBox);

        alert.setView(layout);

        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.v("title", titleBox.getText().toString());
                Log.v("author", authorBox.getText().toString());


                String titleString = titleBox.getText().toString();
                String authorString = authorBox.getText().toString();
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
                                String bookTitle = jObj.getJSONObject("book").getJSONObject("title").getString("title");
                                Toast.makeText(getApplicationContext(), "You successfully added " + bookTitle, Toast.LENGTH_SHORT).show();

                                // Launch login activity
//                                Intent intent = new Intent(
//                                        SignupActivity.this,
//                                        LoginActivity.class);
//                                startActivity(intent);
//                                finish();
                            } else {

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
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        return alert;
    }





    public AddBookDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
}

