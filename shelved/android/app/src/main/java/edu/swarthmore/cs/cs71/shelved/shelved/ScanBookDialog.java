//
//package edu.swarthmore.cs.cs71.shelved.shelved;
//
//import android.app.FragmentTransaction;
//import android.content.Intent;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.util.Log;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import edu.swarthmore.cs.cs71.shelved.model.api.Book;
//import edu.swarthmore.cs.cs71.shelved.network.ValidBookAddedResponse;
//import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
//import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.facebook.FacebookSdk.getApplicationContext;
//
//public class ScanBookDialog {
//
//    private static final String TAG = "ScanBookDialog";
//
////    private String getAddBookUrl() {
////        //AppCompatActivity act = new AppCompatActivity();
////        return "http://"+getApplicationContext().getResources().getString((R.string.server_url))+":4567/addBook";
////
////
////    }
//
//    public ScanBookDialog(Context context) {
//        super(context);
//    }
//
//    public ScanBookDialog newInstance() {
//        final Context context = getContext();
//        final ScanBookDialog alert = new ScanBookDialog(context);
//        alert.setTitle("Add Scanned Book");
//        Log.d(TAG, "inside newInstance");
//
//
//        LinearLayout layout = new LinearLayout(context);
//        layout.setOrientation(LinearLayout.VERTICAL);
//
//        final EditText titleBox = new EditText(context);
//        titleBox.setHint("Title");
//        layout.addView(titleBox);
//
//        final EditText authorBox = new EditText(context);
//        authorBox.setHint("Author");
//        layout.addView(authorBox);
//
//        alert.setView(layout);
//
//        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                // Canceled.
//            }
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
//
//    public ScanBookDialog(Context context, int themeResId) {
//        super(context, themeResId);
//    }
//}