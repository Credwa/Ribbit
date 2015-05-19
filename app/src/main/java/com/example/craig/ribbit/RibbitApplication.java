package com.example.craig.ribbit;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Craig on 5/18/2015.
 */
public class RibbitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "quau1IKp15zVIAocNisebeVsLQ7DleGukDUZmDB6", "uRIZZ5T719mShYgNAFKt6CiMAOmlA37WOmcuWK4U");

    }



}
