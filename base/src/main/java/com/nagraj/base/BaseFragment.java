package com.nagraj.base;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.nagraj.base.databinding.LayoutProgressBinding;

import java.util.Objects;


public abstract class BaseFragment extends Fragment implements BaseView {
    MaterialAlertDialogBuilder materialAlertDialogBuilder;
    LayoutProgressBinding progressBinding;
    AlertDialog progressDialog;
    int progress = 0;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (null != getArguments()) {
            initArguments(getArguments());
        }
        initDependencies();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBinding = LayoutProgressBinding.inflate(getLayoutInflater());
        materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getContext());
        materialAlertDialogBuilder
                .setCancelable(false)
                .setView(progressBinding.getRoot());

        progressDialog = materialAlertDialogBuilder.create();
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(R.color.color_transparent);
        initViews();
        onReady();
    }

    @Override
    public void onDestroyView() {
        destroyPresenter();
        super.onDestroyView();
    }

    @Override
    public void showContent() {
        progress--;
        if(progress<=0) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }


    @Override
    public void showProgress() {
        progress++;
        if(progress<=1) {
            progressDialog.show();
        }

    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getActivity(), "Error : " + errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    protected abstract void initArguments(Bundle extras);

    protected abstract void initDependencies();

    protected abstract void initViews();

    protected abstract void onReady();

    protected abstract void destroyPresenter();

}
