package com.nagraj.smsanalyser.ui.chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nagraj.smsanalyser.App;
import com.nagraj.smsanalyser.R;
import com.nagraj.smsanalyser.databinding.FragmentChartBinding;
import com.nagraj.smsanalyser.ui.BaseHomeFragment;

import javax.inject.Inject;

public class ChartFragment extends BaseHomeFragment implements ChartView {
    @Inject
    ChartPresenter presenter;

    private FragmentChartBinding binding;


    public ChartFragment() {
    }

    public static ChartFragment newInstance() {
        return new ChartFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChartBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initArguments(Bundle extras) {

    }

    @Override
    protected void initDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    protected void initViews() {
        activityCallback.setTitle(getString(R.string.report_in_pie_chart));
    }

    @Override
    protected void onReady() {
        presenter.attachView(this);
    }

    @Override
    protected void destroyPresenter() {
        binding = null;
        presenter.detachView();
    }
}
