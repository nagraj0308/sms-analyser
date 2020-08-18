package com.nagraj.smsanalyser.ui.list;


import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.nagraj.local.Message;
import com.nagraj.smsanalyser.databinding.ViewSmsBinding;

public class ListBinder extends RecyclerView.ViewHolder {
     ViewSmsBinding binding;
    AppCompatTextView tvSn,tvSender,tvAmount,tvIsCredit;

    public ListBinder(ViewSmsBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        tvSn=binding.tvSn;
        tvSender=binding.tvSender;
        tvAmount=binding.tvAmount;
        tvIsCredit=binding.tvIsCredit;

    }

    public void bind(Message item) {
      tvSn.setText(String.valueOf(getAdapterPosition()+1));
      tvSender.setText(item.sender());
      tvAmount.setText(String.valueOf(item.amount()));
      tvIsCredit.setText(item.isCredit()?"credit":"debit");
    }
}
