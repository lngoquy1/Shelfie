
package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;

import java.util.HashMap;
import java.util.Map;

public class AddBookDialog extends AlertDialog.Builder {
    TextView _loginLink;

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
                String authoString = authorBox.getText().toString();

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        return alert;
    }

    protected Map<String, String> getParams() {
        // Posting params to signup url
        Map<String, String> params = new HashMap<String, String>();
        params.put("title", titleBox);
        params.put("author", authorBox);
        params.put("genre", genre);
        params.put("pages", pages);
        params.put("publisher", publisher);
        return params;
    }

    public AddBookDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
}

