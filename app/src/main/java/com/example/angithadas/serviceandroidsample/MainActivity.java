package com.example.angithadas.serviceandroidsample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BoundService mBoundService;
    boolean mServiceBound = false;
    EditText text;
    TextView resultFactoral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "ON CREATE MAIN", Toast.LENGTH_SHORT).show();

        text = (EditText) findViewById(R.id.number);
        resultFactoral = (TextView) findViewById(R.id.result);

        //Unbounded Service life cycle: onCreate()-onStartCommand()- onDestroy()
        //Bounded Service life cycle: onCreate()-onBind()-onUnbind()-onDestroy()
    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unBindService(View view) {
        if (mServiceBound) {
            unbindService(mServiceConnection);
            mServiceBound = false;
        }
    }

    public void factorial(View view){
        if (mServiceBound) {
            int num = Integer.parseInt(text.getText().toString());
            int result = mBoundService.findFactorial(num);
            resultFactoral.setText("Factorial = "+ result);
        }
        else  {
            Toast.makeText(mBoundService, "Please Bound Service first", Toast.LENGTH_SHORT).show();
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) iBinder;
            mBoundService = myBinder.getService();
            mServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mServiceBound = false;
        }
    };

}
