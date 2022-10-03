package com.grapetechnologies.videochat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText joinCode;
     Button joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        joinBtn = findViewById(R.id.btnJoin);
        joinCode = findViewById(R.id.edtxtCode);

        try {
            URL serverURL = new URL("https:meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(joinCode.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Null Room", Toast.LENGTH_SHORT).show();
                }
                else{
                JitsiMeetConferenceOptions roomOptions = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(joinCode.getText().toString())
                        .setAudioMuted(false)
                        .setVideoMuted(false)
                        .build();
                JitsiMeetActivity.launch(MainActivity.this, roomOptions);
            }}
        });


    }
}