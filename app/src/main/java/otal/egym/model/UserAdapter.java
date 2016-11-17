package otal.egym.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import otal.egym.R;

public class UserAdapter extends ArrayAdapter<User> {

    public static class ViewHolder {
        TextView username;
        TextView phone;
        ImageView thumbnail;
    }

    public UserAdapter(Context context, List<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);

        ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate a new view from custom row layout
        // Create viewHolder for the first time
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_list_row, parent, false);

            // Grab references of views so we can populate them with specific user row data
            viewHolder.username = (TextView) convertView.findViewById(R.id.listItemUsername);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.listItemPhone);
            viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.listItemImg);


            convertView.setTag(viewHolder);
        } else {
            // Grab the widgets from it as we already have ViewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Fill each new referenced view with data associated with user it's referencing
        viewHolder.username.setText(user.getUsername());
        viewHolder.phone.setText(user.getPhone());
        Picasso.with(getContext()).load(user.getThumbnail()).into(viewHolder.thumbnail);

        return convertView;
    }
}
