package com.nagraj.smsanalyser.ui;

import android.content.Context;

import com.nagraj.base.BaseFragment;
import com.nagraj.local.Message;

import java.util.List;

public abstract class BaseHomeFragment extends BaseFragment {
    protected HomeActivityCallback activityCallback;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        activityCallback = (HomeActivityCallback) context;
    }

    public interface HomeActivityCallback {

        void setTitle(String title);

        List<Message> getMessageList();

    }
}
