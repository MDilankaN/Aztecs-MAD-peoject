package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Student_results extends AppCompatActivity {
    Button button;
    RecyclerView recyclerView;
    DatabaseReference ref;
    ArrayList<Result> resultsList;
    RecyclerAdapter_ResultList recyclerAdapter;
    DividerItemDecoration dividerItemDecoration;
    EditText ED;
    public static final int putextra = 0;
    int n=0;
    int mark = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_results);
        OnclickButtonListener();

        recyclerView = findViewById(R.id.resultRecyclerview);
        dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        Intent intent = getIntent();
        String qname = intent.getStringExtra("Extar");
        System.out.println(qname);
        ref = FirebaseDatabase.getInstance().getReference().child("Result").child("IT").child(qname);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    resultsList = new ArrayList<>();
                    for (DataSnapshot DS : snapshot.getChildren()) {
                        Result R = new Result();
                        resultsList.add(R);
                        R.setSName(DS.child("name").getValue().toString());
                        R.setSResult(Integer.parseInt(DS.child("result").getValue().toString()));
                        n++;
                    }
                    recyclerAdapter = new RecyclerAdapter_ResultList(resultsList);
                    recyclerView.setAdapter(recyclerAdapter);
                }else {
                    View view = null;
                    Snackbar.make(view,"No one is attempt for this quiz yet",Snackbar.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void OnclickButtonListener() {
        ED = (EditText) findViewById(R.id.edPassMark);
        button = findViewById(R.id.calculateBtn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       try {
                               mark = Integer.parseInt(ED.getText().toString());
                               Intent intent = new Intent(Student_results.this, Pass_Student.class);
                               intent.putExtra("AllResult", n);
                               intent.putExtra("mark", mark);
                               startActivity(intent);
                           } catch (NumberFormatException e) {
                            Snackbar.make(view,"Invalid mark",Snackbar.LENGTH_SHORT).show();
                       }
                    }
                }
        );
    }
}