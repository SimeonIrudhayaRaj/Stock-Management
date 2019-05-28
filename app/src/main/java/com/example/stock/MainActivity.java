package com.example.stock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();



    }

    public void signup(View view) {
        final String email,password;
        password = ((EditText)findViewById(R.id.pass)).getText().toString().toLowerCase().trim();
        email = ((EditText)findViewById(R.id.email)).getText().toString().toLowerCase().trim();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplication(),"success",Toast.LENGTH_LONG).show();
                    Map<String, String> quantity = new HashMap<>();
                    char a='A';
                    quantity.put("quantity","0");
                    quantity.put("Name", String.valueOf(a));
                    for(int i=1;i<=20;i++)
                    {
                        quantity.put("Name", String.valueOf(a));
                        db.collection(email).document("stock "+a).set(quantity);
                        a++;

                    }

                }
                else
                {
                    Toast.makeText(getApplication(),"Failed",Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    public void login(View view) {
        final String email,password;
        password = ((EditText)findViewById(R.id.pass)).getText().toString().toLowerCase().trim();
        email = ((EditText)findViewById(R.id.email)).getText().toString().toLowerCase().trim();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    SharedPreferences sharedpreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("email", email);
                    editor.commit();
                    Intent i= new Intent(getApplication(),choice.class);
                    i.putExtra("email",email);
                    startActivity(i);
                    Toast.makeText(getApplication(),"success",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplication(),"Failed",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
