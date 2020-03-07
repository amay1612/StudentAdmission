package com.gdg.bhopal.studentadmissiongdgform;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn1,btn2;
    databasehelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new databasehelper(this);
        btn1=(Button)findViewById(R.id.addStud);
        btn2=(Button)findViewById(R.id.viewStud);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaddstudent();

            }
        });
        viewbtn2();
    }

    public void openaddstudent(){
        Intent intent=new Intent(this,addstudent.class);
        startActivity(intent);

    }
    public void viewbtn2(){
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=mydb.getalldata();
                if (res.getCount()==0){
                    showmessage("Error","No Data Inserted");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Email :"+res.getString(1)+"\n");
                    buffer.append("DOB :"+res.getString(2)+"\n");
                    buffer.append("Gender :"+res.getString(3)+"\n");
                    buffer.append("Mobile :"+res.getString(4)+"\n");
                    buffer.append("Branch :"+res.getString(5)+"\n");
                    buffer.append("10th marks :"+res.getString(6)+"\n");
                    buffer.append("12th marks :"+res.getString(7)+"\n\n");
                }
                showmessage("Data",buffer.toString());
            }
        });
    }
    public void showmessage(String title,String message){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
