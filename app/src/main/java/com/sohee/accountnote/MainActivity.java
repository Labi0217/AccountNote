package com.sohee.accountnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.nio.Buffer;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button addplace;
    @BindView(R.id.button2)
    Button findplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        addplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View target) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", 0);
                Intent intent = new Intent(getApplicationContext(), AddPlace.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        findplace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindPlace.class);
                startActivity(intent);
            }
        });

    }
}
