package com.example.mohammad.assignmentthree;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    Connection_Detector connection_detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView batteryTxt;
        batteryTxt = (TextView) this.findViewById(R.id.batteryTxt);

        BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context ctxt, Intent intent) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                batteryTxt.setText(String.valueOf(level) + "%");

            }

        };
        this.registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        BroadcastReceiver br = new CheckConnectivity();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(br, filter);


        Button button = findViewById(R.id.button);
        connection_detector = new Connection_Detector(this);
        //  connection_detector.isconnected();
        //  runInBackground();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connection_detector.isconnected();
                Boolean f = connection_detector.isconnected();

            }
        });




    }

}
