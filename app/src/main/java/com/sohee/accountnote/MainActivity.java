package com.sohee.accountnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button addplace;
    Button findplace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addplace = (Button)findViewById(R.id.button);
        findplace = (Button)findViewById(R.id.button2);

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
