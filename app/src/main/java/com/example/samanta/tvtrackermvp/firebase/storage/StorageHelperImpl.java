package com.example.samanta.tvtrackermvp.firebase.storage;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelper;
import com.example.samanta.tvtrackermvp.firebase.database.DatabaseHelperImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class StorageHelperImpl implements StorageHelper {

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageReference = storage.getReference();
    DatabaseHelper helper = new DatabaseHelperImpl();

    @Override
    public void saveProfilePicture(Uri uri, final String userID) {

        final StorageReference reference = storageReference.child("userProfilPictures")
                .child(uri.getLastPathSegment());
        reference.putFile(uri).addOnSuccessListener
                (new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       reference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                           @Override
                           public void onComplete(@NonNull Task<Uri> task) {
                               String downloadUri = task.getResult().toString();
                               helper.updateUserProfilePic(downloadUri, userID);
                           }
                       });
                    }
                });

    }
}
