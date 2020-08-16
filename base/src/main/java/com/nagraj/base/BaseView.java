package com.nagraj.base;

public interface BaseView {

    void showToast(String message);

    void showError(String errorMessage);

    void showProgress();

    void showContent();
}
