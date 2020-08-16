package com.nagraj.smsanalyser.ui.chart;

import com.nagraj.base.BasePresenter;
import com.nagraj.model.Model;

import javax.inject.Inject;

public class ChartPresenter extends BasePresenter<ChartView> {
    public final Model model;

    @Inject
    ChartPresenter(Model model) {
        this.model = model;
    }
}
