package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

import android.app.ProgressDialog;

public interface LogInAttemptListener {
    void onLogInAttempt(String email, String password, ProgressDialog progressDialog);
}
