package com.veticia.piLauncherNext;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;


public class AccessibilityLauncherProxy extends Activity {
      private boolean launch() {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage( getPackageName());
        if (launchIntent != null) return launchInt(launchIntent);
        return false;
    }
    private boolean launchInt(Intent launchIntent) {
        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        finish();
        startActivity(launchIntent);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!launch()) {
            Log.i("AccessibilityLauncherProxy", "Opening launcher activity from accessibility event failed");
        }
    }
}