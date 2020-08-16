package com.nagraj.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.nagraj.base.databinding.LayoutProgressBinding;

import java.util.Objects;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    MaterialAlertDialogBuilder materialAlertDialogBuilder;
    LayoutProgressBinding progressBinding;
    AlertDialog progressDialog;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null) {
            initIntentExtras(getIntent().getExtras());
        }
        initDependencies();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        progressBinding = LayoutProgressBinding.inflate(getLayoutInflater());
        materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder
                .setCancelable(false)
                .setView(progressBinding.getRoot());

        progressDialog = materialAlertDialogBuilder.create();
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(R.color.color_transparent);
        initViews();
        onReady();
    }

    @Override
    public void onDestroy() {
        destroyPresenter();
        super.onDestroy();
    }

    @Override
    public void showContent() {
        progress--;
        if (progress <= 0) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public void showProgress() {
        progress++;
        if (progress <= 1) {
            progressDialog.show();
        }

    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    protected abstract void initIntentExtras(Bundle extras);

    protected abstract void initDependencies();

    protected abstract void initViews();

    protected abstract void onReady();

    protected abstract void destroyPresenter();

    public static <T extends Comparable<T>> T log(T x) {
        Log.v("MYSELF", String.valueOf(x));
        return null;
    }

}