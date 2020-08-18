package com.nagraj.local;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

public class MessageService {

    public MessageService() {
    }

    List<Message> messageList = new ArrayList<>();

    public Observable<List<Message>> getMessages( Context context) {
        ContentResolver cr = context.getContentResolver();

        return Observable.create(emitter -> {
            Cursor c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
            int totalSMS = 0;
            if (c != null) {
                totalSMS = c.getCount();
                if (c.moveToFirst()) {
                    for (int j = 0; j < totalSMS; j++) {
                        String smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE));
                        String sender = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                        String body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY));
                        Date date = new Date(Long.parseLong(smsDate));
                        String type;
                        if (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))==Telephony.Sms.MESSAGE_TYPE_INBOX){
                            messageList.add(Message.create(sender,(j%2)==1,j/3,date));
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

}
