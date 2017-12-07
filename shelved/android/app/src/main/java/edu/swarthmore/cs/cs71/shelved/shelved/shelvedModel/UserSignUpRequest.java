package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;
import edu.swarthmore.cs.cs71.shelved.shelved.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

public class UserSignUpRequest extends StringRequest {
    private final ShelvedModel shelvedModel;
    private static final String TAG = "User request sign up";
    private String email;
    private String name;
    private String password;

    public UserSignUpRequest(final Context context, final ShelvedModel shelvedModel, String name, String email, String password, final ProgressDialog progressDialog){
        super(Request.Method.POST,
                ShelvedUrls.SINGLETON.getUrl(context, ShelvedUrls.Name.SIGN_UP), new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Sign up Response: " + response);
                        AppSingleton.getInstance(context).hideDialog(progressDialog);

                        ResponseMessage message = GsonUtils.makeMessageGson().fromJson(response, ResponseMessage.class);


                        try {
                            Log.d(TAG, response);
                            JSONObject jObj = new JSONObject(response);
                            boolean error = !jObj.getBoolean("result");


                            if (!error) {
                                String userName = jObj.getJSONObject("user").getJSONObject("simpleEmail").getString("userName");
                                Toast.makeText(getApplicationContext(), "Hi " + userName +", You have successfully signed up!", Toast.LENGTH_SHORT).show();
                                // Switch to login page
//                                AppSingleton.getInstance(context).addSignUpSuccessNetworkListeners(context, shelvedModel);

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
                        Log.e(TAG, "Registration Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        AppSingleton.getInstance(context).hideDialog(progressDialog);
                    }
                });
            this.shelvedModel = shelvedModel;


            this.email = email;
            this.name = name;
            this.password = password;

        }

    @Override
    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
        return params;
    }

}


