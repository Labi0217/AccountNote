package com.sohee.accountnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FindPlace extends AppCompatActivity {
    DBHelper notedb;
    private ListView noteListView;
    ArrayAdapter mAdapter;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_place);

        notedb = new DBHelper(this);

        ArrayList array_list = notedb.getAllNote();

        mAdapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);

        noteListView = (ListView) findViewById(R.id.List);
        noteListView.setAdapter(mAdapter);

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg4) {
                String item = (String) ((ListView) parent).getItemAtPosition(position);
                String[] strArray = item.split(" ");
                int id=Integer.parseInt(strArray[0]);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id);
                Intent intent = new Intent(getApplicationContext(), FindPlace2.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });




        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                i = Calendar.YEAR;
                i1 = Calendar.MONTH;
                i2 = Calendar.YEAR;

            }
        });

    }

    protected void onResume() {
        super.onResume();
        mAdapter.clear();
        mAdapter.addAll(notedb.getAllNote());
        mAdapter.notifyDataSetChanged();
    }

}
