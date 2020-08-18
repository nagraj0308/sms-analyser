package com.nagraj.local;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class Message implements Parcelable {
    public static Message create(String sender,boolean isCredit,double amount) {

        return new AutoValue_Message(sender,isCredit,amount);
    }

    public abstract String sender();

    public abstract boolean isCredit();

    public abstract double amount();

}
