package com.example.mohammad.assignmentthree;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Mohammad on 4/12/2019.
 */

public class CheckConnectivity extends BroadcastReceiver {	private static final String LOG_TAG = "NetworkChangeReceiver";
        private boolean isConnected = false;

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v(LOG_TAG, "Receieved notification about network status");
            isNetworkAvailable(context);

            final PendingResult pendingResult = goAsync();
            Task asyncTask = new Task(pendingResult, intent);
            asyncTask.execute();
        }

        private boolean isNetworkAvailable(Context context) {
            ConnectivityManager connectivityManager =(ConnectivityManager)context.getSystemService(Service.CONNECTIVITY_SERVICE);
            if (connectivityManager != null)
            {
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if (info != null)
                {
                    if (info.getState() == NetworkInfo.State.CONNECTED)
                    {
                        Toast toast = Toast.makeText(context, " yes ! r u sure ?  connected", Toast.LENGTH_SHORT);
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
                    else if (info.getState() == NetworkInfo.State.DISCONNECTING)
                    {
                        Toast toast = Toast.makeText(context, "  its  connecting !! ", Toast.LENGTH_SHORT);
                        toast.show();
                        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                            return  true;
                        }
                        return  false;
                    }
                    else {return  false;}

                }

            }

          ///////////////////////////////////
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            if (!isConnected) {
                                Log.v(LOG_TAG, "Now you are connected to Internet!");
                                Toast.makeText(context, "Internet availablle via Broadcast receiver", Toast.LENGTH_SHORT).show();
                                isConnected = true;
                                Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                                    return  true;
                                }
                                // do your processing here ---
                                // if you need to post any data to the server or get
                                // status
                                // update from the server
                            }
                            return true;
                        }
                    }
                }
            }
            Log.v(LOG_TAG, "You are not connected to Internet!");
            Toast.makeText(context, "Internet NOT availablle via Broadcast receiver", Toast.LENGTH_SHORT).show();
            isConnected = false;
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                return  true;
            }
            return false;
        }

    private static class Task extends AsyncTask<String, Integer, String> {

        private final PendingResult pendingResult;
        private final Intent intent;

        private Task(PendingResult pendingResult, Intent intent) {
            this.pendingResult = pendingResult;
            this.intent = intent;
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder sb = new StringBuilder();
            sb.append("Action: " + intent.getAction() + "\n");
            sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
            String log = sb.toString();
            Log.d(TAG, log);
            return log;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // Must call finish() so the BroadcastReceiver can be recycled.
            pendingResult.finish();
        }
    }


}
