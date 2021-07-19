package com.example.calosize;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BMIMainActivity extends AppCompatActivity {

    TextView input_age, input_height, input_weight;
    SeekBar scroll_age, scroll_height, scroll_weight;
    RelativeLayout choice_male, choice_female;
    int current_age, current_height, current_weight;
    String age="100";
    String height="200";
    String weight="200";
    String user="0";
    android.widget.Button bmicalculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmimain);

        bmicalculate = findViewById(R.id.layout_calculate);
        input_age = findViewById(R.id.number_age);
        input_height = findViewById(R.id.number_height);
        input_weight = findViewById(R.id.number_weight);
        scroll_age = findViewById(R.id.seekbar_age);
        scroll_height = findViewById(R.id.seekbar_height);
        scroll_weight = findViewById(R.id.seekbar_weight);
        choice_male = findViewById(R.id.layout_male);
        choice_female = findViewById(R.id.layout_female);

        choice_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice_male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.clicked_layout));
                choice_female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.notclicked_layout));
                user="MALE";
            }
        });

        choice_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice_female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.clicked_layout));
                choice_male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.notclicked_layout));
                user="FEMALE";
            }
        });

        scroll_age.setMax(100);
        scroll_age.setProgress(0);
        scroll_age.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current_age = progress;
                age=String.valueOf(current_age);
                input_age.setText(age);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        scroll_height.setMax(200);
        scroll_height.setProgress(0);
        scroll_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current_height = progress;
                height=String.valueOf(current_height);
                input_height.setText(height);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        scroll_weight.setMax(200);
        scroll_weight.setProgress(0);
        scroll_weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current_weight = progress;
                weight=String.valueOf(current_weight);
                input_weight.setText(weight);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bmicalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(user.equals("0")){
                    Toast.makeText(getApplicationContext(),
                            "Select Gender",
                            Toast.LENGTH_LONG).show();
                }
                else if(age.equals("0")){
                    Toast.makeText(getApplicationContext(),
                            "Indicate your Age",
                            Toast.LENGTH_LONG).show();
                }
                else if(height.equals("0")){
                    Toast.makeText(getApplicationContext(),
                            "Indicate your Height",
                            Toast.LENGTH_LONG).show();
                }
                else if(weight.equals("0")){
                    Toast.makeText(getApplicationContext(),
                            "Indicate your Weight",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(BMIMainActivity.this, com.example.calosize.BMIResult.class);
                    intent.putExtra("gender", user);
                    intent.putExtra("age", age);
                    intent.putExtra("height", height);
                    intent.putExtra("weight", weight);

                    startActivity(intent);
                }

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.your_profile:
                        startActivity(new Intent(getApplicationContext(), YourProfile.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
}