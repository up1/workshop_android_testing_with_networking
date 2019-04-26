package com.example.networkingtesting;

import android.app.Application;

public class DemoApplication extends Application {

    public String getBaseUrl() {
        return "https://api.github.com";
    }

}
