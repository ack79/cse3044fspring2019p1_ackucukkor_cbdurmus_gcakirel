package com.canberkdurmus.libra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.widget.Toast;

public class ActivitySplash extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 150;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(() -> {
            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            String username = sharedPref.getString("username", null);
            String password = sharedPref.getString("password", null);

            if (password == null || username == null)
                redirectLogin();

            if (login()) {
                redirectTimeline();
            } else {
                redirectLogin();
            }

        }, SPLASH_DISPLAY_LENGTH);
    }

    public void redirectLogin() {
        /* Create an Intent that will start the Menu-Activity. */
        Intent timelineIntent = new Intent(ActivitySplash.this, ActivityLogin.class);
        ActivitySplash.this.startActivity(timelineIntent);
        ActivitySplash.this.finish();
    }

    public void redirectTimeline() {
        /* Create an Intent that will start the Menu-Activity. */
        Intent mainIntent = new Intent(ActivitySplash.this, MainActivity.class);
        ActivitySplash.this.startActivity(mainIntent);
        ActivitySplash.this.finish();
    }

    public Boolean login() {
        return false;
    }
}
