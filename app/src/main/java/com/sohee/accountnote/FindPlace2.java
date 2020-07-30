package com.sohee.accountnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindPlace2 extends AppCompatActivity {

    DBHelper notedb;
    int id = 0;
    @BindView(R.id.contentreal)
    TextView contente;
    @BindView(R.id.costreal)
    TextView coste;
    @BindView(R.id.namereal)
    TextView namee;
    @BindView(R.id.datereal)
    TextView monthdate;
    @BindView(R.id.addressreal)
    TextView addresse;
    @BindView(R.id.editreal)
    Button editreal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_place2);

        ButterKnife.bind(this);

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
                String d = rs.getString(rs.getColumnIndex(DBHelper.NOTE_DATE));
                String addre = rs.getString(rs.getColumnIndex(DBHelper.NOTE_ADDRESS));
                if(!rs.isClosed()) {
                    rs.close();
                }

                namee.setText(t);
                coste.setText(c);
                contente.setText(co);
                monthdate.setText(d);
                addresse.setText(addre);
            }
        }
        editreal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id);
                Intent intent = new Intent(getApplicationContext(), AddPlace.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }

        });

    }

    public void delete(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                notedb.deleteNote(id);
                Toast.makeText(getApplicationContext(), "삭제되었음", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), FindPlace.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "삭제되지 않았음", Toast.LENGTH_SHORT).show();
            }
        }
    }
}