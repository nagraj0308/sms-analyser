package com.nagraj.smsanalyser.ui;

import android.content.Context;

import com.nagraj.base.BasePresenter;
import com.nagraj.model.Model;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeView> {

    public final Model model;

    @Inject
    HomePresenter(Model model) {
        this.model = model;
    }

    public void getMessages(Context context) {
        view.showProgress();
        Disposable disposable = model.getMessages(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (isViewAttached()) {
                        view.showContent();
                        if (result.size()==0 ) {
                            view.showError("Your inbox is empty!!");
                        } else {
                            view.setMessages(result);
                        }
                    }
                }, defaultErrorAction);
        addToSubscription(disposable);
     }

}
