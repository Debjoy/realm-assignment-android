package com.example.realmassignment;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    private RealmResults<Student> mRealmResults;
    private Context mContext;
    MyAdapter(RealmResults<Student> mRealmResults,Context mContext){
        this.mRealmResults=mRealmResults;
        this.mContext=mContext;
    }
    @NonNull
    @Override
    public MyAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.myViewHolder holder, int position) {
        Student student=mRealmResults.get(position);
        holder.name.setText(student.getName());
        holder.dept.setText(student.getDept());
        holder.phone.setText(student.getPhone());
        holder.rollNo.setText("Roll No : "+String.valueOf(student.getRollNo()));
        if(student.isGender()){
            holder.gender.setText("Female");
        }else{
            holder.gender.setText("Male");
        }
        if(student.getDept().toString().equals("CSE"))
            holder.bar.setBackgroundColor(Color.argb(255,76,175,80));
        else if(student.getDept().toString().equals("IT"))
            holder.bar.setBackgroundColor(Color.argb(255,21,101,192));
        else if(student.getDept().toString().equals("ECE"))
            holder.bar.setBackgroundColor(Color.argb(255,251,192,45));
        else if(student.getDept().toString().equals("EE"))
            holder.bar.setBackgroundColor(Color.argb(255,211,47,47));

    }

    @Override
    public int getItemCount() {
        return mRealmResults.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView dept;
        private TextView rollNo;
        private TextView phone;
        private TextView gender;
        private RelativeLayout bar;
        public myViewHolder(@NonNull View ItemView){
            super(ItemView);
            name = ItemView.findViewById(R.id.name_tv);
            dept = ItemView.findViewById(R.id.dept_tv);
            rollNo=ItemView.findViewById(R.id.roll_tv);
            phone=ItemView.findViewById(R.id.phone_tv);
            gender=ItemView.findViewById(R.id.gender_tv);
            bar= ItemView.findViewById(R.id.category_image);
        }
    }
}
