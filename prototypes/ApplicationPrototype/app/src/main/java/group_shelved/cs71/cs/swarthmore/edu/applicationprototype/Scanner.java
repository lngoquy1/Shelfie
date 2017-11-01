package group_shelved.cs71.cs.swarthmore.edu.applicationprototype;
//
//
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import me.dm7.barcodescanner.zbar.Result;
//import me.dm7.barcodescanner.zbar.ZBarScannerView;
//
//
public class Scanner extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };
//
//    public class SimpleScannerActivity extends Activity implements ZBarScannerView.ResultHandler {
//        private ZBarScannerView mScannerView;
//
//        @Override
//        public void onCreate(Bundle state) {
//            super.onCreate(state);
//            mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
//            setContentView(mScannerView);                // Set the scanner view as the content view
//        }
//
//        @Override
//        public void onResume() {
//            super.onResume();
//            mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
//            mScannerView.startCamera();          // Start camera on resume
//        }
//
//        @Override
//        public void onPause() {
//            super.onPause();
//            mScannerView.stopCamera();           // Stop camera on pause
//        }
//
//        @Override
//        public void handleResult(Result rawResult) {
//            // Do something with the result here
//            Log.v(this.getClass().getName(), rawResult.getContents()); // Prints scan results
//            Log.v(this.getClass().getName(), rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)
//
//            // If you would like to resume scanning, call this method below:
//            //mScannerView.resumeCameraPreview(this);
//        }
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scanner);
//
//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//        Button scanButton = (Button) findViewById(R.id.SCAN);
//        scanButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                SimpleScannerActivity scan = new SimpleScannerActivity();
//            }
//
//        });
//    }
//
}

//package edu.swarthmore.cs.cs71.shelved.login2shelved;













//        import android.os.Bundle;
//        import android.support.design.widget.FloatingActionButton;
//        import android.support.design.widget.Snackbar;
//        import android.support.v7.app.AppCompatActivity;
//        import android.support.v7.widget.Toolbar;
//        import android.view.View;
//        import android.view.Menu;
//        import android.view.MenuItem;
//
//public class LoginActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scanner);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_login, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}