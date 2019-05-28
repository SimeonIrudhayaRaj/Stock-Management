package com.example.stock;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Market extends AppCompatActivity {
    String email,name;
    FirebaseFirestore db;
    ArrayList<data_model> details;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Adapter_st_display adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stock_display);
        Intent i = getIntent();
        details= new ArrayList<data_model>();
        email=i.getStringExtra("email");

        recyclerView= findViewById(R.id.st_display);
        layoutManager= new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        Toast.makeText(getApplication(),"Stocks in Market",Toast.LENGTH_LONG).show();

        db=FirebaseFirestore.getInstance();
        db.collection("Market")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int j=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                int a= Integer.parseInt(document.get("quantity").toString());j++;
                                name = document.get("Name").toString();
                                data_model dataModel = new data_model();
                                dataModel.setQuantity(a);
                                dataModel.setCol("Market");
                                dataModel.setStock("Stock "+name);
                                details.add(dataModel);
                                j++;
                            }

                            adapter = new Adapter_st_display(getBaseContext(),details);
                            recyclerView.setAdapter(adapter);
                        }
                        else {
                            Log.d("error2", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}
