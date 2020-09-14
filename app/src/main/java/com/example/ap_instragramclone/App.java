package com.example.ap_instragramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

         Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("dhjkCC7Yz2DUNBdxF9KOJSVmYk18oVupKUdJUplz")
                // if defined
                .clientKey("UBhLMrcKoMMamecjYz98Q9J87s60VQCfoBAlys0o")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
