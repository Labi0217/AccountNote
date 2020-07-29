package com.sohee.accountnote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPlace extends AppCompatActivity {
    private DBHelper notedb;
    @BindView(R.id.monthdate)
    TextView monthdate;
    @BindView(R.id.saveB)
    Button save1;
    @BindView(R.id.nameE)
    EditText namee;
    @BindView(R.id.costE)
    EditText coste;
    @BindView(R.id.contentE)
    EditText contente;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.area)
    TextView area;
    @BindView(R.id.areareal)
    TextView areareal;
    @BindView(R.id.imageView)
    ImageView image;
    int id = 0;
    String[] items = {"지역", "서울특별시", "인천광역시","울산광역시","충청북도","전라남도"
            , "부산광역시", "광주광역시", "경기도", "충청남도", "경상북도", "경상남도", "대구광역시", "대전광역시", "강원도"
            , "전라북도", "제주특별자치도"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        ButterKnife.bind(this);

        Glide.with(this)
                .load("")
                .override(300,200)
                .into(image);

        notedb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int Value = extras.getInt("id");
            if(Value > 0) {
                Cursor rs = notedb.getData(Value);
                id = Value;
                rs.moveToFirst();
                String t = rs.getString(rs.getColumnIndex(DBHelper.NOTE_TITLE));
                String c = rs.getString(rs.getColumnIndex(DBHelper.NOTE_COST));
                String co = rs.getString(rs.getColumnIndex(DBHelper.NOTE_CONTENT));
                if(!rs.isClosed()) {
                    rs.close();
                }
                save1.setVisibility(View.INVISIBLE);

                namee.setText(t);
                coste.setText(c);
                contente.setText(co);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {  //text에 spinner값 넣는 부분
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                areareal.setText(items[position]);
                if(items[position] == "서울특별시") {
                    area.setText("서울특별시");
                } else if(items[position] == "인천광역시") {
                    area.setText("인천광역시");
                } else if(items[position] == "울산광역시") {
                    area.setText("울산광역시");
                } else if(items[position] == "충청북도") {
                    area.setText("충청북도");
                } else if(items[position] == "충청남도") {
                    area.setText("충청남도");
                } else if(items[position] == "전라북도") {
                    area.setText("전라북도");
                } else if(items[position] == "전라남도") {
                    area.setText("전라남도");
                } else if(items[position] == "광주광역시") {
                    area.setText("광주광역시");
                } else if(items[position] == "부산광역시") {
                    area.setText("부산광역시");
                } else if(items[position] == "경기도") {
                    area.setText("경기도");
                } else if(items[position] == "경상북도") {
                    area.setText("경상남도");
                } else if(items[position] == "대구광역시") {
                    area.setText("대구광역시");
                } else if(items[position] == "대전광역시") {
                    area.setText("대전광역시");
                } else if(items[position] == "강원도") {
                    area.setText("강원도");
                } else if(items[position] == "제주특별자치도") {
                    area.setText("제주특별자치도");
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                area.setText("");
            }
        });
    }
    public void insert(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (notedb.updateNote(id, namee.getText().toString(), coste.getText().toString(), contente.getText().toString(), area.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), FindPlace.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (notedb.insertNote(namee.getText().toString(), coste.getText().toString(), contente.getText().toString(), area.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "추가되었음", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), FindPlace.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "추가되지 않았음", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        }
    }
}
