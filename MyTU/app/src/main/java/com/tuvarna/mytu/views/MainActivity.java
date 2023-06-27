package com.tuvarna.mytu.views;

import static androidx.navigation.ui.BottomNavigationViewKt.setupWithNavController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tuvarna.mytu.R;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavHostFragment navHostFragment;
    NavController navController;
    PendingIntent pendingIntent;
    IntentFilter[] readFilters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try  {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        } catch (NullPointerException ignored){}

        navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_fragment_layout);
        navController = navHostFragment.getNavController();
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        setupWithNavController(bottomNavigationView, navController);

        try {
            Intent intent = new Intent(this, getClass());
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            //pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                pendingIntent = PendingIntent.getActivity
                        (this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
            }
            else
            {
                pendingIntent = PendingIntent.getActivity
                        (this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
            }

            IntentFilter javadudeFilter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
            javadudeFilter.addDataScheme("http");
            javadudeFilter.addDataAuthority("tsvetelinpetrov.com", null);
            IntentFilter textFilter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED, "text/plain");

            readFilters = new IntentFilter[]{javadudeFilter, textFilter};

        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException(e);
        }

        processNFC(getIntent());
    }

    private void enableRead() {
        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        nfc.enableForegroundDispatch(this, pendingIntent, readFilters, null);
    }

    private void disableRead() {
        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        nfc.disableForegroundDispatch(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        enableRead();
    }

    @Override
    protected void onPause() {
        super.onPause();
        disableRead();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processNFC(intent);
    }

    private void processNFC(Intent intent) {
        Parcelable[] messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if (messages != null) {
            for (Parcelable message : messages) {
                NdefMessage ndefMessage = (NdefMessage) message;
                for (NdefRecord record : ndefMessage.getRecords()) {
                    switch(record.getTnf()){
                        case NdefRecord.TNF_WELL_KNOWN:
                            if(Arrays.equals(record.getType(), NdefRecord.RTD_TEXT)) {
                                // log and toast the record payload
                                String payload = new String(record.getPayload());
                                Toast.makeText(this, "NFC таг прочетен", Toast.LENGTH_SHORT).show();
                                Log.d("NFC", "Plain/Text Payload: " + payload);
                            } else if (Arrays.equals(record.getType(), NdefRecord.RTD_URI)) {
                                // log the record payload
                                String payload = new String(record.getPayload());
                                Toast.makeText(this, "NFC таг прочетен", Toast.LENGTH_SHORT).show();
                                Log.d("NFC", "Uri Payload: " + payload);
                            }
                            break;
                    }
                }
            }
        }
    }
}