package otal.egym.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import otal.egym.R;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        createAndAddFragment();
    }

    /**
     * Instantes the fragment UserVireFragment and it's added to the activity
     */
    private void createAndAddFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        UserViewFragment userViewFragment = new UserViewFragment();
        fragmentTransaction.add(R.id.user_container, userViewFragment, "USER_VIEW_FRAGMENT");

        fragmentTransaction.commit();
    }

}
