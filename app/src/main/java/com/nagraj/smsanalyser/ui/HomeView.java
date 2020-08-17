package com.nagraj.smsanalyser.ui;

import com.nagraj.base.BaseView;
import com.nagraj.local.Message;

import java.util.List;

public interface HomeView extends BaseView {
    void setMessages(List<Message> messageList);
}
