package com.example.realmassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText dept;
    private EditText rollNo;
    private EditText phoneNo;
    private Switch gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name_et);
        dept=findViewById(R.id.dept_et);
        rollNo=findViewById(R.id.roll_et);
        phoneNo=findViewById(R.id.phone_et);
        gender=findViewById(R.id.gender_sw);
    }
    public void upload(View view){
        if(name.getText().toString().equals("")){
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            name.requestFocus();
            return;
        }else if(dept.getText().toString().equals("")){
            Toast.makeText(this, "Please enter your dept", Toast.LENGTH_SHORT).show();
           dept.requestFocus();
            return;
        }else if(rollNo.getText().toString().equals("")){
            Toast.makeText(this, "Please enter your roll no", Toast.LENGTH_SHORT).show();
            rollNo.requestFocus();
            return;
        }else if(phoneNo.getText().toString().equals("")){
            Toast.makeText(this, "Please enter your phone no", Toast.LENGTH_SHORT).show();
            phoneNo.requestFocus();
            return;
        }
        String deptname=dept.getText().toString().toLowerCase();
        if(!deptname.equals("cse")&& !deptname.equals("ece")&& !deptname.equals("it")&& !deptname.equals("ee")){
            Toast.makeText(this, "Please enter either CSE,IT,ECE or EE", Toast.LENGTH_SHORT).show();
            dept.setText("");
            dept.requestFocus();
            return;
        }
        submit();
    }

    public void submit(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        try{
            Student student=realm.createObject(Student.class,System.currentTimeMillis()/1000);
            student.setName(name.getText().toString());
            student.setDept(dept.getText().toString().toUpperCase());
            student.setRollNo(Integer.parseInt(rollNo.getText().toString()));
            student.setPhone(phoneNo.getText().toString());
            student.setGender(gender.isChecked());
            realm.commitTransaction();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            realm.cancelTransaction();
            Toast.makeText(this, "Falure: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();

    }
    public void onDisplayButtonPressed(View view){
        Intent intent = new Intent(this,DisplayActivity.class);
        startActivity(intent);
    }


}
