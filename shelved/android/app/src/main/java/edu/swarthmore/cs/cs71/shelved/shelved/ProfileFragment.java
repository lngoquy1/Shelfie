package edu.swarthmore.cs.cs71.shelved.shelved;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.*;
import android.widget.*;
import butterknife.ButterKnife;

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
        ButterKnife.bind(this, rootView);

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
                fragment = new ShelfFragment();
                replaceFragment(fragment);
                break;
            case R.id.ListsButton:
                fragment = new ShelfFragment();
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