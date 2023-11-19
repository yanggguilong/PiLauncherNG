package com.veticia.piLauncherNext;

import static com.veticia.piLauncherNext.MainActivity.sharedPreferences;
import static com.veticia.piLauncherNext.SettingsProvider.KEY_AUTORUN;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            if(sharedPreferences==null)
                sharedPreferences = context.getSharedPreferences(context.getPackageName() + "_preferences", Context.MODE_PRIVATE);
            boolean autorunEnabled = sharedPreferences.getBoolean(KEY_AUTORUN, true);
            if (autorunEnabled) {
                /*
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

                 */
               //Intent finishIntent = new Intent(MainActivity.FINISH_ACTION);
                //context.sendBroadcast(finishIntent);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent launchIntent = new Intent(context, AccessibilityLauncherProxy.class);
                        // launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

                        Log.i("PiLauncherService", "Opening launcher activity from startup event");
                        context.startActivity(launchIntent);
                    }
                }, 4000);

            }
        }
    }
}