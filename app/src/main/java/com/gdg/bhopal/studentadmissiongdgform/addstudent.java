package com.gdg.bhopal.studentadmissiongdgform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class addstudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    databasehelper mydb;
    Button submit;
    EditText nameEdt, emailEdt, dobEdt, perc12, perc10, number;
    RadioGroup rg;
    RadioButton malerb, femalerb, otherrb;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        mydb=new databasehelper(this);
        nameEdt = (EditText)findViewById(R.id.name);
        emailEdt = (EditText)findViewById(R.id.email);
        dobEdt = (EditText)findViewById(R.id.DOB);
        perc12 = (EditText)findViewById(R.id.mark12);
        perc10 = (EditText)findViewById(R.id.mark10);
        number = (EditText)findViewById(R.id.mobile);
        malerb = (RadioButton)findViewById(R.id.male);
        femalerb = (RadioButton)findViewById(R.id.female);
        otherrb = (RadioButton)findViewById(R.id.other);
        submit = (Button)findViewById(R.id.button);
        AddData();
    }
    public void AddData(){
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String gender = null;
                        if (malerb.isChecked()) {
                            gender = "Male";
                        } else if (femalerb.isChecked()) {
                            gender = "Female";
                        } else {
                            gender = "Other";
                        }
                        boolean isInserted = mydb.insertdata(nameEdt.getText().toString(),
                                emailEdt.getText().toString(),
                                dobEdt.getText().toString(),
                                "gender",
                                number.getText().toString(),
                                spinner.getSelectedItem().toString(),
                                Float.parseFloat(perc10.getText().toString()), Float.parseFloat(perc12.getText().toString()));
                        if (isInserted == true)
                            Toast.makeText(addstudent.this, "Data Inserted", Toast.LENGTH_SHORT).show();

                         else{
                        Toast.makeText(addstudent.this, "failed", Toast.LENGTH_SHORT).show();
                             }
                    }
                }
        );
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
