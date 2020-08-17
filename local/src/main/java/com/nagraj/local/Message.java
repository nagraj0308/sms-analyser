package com.nagraj.local;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;


@AutoValue public abstract class Message implements Parcelable {
    public static Message create(String message) {

        return new AutoValue_Message(message);
    }

    public abstract String message();
}
