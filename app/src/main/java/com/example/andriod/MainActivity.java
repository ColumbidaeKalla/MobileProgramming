package com.example.andriod;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    // Theme toggle
    boolean darkMode = false;

    // Score checker
    EditText txtMarks;
    Button btnCheck, btnTheme;

    // Fragment switching
    Button btnFrag1, btnFrag2;

    // Counter
    int counter = 0;
    TextView tvCounter;
    Button btnPlus, btnMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Apply theme before super.onCreate()
        if (darkMode) {
            setTheme(R.style.Theme_MyApp_Dark);
        } else {
            setTheme(R.style.Theme_Andriod);   // default
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ==================== Score Checker ====================
        txtMarks = findViewById(R.id.txtMarks);
        btnCheck = findViewById(R.id.btnCheck);
        btnTheme = findViewById(R.id.btnTheme);

        btnCheck.setOnClickListener(v -> {
            String s = txtMarks.getText().toString().trim();
            if (s.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter a score", Toast.LENGTH_SHORT).show();
                return;
            }

            int score = Integer.parseInt(s);
            Intent i;
            String status;

            if (score < 40) {
                i = new Intent(MainActivity.this, FailActivity.class);
                status = "Fail";
            } else if (score < 80) {
                i = new Intent(MainActivity.this, FirstActivity.class);
                status = "Pass";
            } else {
                i = new Intent(MainActivity.this, DistinctionActivity.class);
                status = "Distinction";
            }

            i.putExtra("status", status);
            startActivity(i);
        });

        // Theme toggle
        btnTheme.setOnClickListener(v -> {
            darkMode = !darkMode;
            recreate(); // reload activity with new theme
        });

        // ==================== Fragment Switching ====================
        btnFrag1 = findViewById(R.id.btnFrag1);
        btnFrag2 = findViewById(R.id.btnFrag2);

        // Load default fragment
        loadFragment(new FragmentOne());

        btnFrag1.setOnClickListener(v -> loadFragment(new FragmentOne()));
        btnFrag2.setOnClickListener(v -> loadFragment(new FragmentTwo()));

        // ==================== Counter ====================
        tvCounter = findViewById(R.id.tvCounter);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);

        btnPlus.setOnClickListener(v -> {
            counter++;
            updateCounter();
        });

        btnMinus.setOnClickListener(v -> {
            counter--;
            updateCounter();
        });

        updateCounter();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    private void updateCounter() {
        tvCounter.setText(String.valueOf(counter));

        if (counter < 0) {
            tvCounter.setTextColor(Color.RED);
        } else if (counter == 0) {
            tvCounter.setTextColor(Color.BLACK);
        } else {
            tvCounter.setTextColor(Color.GREEN);
        }
    }
}
