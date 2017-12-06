package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.ValidSearchResponseISBN;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;
import edu.swarthmore.cs.cs71.shelved.shelved.AppSingleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchByISBNRequest extends StringRequest {

    private static final String TAG = "Search Book by ISBN";

    public SearchByISBNRequest(final Context context, final ShelvedModel shelvedModel, final String ISBN) {
        super(Request.Method.POST, ShelvedUrls.SINGLETON.getUrl(context, ShelvedUrls.Name.SEARCH_BOOK_BY_ISBN),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Search book by ISBN response: " + response);
                        ResponseMessage message = GsonUtils.makeMessageGson().fromJson(response, ResponseMessage.class);

                        if (message.isResult()){
                            ValidSearchResponseISBN searchResponseISBN = (ValidSearchResponseISBN) message;
                        }
                        try {
                            Log.d(TAG, response);
                            JSONObject jObj = new JSONObject(response);
                            boolean error = !jObj.getBoolean("result");


                            if (!error) {
                                Log.d(TAG, "no error");
                                Toast.makeText(context, "Results for ISBN "+ISBN, Toast.LENGTH_SHORT).show();
                                Gson gson = new Gson();
                                SimpleBook book = gson.fromJson(jObj.getJSONObject("book").toString(), SimpleBook.class);
                                AppSingleton.getInstance(context).getModel(context).

                            } else {
                                Log.d(TAG, "error");
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(context,
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
                        Toast.makeText(context,
                                error.getMessage(), Toast.LENGTH_LONG).show();
//                        hideDialog(progressDialog);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("isbn", ISBN);
                return params;
            }
        };
    }
}
