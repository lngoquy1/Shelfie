package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.ValidBookInfoReqResponse;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;
import edu.swarthmore.cs.cs71.shelved.shelved.AppSingleton;
import edu.swarthmore.cs.cs71.shelved.shelved.ScannerActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// TODO TODO TODO TODO
// check on _author _title ask zac
// figure out where do get author and title from ISBN
// probably rename this class because its not actually adding the book


public class AddBookScanStringRequest extends StringRequest {
    private final ShelvedModel shelvedModel;
    private final SimpleBook book;
    private static final String TAG = "Add scan string request";

    public AddBookScanStringRequest(final Context context, final ShelvedModel shelvedModel, SimpleBook book) {
        super(Request.Method.POST, ShelvedUrls.SINGLETON.getUrl(context, ShelvedUrls.Name.ADD_BOOK_SCAN), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "addScanResp: " + response);

                ResponseMessage message = GsonUtils.makeMessageGson().fromJson(response, ResponseMessage.class);
                if (message.isResult()) {
                    ValidBookInfoReqResponse bookInfoReqResponse = (ValidBookInfoReqResponse) message;
                }
                try {
                    Log.d(TAG, response);
                    JSONObject jObj = new JSONObject(response);
                    boolean error = !jObj.getBoolean("result");

                    // TODO: move Toasts ?
                    if (!error) {
                        Log.d(TAG, "no error");
                        String bookTitle = jObj.getJSONObject("book").getJSONObject("title").getString("title");
                        String bookAuthor = jObj.getJSONObject("book").getJSONObject("author").getString("title");
                        //_Author.setText(bookAuthor);
                        //_Title.setText(bookTitle);

                        GetBookListStringRequest getBookListStringRequest = new GetBookListStringRequest(context, shelvedModel);
                        AppSingleton.getInstance(context).addToRequestQueue(getBookListStringRequest, "get book list");
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
            }
        });
        this.shelvedModel = shelvedModel;
        this.book = book;
    }

    @Override
    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ISBN", ScannerActivity.getISBN());
        return params;
    }
}

