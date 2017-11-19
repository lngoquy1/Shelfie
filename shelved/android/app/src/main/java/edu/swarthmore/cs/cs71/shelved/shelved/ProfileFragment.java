package edu.swarthmore.cs.cs71.shelved.shelved;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {
    private ImageButton settingsButton;

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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        settingsButton = (ImageButton)view.findViewById(R.id.popup_settings);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        settingsButton.setOnClickListener(new View.OnClickListener() {
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