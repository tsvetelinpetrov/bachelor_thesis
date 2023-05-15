package com.tuvarna.mytu.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tuvarna.mytu.R;
import com.tuvarna.mytu.adapters.ChatMessageAdapter;
import com.tuvarna.mytu.models.ChatMessage;
import com.tuvarna.mytu.util.Constants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {
    private ListView messagesListView;
    private ArrayList<ChatMessage> messages;
    private ChatMessageAdapter chatMessageAdapter;
    Button btnSend;
    EditText editTextMessage;




    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        btnSend = view.findViewById(R.id.buttonSend);
        editTextMessage = view.findViewById(R.id.editTextMessage);
        messagesListView = view.findViewById(R.id.listViewMessages);

        messages = new ArrayList<>();
        ChatMessage message = new ChatMessage();
        message.setText("Здравейте! Аз съм езиковият модел на ТУ - Варна. Трениран съм на" +
                "милиардивъпроси към служители на ТУ и техните отговори." +
                "Как мога да ви бъда полезен? ");
        message.setRecipient_id("user-19621795");
        message.setAvatar(Constants.TILE_SOURCE_URL + "images/bot_avatar.png");
        messages.add(message);


        chatMessageAdapter = new ChatMessageAdapter(getContext(), messages);
        messagesListView.setAdapter(chatMessageAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatMessage message = new ChatMessage();
                message.setText(editTextMessage.getText().toString());
                message.setSender("user-19621795");
                message.setAvatar(Constants.TILE_SOURCE_URL + "images/user_avatar.png");
                messages.add(message);
                chatMessageAdapter.notifyDataSetChanged();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}