package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pass_Student extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference ref;
    ArrayList<Result> resultsList;
    RecyclerAdapter_ResultList recyclerAdapter;
    DividerItemDecoration dividerItemDecoration;
    TextView percentage;
    int n = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass__student);

        recyclerView = findViewById(R.id.passRecyclerview);
        Intent intent = getIntent();
        int AllResult = intent.getIntExtra("AllResult",0);

        dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ref = FirebaseDatabase.getInstance().getReference().child("Result").child("IT").child("Quiz 01");
        ref.orderByChild("result").startAt(AllResult).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    resultsList = new ArrayList<>();

                    for(DataSnapshot DS : snapshot.getChildren()){
                        Result R = new Result();
                        resultsList.add(R);
                        R.setSName(DS.child("name").getValue().toString());
                        R.setSResult(Integer.parseInt((DS.child("result").getValue().toString())));
                        n++;
                    }
                    recyclerAdapter = new RecyclerAdapter_ResultList(resultsList);
                    recyclerView.setAdapter(recyclerAdapter);
                }else{
                    Toast.makeText(getApplicationContext(),"can't find any child",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
            }
        });
        percentage = findViewById(R.id.PassResult);

        if(n == 0){
            //Toast.makeText(getApplicationContext(),"n ="+n,Toast.LENGTH_LONG).show();
        }else{

            percentage.setText(AllResult+"%");
        }

    }
}