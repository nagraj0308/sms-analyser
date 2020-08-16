package com.nagraj.smsanalyser.ui;

import com.nagraj.base.BasePresenter;
import com.nagraj.model.Model;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter<HomeView> {

    public final Model model;

    @Inject
    HomePresenter(Model model) {
        this.model = model;
    }

//    public void loadFeeds(int appId) {
//        view.showProgress();
//        Disposable disposable = model.loadFeeds(appId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result -> {
//                    if (isViewAttached()) {
//                        view.showContent();
//                        if (result != null) {
//                            if (result.getIsTrue()) {
//                                view.setUserFeedsResult(result.getLoadFeedsResult());
//                            } else {
//                                view.showError(result.getError());
//                            }
//                        } else {
//                            view.showError("There is some error in server,Please Try again");
//                        }
//                    }
//                }, defaultErrorAction);
//        addToSubscription(disposable);
    // }

}
