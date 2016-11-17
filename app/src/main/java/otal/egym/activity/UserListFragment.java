package otal.egym.activity;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import otal.egym.model.User;

public class UserListFragment extends ListFragment {

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        launchUserDetailActivity(position);
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
}
