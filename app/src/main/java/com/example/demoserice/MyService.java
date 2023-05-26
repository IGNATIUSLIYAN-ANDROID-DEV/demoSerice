package com.example.demoserice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyService extends Service {

    private int  randomNumber;

    private boolean IsRamdomNumberOn=false;

    class MyServiceBinder extends Binder{
        public MyService getBinder(){
            return MyService.this;
        }
    }
    IBinder iBinder = new MyServiceBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Service Demo","Service Started");
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        IsRamdomNumberOn=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                showRandomNumber();
            }
        }).start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopRandomNumber();
        Log.i("Service Demo","Service Stoped");
        Toast.makeText(this,"Service Stoped",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private void showRandomNumber(){

        while(IsRamdomNumberOn)
        {
                try {
                    Thread.sleep(1000);
                    if(IsRamdomNumberOn==true) {
                        randomNumber = new Random().nextInt(100);
                        Log.i("Service Demo", "Running Thread is " + Thread.currentThread().getId() + " Random Number is " + randomNumber);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

    private void stopRandomNumber()
    {
        IsRamdomNumberOn=false;
    }
}
