package com.example.andriod;

import android.os.Bundle;
import android.widget.Button;
import androidx.fragment.app.Fragment;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnFrag1, btnFrag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFrag1 = findViewById(R.id.btnFrag1);
        btnFrag2 = findViewById(R.id.btnFrag2);

        // Load Fragment 1 by default
        loadFragment(new FragmentOne());

        btnFrag1.setOnClickListener(v -> loadFragment(new FragmentOne()));
        btnFrag2.setOnClickListener(v -> loadFragment(new FragmentTwo()));

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    /*boolean darkMode = false;
    EditText textV;
    Button btnCheck, btnTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Apply theme before super.onCreate()
        if (darkMode) {
            setTheme(R.style.Theme_MyApp_Dark);
        } else {
            setTheme(R.style.Theme_Andriod);   // your default theme
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textV = findViewById(R.id.txtMarks);
        btnCheck = findViewById(R.id.btnCheck);
        btnTheme = findViewById(R.id.btnTheme);

        // THEME CHANGE ON BUTTON CLICK ✔
        btnTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                darkMode = !darkMode;        // toggle theme
                recreate();                 // reload activity with new theme
            }
        });
    }*/


    /*    private int counter = 0;
        private TextView tvCounter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.counterbutton);

            tvCounter = findViewById(R.id.tvCounter);
            Button btnPlus = findViewById(R.id.btnPlus);
            Button btnMinus = findViewById(R.id.btnMinus);

            btnPlus.setOnClickListener(v -> {
                counter++;
                updateCounter();
            });

            btnMinus.setOnClickListener(v -> {
                counter--;
                updateCounter();
            });
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
        }*/

    /*EditText textV;
    Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textV = findViewById(R.id.txtMarks);
        btnCheck = findViewById(R.id.btnCheck);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = textV.getText().toString().trim();

                if (s.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a score", Toast.LENGTH_SHORT).show();
                    return;
                }

                int score = Integer.parseInt(s);
                Intent i;
                String status = "";   // store status once

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

                // 👉 putExtra only once
                i.putExtra("status", status);

                startActivity(i);
            }
        });
    }*/
}