package com.nagraj.smsanalyser;

import android.app.Application;

import com.nagraj.smsanalyser.di.ApplicationComponent;
import com.nagraj.smsanalyser.di.ApplicationModule;
import com.nagraj.smsanalyser.di.DaggerApplicationComponent;

public class App extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }
    public static ApplicationComponent getComponent() {
        return applicationComponent;
    }
}