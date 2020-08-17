package com.nagraj.smsanalyser.di;

import android.content.Context;
import com.nagraj.local.MessageService;
import com.nagraj.model.Model;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    MessageService provideService() {
        return new MessageService() ;
    }

    @Provides
    @Singleton
    Model provideModel(MessageService messageService) {
        return new Model(messageService);
    }



}
