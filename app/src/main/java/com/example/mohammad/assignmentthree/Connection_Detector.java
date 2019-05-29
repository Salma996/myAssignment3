package com.example.mohammad.assignmentthree;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * Created by Mohammad on 12/15/2018.
 */

public class Connection_Detector extends AsyncTask<String,Void,Integer> {

    Context context;
    @Override
    protected Integer doInBackground(String... strings) {
        isconnected();
        return null;
    }

    public Connection_Detector() {
    }

    public Connection_Detector(Context context) {
        this.context = context;
    }


    public boolean isconnected()
    {
        ConnectivityManager connectivityManager =(ConnectivityManager)context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null)
            {
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    Toast toast = Toast.makeText(context, " yes ! connected", Toast.LENGTH_SHORT);
                    toast.show();
                    Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    return  true;
                }

                }
                else if (info.getState() == NetworkInfo.State.DISCONNECTED)
                    {
                        Toast toast = Toast.makeText(context, " nooo ! its not  connected", Toast.LENGTH_SHORT);
                        toast.show();
                        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                            return  true;
                        }
                        return  false;
                    }
                else if (info.getState() == NetworkInfo.State.CONNECTING)
                {
                    Toast toast = Toast.makeText(context, "  its  connecting !! ", Toast.LENGTH_SHORT);
                    toast.show();
                    Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));


                    }
                    return  false;
                }
                else {return  false;}

            }
            return false;
        }

        return false;
    }

    @Override
    protected void onProgressUpdate(Void... values) {

        if (isconnected())
        {
            Toast toast = Toast.makeText(context, "baby shark ! dododod connected", Toast.LENGTH_SHORT);
            toast.show();
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));


            }
        }
        else {
            Toast toast = Toast.makeText(context, "dis connected", Toast.LENGTH_SHORT);
            toast.show();
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));


            }
        }


        super.onProgressUpdate(values);
    }

    @Override
    public void onPreExecute() {
        if (isconnected())
        {
                Toast toast = Toast.makeText(context, "baby shark ! dododod connected", Toast.LENGTH_SHORT);
        toast.show();
        }
        else {
            Toast toast = Toast.makeText(context, "dis connected", Toast.LENGTH_SHORT);
            toast.show();
        }
        super.onPreExecute();
    }
}
