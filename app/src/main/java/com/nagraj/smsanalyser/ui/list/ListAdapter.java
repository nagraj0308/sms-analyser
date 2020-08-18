package com.nagraj.smsanalyser.ui.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nagraj.local.Message;
import com.nagraj.smsanalyser.databinding.ViewSmsBinding;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListBinder> {
    List<Message> messageList;

    public ListAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public ListBinder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewSmsBinding itemBinding =
                ViewSmsBinding.inflate(layoutInflater, parent, false);
        return new ListBinder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBinder holder, int position) {
        holder.bind(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
