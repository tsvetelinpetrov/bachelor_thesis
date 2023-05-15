package com.tuvarna.mytu.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tuvarna.mytu.R;
import com.tuvarna.mytu.adapters.ChatMessageAdapter;
import com.tuvarna.mytu.models.ChatMessage;
import com.tuvarna.mytu.models.ChatRequestMessage;
import com.tuvarna.mytu.repositories.ChatBotRepository;
import com.tuvarna.mytu.repositories.IChatBotCallback;
import com.tuvarna.mytu.util.Constants;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment implements IChatBotCallback {
    ChatBotRepository chatBotRepository;
    private ListView messagesListView;
    private ArrayList<ChatMessage> messages;
    private ChatMessageAdapter chatMessageAdapter;
    Button btnSend;
    EditText editTextMessage;

    Integer funnyCounter = 0;

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

        chatBotRepository = new ChatBotRepository();

        messages = new ArrayList<>();
        ChatMessage message = new ChatMessage();
        message.setText(getString(R.string.chart_first_response));
        message.setRecipient_id("user-19621795");
        message.setAvatar(Constants.TILE_SOURCE_URL + "images/bot_avatar.png");
        messages.add(message);

        chatMessageAdapter = new ChatMessageAdapter(getContext(), messages);
        messagesListView.setAdapter(chatMessageAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSend.setEnabled(false);
                ChatMessage message = new ChatMessage();
                if(editTextMessage.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), getString(R.string.chat_no_message), Toast.LENGTH_SHORT).show();
                    btnSend.setEnabled(true);
                    return;
                }
                message.setText(editTextMessage.getText().toString().trim());
                message.setSender("user-19621795");
                message.setAvatar(Constants.TILE_SOURCE_URL + "images/user_avatar.png");
                messages.add(message);
                chatMessageAdapter.notifyDataSetChanged();
                editTextMessage.setText("");

                if(funnyCounter == 0) {
                    ChatMessage messageEla = new ChatMessage();
                    messageEla.setAvatar(Constants.TILE_SOURCE_URL + "images/bot_avatar.png");
                    Random random = new Random();
                    int randomIndex = random.nextInt(2);
                    String messageString = (randomIndex == 0) ? getString(R.string.chart_second_response) : getString(R.string.chart_second_2_response);
                    messageEla.setText(messageString);
                    messages.add(messageEla);
                    chatMessageAdapter.notifyDataSetChanged();

                    new Thread(() -> {
                        ChatMessage message1 = new ChatMessage();
                        message1.setAvatar(Constants.TILE_SOURCE_URL + "images/bot_avatar.png");
                        message1.setText(getString(R.string.chart_third_response));

                        // do background stuff here
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        messages.add(message1);

                        getActivity().runOnUiThread(() -> {
                            chatMessageAdapter.notifyDataSetChanged();
                        });

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        chatBotRepository.getChatBotResponse(new ChatRequestMessage(message.getSender(), message.getText()), ChatFragment.this);

                    }).start();
                    funnyCounter++;

                }else{
                    chatBotRepository.getChatBotResponse(new ChatRequestMessage(message.getSender(), message.getText()), ChatFragment.this);
                }
            }
        });
        return view;
    }

    @Override
    public void onChatBotResponseReceived(ArrayList <ChatMessage>  chatMessageResponse) {
        chatMessageResponse.get(0).setAvatar(Constants.TILE_SOURCE_URL + "images/bot_avatar.png");
        messages.add(chatMessageResponse.get(0));
        chatMessageAdapter.notifyDataSetChanged();
        btnSend.setEnabled(true);
    }

    @Override
    public void onChatBotResponseFailure(Throwable t) {
        Log.e("ChatFragment", "Error: " + t.getMessage());
        Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
        btnSend.setEnabled(true);
    }
}