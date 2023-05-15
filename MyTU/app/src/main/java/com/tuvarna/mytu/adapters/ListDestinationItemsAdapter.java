package com.tuvarna.mytu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuvarna.mytu.R;
import com.tuvarna.mytu.models.Room;
import com.tuvarna.mytu.util.IconSelector;

import java.util.ArrayList;

public class ListDestinationItemsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Room> rooms;

    public ListDestinationItemsAdapter(Context context, ArrayList<Room> rooms) {
        this.context = context;
        this.rooms = rooms;
    }

    @Override
    public int getCount() {
        return rooms.size();
    }

    @Override
    public Object getItem(int position) {
        return rooms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_destination, parent, false);
        }

        Room room = (Room) getItem(position);

        LinearLayout listDestinationItemHolder = convertView.findViewById(R.id.listDestinationItemHolder);
        ImageView destinationIconImageView = convertView.findViewById(R.id.destinationIconImageView);
        TextView destinationTextView = convertView.findViewById(R.id.destinationTextView);
        TextView destinationDescriptionTextView = convertView.findViewById(R.id.destinationDescriptionTextView);

        if(room.getLabel().getIcon() == 0) {
            destinationIconImageView.setImageResource(R.drawable.ic_outline_location_on);
        } else {
            destinationIconImageView.setImageResource(IconSelector.getIconId(room.getLabel().getIcon()));
        }
        destinationTextView.setText(room.getLabel().getText());
        destinationDescriptionTextView.setText(room.getLabel().getSubText());

        return convertView;
    }
}
