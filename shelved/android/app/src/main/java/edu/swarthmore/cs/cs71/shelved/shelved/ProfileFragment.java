package edu.swarthmore.cs.cs71.shelved.shelved;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.facebook.login.Login;

public class ProfileFragment extends Fragment implements View.OnClickListener{
    private ImageButton settingsBtn;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        settingsBtn = (ImageButton)rootView.findViewById(R.id.popup_settings);
        Button shelvesBtn = (Button) rootView.findViewById(R.id.ShelvesButton);
        Button listsBtn = (Button) rootView.findViewById(R.id.ListsButton);

        shelvesBtn.setOnClickListener(this);
        listsBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.ShelvesButton:
                fragment = ShelfFragment.newInstance(null);
                replaceFragment(fragment);
                break;
            case R.id.ListsButton:
                fragment = new BookListFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.shelf_list_frame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getContext(), view);
                popup.getMenuInflater().inflate(R.menu.profile_popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle() == "Edit Profile") {
                            Intent i = new Intent(getContext(), LoginActivity.class);
                            startActivity(i);
                        }
                        Toast.makeText(getContext()
                                ,
                                "You Clicked : " + menuItem.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });
                popup.show();
            }
        });
    }
}