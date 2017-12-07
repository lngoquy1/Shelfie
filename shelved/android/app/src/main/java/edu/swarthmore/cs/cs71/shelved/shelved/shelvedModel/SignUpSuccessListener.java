package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

import android.app.ProgressDialog;

public interface SignUpSuccessListener {
    void onSignUpSuccess(String userName, String email, String password, ProgressDialog progressDialog);
}
