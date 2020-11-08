package com.smsaz.class9_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonCell1 = findViewById(R.id.buttonCell1);

        buttonCell1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCell1.setText("O");
            }
        });

    }
}