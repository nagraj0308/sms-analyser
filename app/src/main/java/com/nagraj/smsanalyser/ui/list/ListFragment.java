package com.nagraj.smsanalyser.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nagraj.local.Message;
import com.nagraj.smsanalyser.App;
import com.nagraj.smsanalyser.R;
import com.nagraj.smsanalyser.ui.BaseHomeFragment;
import com.nagraj.smsanalyser.databinding.FragmentListBinding;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ListFragment extends BaseHomeFragment implements ListView {
    @Inject
    ListPresenter presenter;

    private FragmentListBinding binding;
    RecyclerView rcvMessages;

    public ListFragment() {
    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initArguments(Bundle extras) {

    }

    @Override
    protected void initDependencies() {
        App.getComponent().inject(this);
    }

    @Override
    protected void initViews() {
        activityCallback.setPageData(getString(R.string.report_in_list), false);
        rcvMessages = binding.rvMessages;
    }

    public void setFilterOption(int filterOption) {
        if (filterOption != 0) {
            List<Message> filteredMessageList = new ArrayList<>();
            for (Message message : activityCallback.getMessageList()) {
                if (filterOption == 1 && message.isCredit()) {
                    filteredMessageList.add(message);
                } else if (filterOption == 2 && !message.isCredit()) {
                    filteredMessageList.add(message);
                }
            }
            rcvMessages.setAdapter(new ListAdapter(filteredMessageList));
        } else {
            rcvMessages.setAdapter(new ListAdapter(activityCallback.getMessageList()));
        }

    }

    @Override
    protected void onReady() {
        presenter.attachView(this);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvMessages.setLayoutManager(llm);
        setFilterOption(activityCallback.getFilterOption());
    }

    @Override
    protected void destroyPresenter() {
        binding = null;
        presenter.detachView();
    }
}
