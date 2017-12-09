package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

import android.content.Context;
import android.util.Log;
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

    private static final String TAG = "User request sign up";
    private String email;
    private String name;
    private String password;

    public UserSignUpRequest(final Context context, final String name, final String email, final String password,
                             final Continuation<SignupInfo> success, final Continuation<String> failure){
        super(Request.Method.POST,
                ShelvedUrls.SINGLETON.getUrl(context, ShelvedUrls.Name.SIGN_UP), new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Sign up Response: " + response);

                        ResponseMessage message = GsonUtils.makeMessageGson().fromJson(response, ResponseMessage.class);


                        try {
                            Log.d(TAG, response);
                            JSONObject jObj = new JSONObject(response);
                            boolean error = !jObj.getBoolean("result");

                            if (!error) {
                                SignupInfo signupInfo = new SignupInfo(name, email, password);
                                success.run(signupInfo);

                            } else {
                                String errorMsg = jObj.getString("error_msg");
                                failure.run(errorMsg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("Error case");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        failure.run(error.getMessage());
                    }
                });



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


