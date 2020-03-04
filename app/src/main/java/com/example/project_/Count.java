package com.example.project_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Count extends AppCompatActivity {

    private Button mAddBtn , mCancelBtn;
    private EditText mfullname,mGender,mOccupation,mLocation,mPhone;

    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        mFirestore = FirebaseFirestore.getInstance();

        mfullname = (EditText) findViewById(R.id.cFullName);
        mGender = (EditText) findViewById(R.id.cGender);
        mOccupation = (EditText) findViewById(R.id.cOccupation);
        mLocation = (EditText) findViewById(R.id.cLocation);
        mPhone = (EditText) findViewById(R.id.cPhone);
        mAddBtn = (Button) findViewById(R.id.AddBtn);
        mCancelBtn = (Button) findViewById(R.id.CancelBtn);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = mfullname.getText().toString();
                String gender = mGender.getText().toString();
                String occupation = mOccupation.getText().toString();
                String location = mLocation.getText().toString();
                String phone = mPhone.getText().toString();

                Map<String,String> citizenMap = new HashMap<>();

                citizenMap.put("fullname", fullname);
                citizenMap.put("gender", gender);
                citizenMap.put("occupation", occupation);
                citizenMap.put("location", location);
                citizenMap.put("phone", phone);


                mFirestore.collection("Citizen").add(citizenMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Count.this,"Citizen Added!", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();

                        Toast.makeText(Count.this,"Error :"+error,Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Count.this,MainActivity.class);
                startActivity(intent);
            }
        });




    }
}
