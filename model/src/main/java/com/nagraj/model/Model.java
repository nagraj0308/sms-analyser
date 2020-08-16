package com.nagraj.model;

import javax.inject.Inject;

public class Model {

    // private final AndroidServer0308Service service;

//    @Inject
//    public Model(AndroidServer0308Service service, PreferenceData preferenceData) {
//        //this.service = service;
//    }

    @Inject
    public Model() {
    }

    //User

//    public Observable<UserLoginResponse> loginUser(String username, String password) {
//        return service.loginUser(new UserLoginRequest(username, password));
//    }


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
