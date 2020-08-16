package com.nagraj.smsanalyser.di;

import com.nagraj.smsanalyser.ui.HomeActivity;
import com.nagraj.smsanalyser.ui.chart.ChartFragment;
import com.nagraj.smsanalyser.ui.list.ListFragment;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class) public interface ApplicationComponent {

    void inject(HomeActivity homeActivity);

    void inject(ChartFragment chartFragment);

    void inject(ListFragment listFragment);

}

