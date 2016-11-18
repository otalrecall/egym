package otal.egym.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import otal.egym.model.JSONParser;
import otal.egym.model.User;
import otal.egym.model.UserAdapter;

public class UserListFragment extends ListFragment  {
    private String apiURL;
    private int page;
    private int results;
    private List<User> currentUsers;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initVar();
        new ParseJSON().execute(apiURL);
        setListeners();
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Start an alpha animation for clicked item
        Animation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(4000);
        view.startAnimation(animation);
        
        launchUserDetailActivity(position);
    }

    /**
     * Initializes the variables
     */
    private void initVar() {
        currentUsers = new ArrayList<>();
        page = 1;
        results = 10;
        apiURL = String.format("https://api.randomuser.me/?seed=egym&page=%d&results=%d",
                page, results);
    }

    /**
     * Sets the listener to check when the scroll view hits the bottom of the screen to implement
     * pagination.
     */
    private void setListeners() {
        final ListView listView = getListView();
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && (listView.getLastVisiblePosition() - listView.getHeaderViewsCount() -
                        listView.getFooterViewsCount()) >= (listView.getAdapter().getCount() - 1)) {

                    // ListView has hit the bottom
                    ++page;
                    apiURL = String.format("https://api.randomuser.me/?seed=egym&page=%d&results=%d", page, 10);
                    new ParseJSON().execute(apiURL);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    /**
     * Once a user is selected in the list, creates an intent and adds every piece of information
     * needed for UserDetailActivity
     *
     * @param position
     */
    private void launchUserDetailActivity(int position) {
        User user = (User) getListAdapter().getItem(position);

        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra(MainActivity.USERNAME_EXTRA, user.getUsername());
        intent.putExtra(MainActivity.PHONE_EXTRA, user.getPhone());
        intent.putExtra(MainActivity.GENDER_EXTRA, user.getGender());
        intent.putExtra(MainActivity.FULLNAME_EXTRA, user.getTitle() + " " + user.getFirstName() +
                " " + user.getLastName());
        intent.putExtra(MainActivity.STREET_EXTRA, user.getStreet());
        intent.putExtra(MainActivity.LOCATION_EXTRA, user.getPostcode() + " " + user.getCity() +
                ", " + user.getState());
        intent.putExtra(MainActivity.LARGE_PIC_EXTRA, user.getLargePicture());
        intent.putExtra(MainActivity.USER_ID_EXTRA, user.getId());

        startActivity(intent);
    }

    private class ParseJSON extends AsyncTask< String, Void, List<User> > {

        @Override
        protected List<User> doInBackground(String... strings) {
            return JSONParser.parseJSON(strings[0]);
        }

        @Override
        protected void onPostExecute(List<User> users) {
            UserAdapter userAdapter;

            // If it's the first time we fill the list, creates adapter. If not, updates it.
            if (currentUsers.isEmpty()) {
                currentUsers = users;
                userAdapter = new UserAdapter(getContext(), currentUsers);
                setListAdapter(userAdapter);
            }
            else {
                currentUsers.addAll(users);
                userAdapter = (UserAdapter) getListAdapter();
                userAdapter.notifyDataSetChanged();
            }

        }
    }
}
