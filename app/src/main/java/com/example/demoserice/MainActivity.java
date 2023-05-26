package com.example.demoserice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.demoserice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Intent serviceIntent;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        serviceIntent = new Intent(getApplicationContext(),MyService.class);

        Log.i("Service Demo","Service intialized:"+Thread.currentThread().getName());
        Toast.makeText(this, "Service Intialized", Toast.LENGTH_SHORT).show();

        binding.startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(serviceIntent);
            }
        });

        binding.stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
            }
        });


    }
}