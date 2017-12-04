package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
//import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleReadingList;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;
import edu.swarthmore.cs.cs71.shelved.network.ValidListAddedResponse;
import edu.swarthmore.cs.cs71.shelved.network.InvalidListAddedResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddListDialog extends AlertDialog.Builder {
    private static final String TAG = "AddListDialog";
    public AddListDialog(Context context) {
        super(context);
    }


    public AddListDialog newInstance() {
        Context context = getContext();
        AddListDialog alert = new AddListDialog(context);
        alert.setTitle("Add List");


        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText listNameBox = new EditText(context);
        listNameBox.setHint("Title");
        layout.addView(listNameBox);

//        final EditText authorBox = new EditText(context);
//        authorBox.setHint("Author");
//        layout.addView(authorBox);

        final Switch status = new Switch(context);
        status.setText("Public?");
        status.setShowText(true);
        status.setTextOn("Yes");
        status.setTextOff("No");
        layout.addView(status);

        alert.setView(layout);

        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.v("name", listNameBox.getText().toString());
                Log.v("public", status.getText().toString());

                String cancel_req_tag = "addList";
                String listNameString = listNameBox.getText().toString();
                String statusString = status.getText().toString();


                StringRequest strReq = new StringRequest(Request.Method.POST, getAddListUrl(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Add list response: " + response);

                        ResponseMessage message = GsonUtils.makeMessageGson().fromJson(response, ResponseMessage.class);
                        if (message.isResult()) {
                            ValidListAddedResponse listAddedResponse = (ValidListAddedResponse) message;
                        }
                        try {
                            Log.d(TAG, response);
                            JSONObject jObj = new JSONObject(response);
                            boolean error = !jObj.getBoolean("result");


                            if (!error) {
                                Log.d(TAG, "no error");
                                //String listName = jObj.getJSONObject("list").getJSONObject("name").getString("name");
                                //Toast.makeText(getContext(), "You successfully added " + bookTitle, Toast.LENGTH_SHORT).show();

                            } else {
                                Log.d(TAG, "error");
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getContext(),
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
                        Log.e(TAG, "Add list error: " + error.getMessage());
                        Toast.makeText(getContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
//                        hideDialog(progressDialog);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        //params.put("userID", AddBookDialog.this.userID);
                        params.put("ListName", listNameBox.getText().toString());
                        params.put("publicStatus", status.getText().toString());
                        return params;
                    }
                };

                AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, cancel_req_tag);
            }

        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        return alert;
    }
    // TODO: need userID in the constructor
//    public AddListDialog(Context context, final Continuation<SimpleReadingList> positiveContinuation) {
//        super(context);
//        this.setTitle("Add List");
//
//        LinearLayout layout = new LinearLayout(context);
//        layout.setOrientation(LinearLayout.VERTICAL);
//
//        final EditText listNameBox = new EditText(context);
//        listNameBox.setHint("Title");
//        layout.addView(listNameBox);
//
////        final EditText authorBox = new EditText(context);
////        authorBox.setHint("Author");
////        layout.addView(authorBox);
//
//        final Switch status = new Switch(context);
//        status.setText("Public?");
//        status.setShowText(true);
//        status.setTextOn("Yes");
//        status.setTextOff("No");
//        layout.addView(status);
//
//        alert.setView(layout);
//
//        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                Log.v("name", listNameBox.getText().toString());
//                Log.v("public", status.getText().toString());
//
//                String cancel_req_tag = "addList";
//                String listNameString = listNameBox.getText().toString();
//                String statusString = status.getText().toString();
//
//
//                StringRequest strReq = new StringRequest(Request.Method.POST, getAddListUrl(), new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d(TAG, "Add list response: " + response);
//
//                        ResponseMessage message = GsonUtils.makeMessageGson().fromJson(response, ResponseMessage.class);
//                        if (message.isResult()) {
//                            ValidListAddedResponse listAddedResponse = (ValidListAddedResponse) message;
//                        }
//                        try {
//                            Log.d(TAG, response);
//                            JSONObject jObj = new JSONObject(response);
//                            boolean error = !jObj.getBoolean("result");
//
//
//                            if (!error) {
//                                Log.d(TAG, "no error");
//                                //String listName = jObj.getJSONObject("list").getJSONObject("name").getString("name");
//                                //Toast.makeText(getContext(), "You successfully added " + bookTitle, Toast.LENGTH_SHORT).show();
//
//                            } else {
//                                Log.d(TAG, "error");
//                                String errorMsg = jObj.getString("error_msg");
//                                Toast.makeText(getContext(),
//                                        errorMsg, Toast.LENGTH_LONG).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            System.out.println("Error case");
//                        }
//
//                    }
//
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Add list error: " + error.getMessage());
//                        Toast.makeText(getContext(),
//                                error.getMessage(), Toast.LENGTH_LONG).show();
////                        hideDialog(progressDialog);
//                    }
//                }) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String, String> params = new HashMap<String, String>();
//                        //params.put("userID", AddBookDialog.this.userID);
//                        params.put("ListName", listNameBox.getText().toString());
//                        params.put("publicStatus", status.getText().toString());
//                        return params;
//                    }
//                };
//
//                AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, cancel_req_tag);
//            }
//
//        });
//
//        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                // Canceled.
//            }
//        });
//
//        return alert;
//    }
    private String getAddListUrl() {
        //AppCompatActivity act = new AppCompatActivity();
        return "http://"+getContext().getResources().getString((R.string.server_url))+":4567/addList";
    }


    public AddListDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
}
