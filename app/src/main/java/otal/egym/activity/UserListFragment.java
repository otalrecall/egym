package otal.egym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
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
     *
     * @param position
     */
    private void launchUserDetailActivity(int position) {
        User user = (User) getListAdapter().getItem(position);

        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra(MainActivity.USERNAME_EXTRA, user.getUsername());
        intent.putExtra(MainActivity.PHONE_EXTRA, user.getPhone());
        intent.putExtra(MainActivity.IMG_EXTRA, user.getThumbnail());

        startActivity(intent);
    }
}
