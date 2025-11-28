package com.example.andriod;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);

        String status = getIntent().getStringExtra("status");
        TextView txt = findViewById(R.id.txtStatus);
        txt.setText("Status: " + status);
    }
}
