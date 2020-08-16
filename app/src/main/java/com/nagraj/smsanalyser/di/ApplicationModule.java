package com.nagraj.smsanalyser.di;

import android.content.Context;
import dagger.Module;


@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

//    @Provides
//    @Singleton
//    AndroidServer0308Service provideService() {
//        return RetrofitClass.getClient().create(AndroidServer0308Service.class);
//    }

//    @Provides
//    @Singleton
//    Model provideModel(AndroidServer0308Service androidServer0308Service,PreferenceData preferenceData) {
//        return new Model(androidServer0308Service,preferenceData);
//    }



}
