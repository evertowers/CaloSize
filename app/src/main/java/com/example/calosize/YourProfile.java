package com.example.calosize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class YourProfile extends AppCompatActivity {

    private TextView calIntakeResult;
    private TextView userName;
    private TextView bmiResultNum;
    private TextView bmiResultEqui;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.your_profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.your_profile:
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

        calIntakeResult = findViewById(R.id.profileCalorie);
        userName = findViewById(R.id.profileUsername);
        bmiResultNum = findViewById(R.id.profileBMI);
        bmiResultEqui = findViewById(R.id.profileBMIEquivalent);

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        String pCalRes = sharedPreferences.getString("CalorieResult", "");
        String pUser = sharedPreferences.getString("LastSavedUsername", "");
        String pBMIRes = sharedPreferences.getString("BMIResult","");
        String pBMIEqui = sharedPreferences.getString("BMIEquivalent", "");

        calIntakeResult.setText(pCalRes);
        userName.setText(pUser);
        bmiResultNum.setText(pBMIRes);
        bmiResultEqui.setText(pBMIEqui);

    }
}