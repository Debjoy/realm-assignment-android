package com.example.realmassignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;

public class DisplayActivity extends AppCompatActivity {
    private static final String TAG = "Experiment";
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mRecyclerView = findViewById(R.id.studentRecycler);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Student> personRealmResults = realm.where(Student.class).findAll();
        MyAdapter myAdapter = new MyAdapter(personRealmResults,this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(myAdapter);
    }
}
