package com.nagraj.smsanalyser.ui.list;

import com.nagraj.base.BasePresenter;
import com.nagraj.model.Model;
import javax.inject.Inject;

public class ListPresenter extends BasePresenter<ListView> {
    public final Model model;

    @Inject
    ListPresenter(Model model) {
        this.model = model;
    }
}
