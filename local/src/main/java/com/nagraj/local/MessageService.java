package com.nagraj.local;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;

public class MessageService {

    private static final String INR = "inr";
    private static final String RS = "rs";
    private static final String RUP = "₹";

    public MessageService() {
    }

    List<Message> messageList = new ArrayList<>();

    public Observable<List<Message>> getMessages(Context context) {
        ContentResolver cr = context.getContentResolver();

        return Observable.create(emitter -> {
            Cursor c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
            int totalSMS;
            if (c != null) {
                totalSMS = c.getCount();
                if (c.moveToFirst()) {
                    for (int j = 0; j < totalSMS; j++) {
                        String sender = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                        String body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY)).toLowerCase();
                        if (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE))) == Telephony.Sms.MESSAGE_TYPE_INBOX) {
                            if (body.contains(INR) || body.contains(RS) || body.contains(RUP)) {
                                double amount = getDoubleFromString(body);
                                if (body.contains("credited")) {
                                    messageList.add(Message.create(sender, true, amount));
                                } else if (body.contains("debited")) {
                                    messageList.add(Message.create(sender, false, amount));
                                }
                            }

                        }
                        c.moveToNext();
                    }
                }
                c.close();
            }
            emitter.onNext(messageList);
            emitter.onComplete();

        });
    }

    double getDoubleFromString(String string) {

        String[] array = string.split(" ");
        double value = 0.0;
        for (int i = 1; i < array.length; i++) {
            try {
                if (array[i].contains(RS)) {
                    value = Double.parseDouble(array[i].substring(2));
                } else if (array[i].contains(INR)) {
                    value = Double.parseDouble(array[i].substring(2));
                } else if (Double.parseDouble(array[i]) < 300000) {
                    value = Double.parseDouble(array[i]);
                    if (array[i - 1].equals(INR) || array[i - 1].equals(RS) || array[i - 1].equals(RUP))
                        break;
                }
            } catch (Exception ignored) {
            }
        }
        return value;
    }

}
