package com.example.nikma.shopping_s;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
    EditText n,d,p;
    Button button,logout,choose;
    ImageView imageView;
   private DatabaseReference databaseReference;
    private StorageReference mref;
    Uri imageuri;
    public static final int PICK_IMAGE_REQUST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfilechooser();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploaddata();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("food");
        //mref = FirebaseStorage.getInstance().getReference("uploads");
    }

    private void init() {
        n = findViewById(R.id.na);
        d= findViewById(R.id.des);
        p = findViewById(R.id.price);
        imageView = findViewById(R.id.img);
        button = findViewById(R.id.btn);
        logout = findViewById(R.id.logout);
        choose = findViewById(R.id.choose);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE_REQUST && resultCode==RESULT_OK &&data !=null){
            imageuri = data.getData();
            Glide.with(getApplicationContext()).load(imageuri).into(imageView);
        }
    }

    private void logout() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent i = new Intent(getApplicationContext(),login.class);
                startActivity(i);
                finish();
            }
        });
    }
    private String imageextension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploaddata(){


            final StorageReference fileref = FirebaseStorage.getInstance().getReference("uploads").child(System.currentTimeMillis()+
            "."+imageextension(imageuri));
            UploadTask uploadTask = fileref.putFile(imageuri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw task.getException();
                    }
                    return  fileref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri taskresult = task.getResult();
                        model m = new model(n.getText().toString().trim(),taskresult.toString(),
                                d.getText().toString().trim(),Integer.parseInt(String.valueOf(p.getText())));
                        String uploadID = databaseReference.push().getKey();
                        databaseReference.child(uploadID).setValue(m);
                    }
                }
            });

        }

    private void openfilechooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUST);
    }

}
