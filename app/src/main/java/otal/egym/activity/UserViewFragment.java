package otal.egym.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import otal.egym.R;
import otal.egym.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserViewFragment extends Fragment {


    public UserViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_user_view, container, false);

        ImageView largeImage = (ImageView) fragmentLayout.findViewById(R.id.userViewImg);
        TextView username = (TextView) fragmentLayout.findViewById(R.id.userViewUsername);
        TextView phone = (TextView) fragmentLayout.findViewById(R.id.userViewPhone);
        TextView gender = (TextView) fragmentLayout.findViewById(R.id.userViewGender);
        TextView fullname = (TextView) fragmentLayout.findViewById(R.id.userViewFullname);
        TextView street = (TextView) fragmentLayout.findViewById(R.id.userViewStreet);
        TextView location = (TextView) fragmentLayout.findViewById(R.id.userViewLocation);

        Intent intent = getActivity().getIntent();
        username.setText(intent.getExtras().getString(MainActivity.USERNAME_EXTRA));
        fullname.setText(intent.getExtras().getString(MainActivity.FULLNAME_EXTRA));
        street.setText(intent.getExtras().getString(MainActivity.STREET_EXTRA));
        location.setText(intent.getExtras().getString(MainActivity.LOCATION_EXTRA));
        phone.setText(intent.getExtras().getString(MainActivity.PHONE_EXTRA));

        User.Gender genderEnum = (User.Gender) intent.getSerializableExtra(MainActivity.GENDER_EXTRA);
        gender.setText( User.genderToString(genderEnum) );

        Picasso.with(getContext()).load(intent.getExtras().getString(MainActivity.LARGE_PIC_EXTRA))
                .into(largeImage);

        // Inflate the layout for this fragment
        return fragmentLayout;
    }

}
