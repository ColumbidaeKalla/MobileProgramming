package com.example.andriod;import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate; // Import this
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    // Removed boolean darkMode = false; as it resets on recreate()

    // Score checker
    EditText txtMarks;
    Button btnCheck, btnTheme;

    // Fragment switching
    Button btnFrag1, btnFrag2;

    // Counter
    int counter = 0;
    TextView tvCounter;
    Button btnPlus, btnMinus;

    ListView listView;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        // ==================== Theme Toggle Logic Fixed ====================
        btnTheme.setOnClickListener(v -> {
            // Get the current mode
            int currentMode = AppCompatDelegate.getDefaultNightMode();

            // Toggle the mode
            if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            // Note: setDefaultNightMode automatically recreates the activity,
            // so we don't need to call recreate() manually here.
        });

        // ==================== Fragment Switching ====================
        btnFrag1 = findViewById(R.id.btnFrag1);
        btnFrag2 = findViewById(R.id.btnFrag2);

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(new FragmentOne());
        }

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

        // ==================== Lists ====================
        listView = findViewById(R.id.listView);
        String[] countries = {"Nepal", "India", "Germany", "USA"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, countries
        );
        listView.setAdapter(adapter);

        gridView = findViewById(R.id.gridView);
        // Reusing array for grid
        ArrayAdapter<String> gridAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, countries
        );
        gridView.setAdapter(gridAdapter);
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
            // Check theme to set correct default color (White for dark mode, Black for light)
            int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
            if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                tvCounter.setTextColor(Color.WHITE);
            } else {
                tvCounter.setTextColor(Color.BLACK);
            }
        } else {
            tvCounter.setTextColor(Color.GREEN);
        }
    }
}
