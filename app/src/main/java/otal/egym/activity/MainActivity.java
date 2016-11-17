package otal.egym.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import otal.egym.R;
import otal.egym.model.JSONParser;
import otal.egym.model.User;
import otal.egym.model.UserAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String API_URL = "https://api.randomuser.me/?seed=egym&page=1&results=50";
    public static final String USERNAME_EXTRA = "otal.egym.username";
    public static final String PHONE_EXTRA = "otal.egym.phone";
    public static final String IMG_EXTRA = "otal.egym.img";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListFragment userListFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.userListFragment);

        new ParseJSON(userListFragment).execute(API_URL);
    }

    private class ParseJSON extends AsyncTask< String, Void, List<User> > {

        ListFragment userListFragment;

        ParseJSON(ListFragment userListFragment) {
            this.userListFragment = userListFragment;
        }

        @Override
        protected List<User> doInBackground(String... strings) {
            return JSONParser.parseJSON(strings[0]);
        }

        @Override
        protected void onPostExecute(List<User> users) {
            UserAdapter userAdapter = new UserAdapter(MainActivity.this, users);
            userListFragment.setListAdapter(userAdapter);
        }
    }
}