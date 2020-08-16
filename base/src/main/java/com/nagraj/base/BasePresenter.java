package com.nagraj.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BasePresenter<V extends BaseView> {
    protected V view;
    private CompositeDisposable disposable;
    protected Consumer<Throwable> defaultErrorAction;
    public void attachView(V view){
        this.view=view;
        defaultErrorAction = throwable -> {
            if (isViewAttached()) {
                view.showContent();
                view.showError("There is some error in internet,Please Try again");
            }
        };
    }

    public void detachView() {
        this.view = null;
        if (disposable != null && disposable.isDisposed()) {
            disposable.clear();
        }
    }


    protected boolean isViewAttached() {
        return this.view != null;
    }

    protected final void addToSubscription(Disposable disposable1) {
        if (disposable==null) {
            disposable = new CompositeDisposable();
        }
        disposable.add(disposable1);
    }
}
