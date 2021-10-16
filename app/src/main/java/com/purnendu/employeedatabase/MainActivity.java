package com.purnendu.employeedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private  EditText name,designation,age,employee_no, employee_id;
    private TableLayout records;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        name = findViewById(R.id.name);
        designation = findViewById(R.id.designation);
        age = findViewById(R.id.age);
        employee_no = findViewById(R.id.employee_no);
        Button add = findViewById(R.id.add);
        Button showAll=findViewById(R.id.showAll);
        records=findViewById(R.id.records);
        Button delete=findViewById(R.id.delete);
        employee_id=findViewById(R.id.employee_id);
        Button search=findViewById(R.id.search);
        Button update=findViewById(R.id.update);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty())
                {
                    name.setError("Required");
                    return;
                }
                if(designation.getText().toString().isEmpty())
                {
                    designation.setError("Required");
                    return;
                }
                if(age.getText().toString().isEmpty())
                {
                    age.setError("Required");
                    return;
                }
                if(employee_no.getText().toString().isEmpty())
                {
                    employee_no.setError("Required");
                    return;
                }
                 if(!validateData())
                 {
                     Toast.makeText(MainActivity.this, "Letter limit exceed", Toast.LENGTH_SHORT).show();
                     return;
                 }
                DbHelper dbHelper=new DbHelper(MainActivity.this);
                 if(dbHelper.insertEmployeeDetails(Integer.parseInt(employee_no.getText().toString()),name.getText().toString(),Integer.parseInt(age.getText().toString()),designation.getText().toString()))
                 {
                     Toast.makeText(MainActivity.this, "Data Added successfully", Toast.LENGTH_SHORT).show();
                     name.setText("");
                     designation.setText("");
                     age.setText("");
                     employee_no.setText("");
                 }

                 else
                     Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });


        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeTable();
                DbHelper dbHelper=new DbHelper(MainActivity.this);
                ArrayList<Model>list=dbHelper.getAllData();
                if(list.isEmpty())
                    Toast.makeText(MainActivity.this, "No records found", Toast.LENGTH_SHORT).show();
                else
                {
                    showTableData(list);
                }

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(employee_id.getText().toString().isEmpty())
                {
                    employee_id.setError("Required");
                    return;
                }
                DbHelper dbHelper=new DbHelper(MainActivity.this);
                if(dbHelper.deleteData(Integer.parseInt(employee_id.getText().toString())))
                {
                    employee_id.setText("");
                    Toast.makeText(MainActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                }


            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(employee_id.getText().toString().isEmpty())
                {
                    employee_id.setError("Required");
                    return;
                }
                DbHelper dbHelper=new DbHelper(MainActivity.this);
                if(dbHelper.searchData(Integer.parseInt(employee_id.getText().toString())).isEmpty())
                    Toast.makeText(MainActivity.this, "No records Found", Toast.LENGTH_SHORT).show();
                else
                {
                    removeTable();
                    showTableData(dbHelper.searchData(Integer.parseInt(employee_id.getText().toString())));
                }


                employee_id.setText("");


            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().isEmpty())
                {
                    name.setError("Required");
                    return;
                }
                if(designation.getText().toString().isEmpty())
                {
                    designation.setError("Required");
                    return;
                }
                if(age.getText().toString().isEmpty())
                {
                    age.setError("Required");
                    return;
                }
                if(employee_no.getText().toString().isEmpty())
                {
                    employee_no.setError("Required");
                    return;
                }
                if(employee_id.getText().toString().isEmpty())
                {
                    employee_id.setError("Required");
                    return;
                }
                if(!validateData())
                {
                    Toast.makeText(MainActivity.this, "Letter limit exceed", Toast.LENGTH_SHORT).show();
                    return;
                }
                DbHelper dbHelper=new DbHelper(MainActivity.this);
                if((dbHelper.update(Integer.parseInt(employee_id.getText().toString()),Integer.parseInt(employee_no.getText().toString()),name.getText().toString(),Integer.parseInt(age.getText().toString()),designation.getText().toString()))==1)
                {
                    Toast.makeText(MainActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    designation.setText("");
                    age.setText("");
                    employee_no.setText("");
                    employee_id.setText("");
                }
                else
                    Toast.makeText(MainActivity.this, "Not found Employee id ", Toast.LENGTH_SHORT).show();
            }
        });




    }
    private void showTableData(ArrayList<Model>list)
    {
        TableRow row=new TableRow(MainActivity.this);
        TextView idRow=new TextView(MainActivity.this);
        idRow.setText("Id");
        idRow.setTypeface(idRow.getTypeface(), Typeface.BOLD);
        idRow.setHighlightColor(Color.BLACK);
        row.addView(idRow);
        TextView employeeNo=new TextView(MainActivity.this);
        employeeNo.setText("No");
        employeeNo.setTypeface(employeeNo.getTypeface(), Typeface.BOLD);
        employeeNo.setHighlightColor(Color.BLACK);
        row.addView(employeeNo);
        TextView name=new TextView(MainActivity.this);
        name.setText("Name");
        name.setGravity(Gravity.CENTER_VERTICAL);
        name.setTypeface(name.getTypeface(), Typeface.BOLD);
        name.setHighlightColor(Color.BLACK);
        row.addView(name);
        TextView age=new TextView(MainActivity.this);
        age.setText("Age");
        age.setTypeface(age.getTypeface(), Typeface.BOLD);
        age.setHighlightColor(Color.BLACK);
        row.addView(age);
        TextView designation=new TextView(MainActivity.this);
        designation.setText("Designation");
        designation.setTypeface(designation.getTypeface(), Typeface.BOLD);
        designation.setHighlightColor(Color.BLACK);
        row.addView(designation);
        records.addView(row);




        for(Model model: list)
        {
            TableRow dataRow=new TableRow(MainActivity.this);
            TextView dataIdRow=new TextView(MainActivity.this);
            dataIdRow.setText(String.valueOf(model.id));
            dataRow.addView(dataIdRow);
            TextView dataEmployeeNo=new TextView(MainActivity.this);
            dataEmployeeNo.setText(String.valueOf(model.no));
            dataRow.addView(dataEmployeeNo);
            TextView dataName=new TextView(MainActivity.this);
            dataName.setText(model.name);
            dataName.setWidth(10);
            dataRow.addView(dataName);
            TextView dataAge=new TextView(MainActivity.this);
            dataAge.setText(String.valueOf(model.age));
            dataRow.addView(dataAge);
            TextView dataDesignation=new TextView(MainActivity.this);
            dataDesignation.setText(model.designation);
            dataDesignation.setWidth(10);
            dataRow.addView(dataDesignation);
            records.addView(dataRow);
        }
    }

    private  void removeTable()
    {
        int count = records.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = records.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }
    }

    private boolean validateData()
    {
        String employeeName=name.getText().toString();
        String employeeDesignation=designation.getText().toString();
        int employeeAge=Integer.parseInt(age.getText().toString());
        int employeeNo=Integer.parseInt(employee_no.getText().toString());

        return employeeName.length() < 30 && employeeDesignation.length() < 20 && employeeAge < 100 && employeeNo < 1000;
    }


}