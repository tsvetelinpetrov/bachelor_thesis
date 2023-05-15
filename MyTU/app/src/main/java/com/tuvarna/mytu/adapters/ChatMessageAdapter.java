package com.tuvarna.mytu.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tuvarna.mytu.R;
import com.tuvarna.mytu.models.ChatMessage;

import java.util.ArrayList;
import java.util.Objects;

public class ChatMessageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ChatMessage> mMessages;

    public ChatMessageAdapter(Context context, ArrayList<ChatMessage> messages) {
        mContext = context;
        mMessages = messages;
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return mMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_message, parent, false);
        }

        ChatMessage message = (ChatMessage) getItem(position);

        TextView messageTextView = convertView.findViewById(R.id.messageTextView);
        ImageView messageImageView = convertView.findViewById(R.id.messageImageView);
        LinearLayout messageHolder = convertView.findViewById(R.id.messageHolder);

        Picasso.get().load(message.getAvatar()).into(messageImageView);
        messageTextView.setText(Html.fromHtml(message.getText()));
        if(message.getSender() != null && !Objects.equals(message.getSender(), "")) {
            messageHolder.setBackgroundColor(mContext.getResources().getColor(R.color.secondaryBackground));
        } else {
            messageHolder.setBackgroundColor(mContext.getResources().getColor(R.color.mainBackground));
        }

        return convertView;
    }
}