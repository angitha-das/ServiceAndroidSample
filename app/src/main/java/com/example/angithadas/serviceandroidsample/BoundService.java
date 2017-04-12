package com.example.angithadas.serviceandroidsample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by angitha.das on 10-04-2017.
 */

public class BoundService extends Service {
    /**
     * interface for clients that bind
     */
    private final IBinder mBinder = new MyBinder();

    /**
     * indicates whether onRebind should be used
     */
    boolean mAllowRebind;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "ON BIND", Toast.LENGTH_SHORT).show();
        return mBinder;
    }

    /**
     * Called when all clients have unbound with unbindService()
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "ON UN-BIND", Toast.LENGTH_SHORT).show();
        return mAllowRebind;
    }

    /**
     * Called when a client is binding to the service with bindService()
     */
    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(this, "ON RE-BIND", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    public int findFactorial(int num) {
        int fact = 1;
        for (int i = 1; i <= num; i++) {
            fact = fact * i;
        }
        return fact;
    }

    class MyBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
}
