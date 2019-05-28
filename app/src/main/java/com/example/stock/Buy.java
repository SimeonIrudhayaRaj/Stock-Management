package com.example.stock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Buy extends AppCompatActivity {
    String email;
    String st;
    FirebaseFirestore db;

    char name;
    int mq,change=0,q;
    TextView tv_st,tv_q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        SharedPreferences sharedpreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
        email=sharedpreferences.getString("email","none");
        Intent i = getIntent();
        st=i.getStringExtra("name");
        tv_q= findViewById(R.id.quan_buy);
        tv_st=findViewById(R.id.st_name);
        name=st.charAt(st.length()-1);
        db = FirebaseFirestore.getInstance();
        tv_st.setText("Select number of units from "+st+" to buy.");


        DocumentReference df= db.document("Market/stock "+name);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                mq=Integer.parseInt(documentSnapshot.getString("quantity"));
            }
        });


        df= db.document(email+"/stock "+name);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                q=Integer.parseInt(documentSnapshot.getString("quantity"));
            }
        });



        Toast.makeText(getApplication(),q+"  "+mq,Toast.LENGTH_LONG).show();

    }

    public void sub(View view) {
        if(change>0)
            change--;
        tv_q.setText(change+"");
    }

    public void add(View view) {
        if(change+q<mq)
            change++;
        tv_q.setText(change+"");

    }

    public void confirm(View view) {
        q=q+change;
        Map<String, String> quantity = new HashMap<>();
        quantity.put("quantity",q+"");
        quantity.put("Name",name+"");
        db.collection(email).document("stock "+name).set(quantity);


        mq=mq-change;
        Map<String, String> qq = new HashMap<>();
        qq.put("quantity",mq+"");
        qq.put("Name",name+"");
        db.collection("Market").document("stock "+name).set(qq);

        Intent i= new Intent(getApplication(),choice.class);
        i.putExtra("email",email);
        startActivity(i);    }
}