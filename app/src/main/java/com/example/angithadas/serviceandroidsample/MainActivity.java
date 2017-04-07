package com.example.angithadas.serviceandroidsample;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ServiceConnection serviceConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "ON CREATE MAIN", Toast.LENGTH_SHORT).show();
    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unBindService(View view) {

    }


}
