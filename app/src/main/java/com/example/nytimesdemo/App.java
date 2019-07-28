package com.example.nytimesdemo;

import android.app.Application;
import android.content.Context;

import com.example.nytimesdemo.data.remote.NetworkService;
import com.example.nytimesdemo.data.remote.Networking;

/**
 * TODO: Add a class header comment!
 */
public class App extends Application {

    private  Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext=   getApplicationContext();
    }
}
