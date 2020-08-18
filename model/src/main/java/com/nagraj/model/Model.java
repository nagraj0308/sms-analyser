package com.nagraj.model;

import android.content.Context;
import com.nagraj.local.Message;
import com.nagraj.local.MessageService;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;

public class Model {

     private final MessageService messageService;

    @Inject
    public Model(MessageService messageService) {
        this.messageService=messageService;
    }

    public Observable<List<Message>> getMessages(Context context) {
        return messageService.getMessages(context);
    }

}
