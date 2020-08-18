package com.nagraj.local;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.Date;


@AutoValue public abstract class Message implements Parcelable {
    public static Message create(String sender,boolean isCredit,double amount,Date date) {

        return new AutoValue_Message(sender,isCredit,amount,date);
    }

    public abstract String sender();

    public abstract boolean isCredit();

    public abstract double amount();

    public abstract Date date();
}
