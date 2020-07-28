package com.sohee.accountnote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddPlace extends AppCompatActivity {
    private DBHelper notedb;
    TextView text1;
    Button save1;
    EditText namee;
    EditText coste;
    EditText contente;
    Spinner spinner;
    String[] items = {"지역", "서울특별시", "인천광역시","울산광역시","충청북도","전라남도"
            , "부산광역시", "광주광역시", "경기도", "충청남도", "경상북도", "경상남도", "대구광역시", "대전광역시", "강원도"
            , "전라북도", "제주특별자치도"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        spinner = findViewById(R.id.spinner);
        text1 = (TextView) findViewById(R.id.textView);
        namee = (EditText) findViewById(R.id.nameE);
        coste = (EditText) findViewById(R.id.costE);
        contente = (EditText) findViewById(R.id.contentE);
        save1 = (Button) findViewById(R.id.saveB);

        notedb = new DBHelper(this);

        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindPlace.class);
                startActivity(intent);
            }
        });

    }
}
