package com.example.calosize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {

    private Button toCalo;
    private Button toBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        toCalo = findViewById(R.id.btCalo);
        toBmi = findViewById(R.id.btBmi);

        toCalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, CalorieCalculator.class);
                startActivity(intent);
            }
        });

        toBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, BMILogo.class);
                startActivity(intent);
            }
        });
    }
}