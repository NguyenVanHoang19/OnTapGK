package com.example.ontapgk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button btnExit;
    private Spinner spTenFile;
    private TextView edtNoiDung;
    private String[] fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        loadDataSpinner();
        spTenFile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int index = adapterView.getSelectedItemPosition();
                    docFile(fileName[index]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void anhXa(){
        btnExit = (Button) findViewById(R.id.btnExit);
        edtNoiDung = (TextView) findViewById(R.id.edtNoiDung);
        spTenFile = (Spinner) findViewById(R.id.spTenFile);
    }
    public void loadDataSpinner(){
        getFileName();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,fileName);
        spTenFile.setAdapter(arrayAdapter);
    }
    private void getFileName(){
        try {
           fileName = getAssets().list("assde");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void docFile(String fileName){
        try {
            InputStream inputStream = getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            String myString  = new String(buffer);
            edtNoiDung.setText(myString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}