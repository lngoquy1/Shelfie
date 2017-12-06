package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

import android.app.ProgressDialog;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleUser;

public interface SignUpSuccessListener {
    void onSignUpSucceed(String userName, String email, String password, ProgressDialog progressDialog);
}
