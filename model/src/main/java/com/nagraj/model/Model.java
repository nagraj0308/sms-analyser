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


//    public Observable<ProfilePicture> getAdminProfilePicture() {
//        return Observable.create(emitter -> {
//            Firebase.DATABASE_PROFILE_PICTURES.child(preferenceData.getUserName()).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    ProfilePicture post = dataSnapshot.getValue(ProfilePicture.class);
//                    if (post != null) {
//                        emitter.onNext(post);
//                    } else {
//                        emitter.onError(new Exception("You have not updated profile picture!!"));
//                    }
//                    emitter.onComplete();
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    emitter.onError(databaseError.toException());
//                }
//            });
//
//        });
//    }


}
