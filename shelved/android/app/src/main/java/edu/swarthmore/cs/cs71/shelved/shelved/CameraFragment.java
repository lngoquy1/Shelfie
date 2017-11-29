package edu.swarthmore.cs.cs71.shelved.shelved;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CameraFragment extends Fragment {
    private static final String TAG = "CameraFragment";

    public static CameraFragment newInstance() {
        CameraFragment fragment = new CameraFragment();
        Context context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context;
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);
        context = rootView.getContext();
        Intent intent = new Intent(context, ScannerActivity.class);
        Log.d(TAG, "about to call activity start");
        startActivity(intent);
        Log.d(TAG, "past activity start");
        return rootView;
        //return inflater.inflate(R.layout.fragment_camera, container, false);
    }
}