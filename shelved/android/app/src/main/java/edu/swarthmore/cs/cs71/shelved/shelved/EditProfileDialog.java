package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleUser;

public class EditProfileDialog extends AlertDialog.Builder {

    private SimpleUser user;

    public EditProfileDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public EditProfileDialog(Context context) {
        super(context);
        this.setTitle("Edit Profile");

        user = new SimpleUser();
        user.setName("Your Full Name");

        LinearLayout main = new LinearLayout(context);
        main.setOrientation(LinearLayout.VERTICAL);

        LinearLayout name = new LinearLayout(context);
        name.setOrientation(LinearLayout.HORIZONTAL);
        main.addView(name);

        TextView nameText = new TextView(context);
        nameText.setText("Name");
        name.addView(nameText);

        EditText nameEdit = new EditText(context);
        nameEdit.setHint(user.getName());
        name.addView(nameEdit);

        LinearLayout username = new LinearLayout(context);
        username.setOrientation(LinearLayout.HORIZONTAL);
        main.addView(username);

        TextView usernameText = new TextView(context);
        usernameText.setText("Username");
        username.addView(usernameText);

        EditText usernameEdit = new EditText(context);
        usernameEdit.setHint(user.getName());
        username.addView(usernameEdit);

        LinearLayout picture = new LinearLayout(context);
        picture.setOrientation(LinearLayout.HORIZONTAL);
        main.addView(picture);

        this.setView(main);

        this.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.v("profile dialog", "submit");

            }
        });

        this.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.v("profile dialog", "cancel");
            }
        });
    }
}
