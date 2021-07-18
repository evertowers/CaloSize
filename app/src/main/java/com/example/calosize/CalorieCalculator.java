package com.example.calosize;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class CalorieCalculator extends AppCompatActivity {
    private EditText edit_weight;
    private EditText edit_height;
    private EditText edit_age;
    private TextView textview_show;
    private RadioButton female;
    private RadioButton male;
    private TextView textview_label;
    private RadioGroup sexRadioGroup;
    private Spinner mySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calo_calc);

        edit_weight = findViewById(R.id.editWeight);
        edit_height = findViewById(R.id.editHeight);
        edit_age = findViewById(R.id.editAge);
        textview_show = findViewById(R.id.tVShow);
        female = findViewById(R.id.radio_female);
        male = findViewById(R.id.radio_male);
        textview_label = findViewById(R.id.label);
        sexRadioGroup = findViewById(R.id.radioGroup);
        mySpinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Calculate Button
                Button calculate = findViewById(R.id.btCalo);
                calculate.setOnClickListener(v -> {
                    String w = edit_weight.getText().toString();
                    String h = edit_height.getText().toString();
                    String a = edit_age.getText().toString();

                    if (TextUtils.isEmpty(w)) {
                        edit_weight.setError("Item cannot be empty");
                        textview_label.setVisibility(View.INVISIBLE);
                    }
                    if (TextUtils.isEmpty(h)) {
                        edit_height.setError("Item cannot be empty");
                        textview_label.setVisibility(View.INVISIBLE);
                    }
                    if (TextUtils.isEmpty(a)) {
                        edit_age.setError("Item cannot be empty");
                        textview_label.setVisibility(View.INVISIBLE);
                    }
                    else {
                        int weight = Integer.parseInt(edit_weight.getText().toString());
                        int height = Integer.parseInt(edit_height.getText().toString());
                        int age = Integer.parseInt(edit_age.getText().toString());
                        double calIntake;
                        int ans;

                        double bmi = (10 * weight) + (6.25 * height) - (5 * age);
                        double fml = bmi - 161;
                        double ml = bmi + 5;

                        male.setError(null);
                        female.setError(null);

                        switch (position) {

                            case 0:
                                TextView errorTextview = (TextView) mySpinner.getSelectedView();
                                errorTextview.setError("Select Item");
                                textview_label.setVisibility(View.INVISIBLE);
                                textview_show.setVisibility(View.INVISIBLE);
                                break;

                            case 1:
                                textview_label.setVisibility(View.VISIBLE);
                                textview_show.setVisibility(View.VISIBLE);

                                if (female.isChecked()) {
                                    calIntake = (fml * 1.2);
                                    ans = (int) Math.round(calIntake);
                                    textview_show.setText(String.valueOf(ans));
                                }
                                else if (male.isChecked()) {
                                    calIntake = (ml * 1.2);
                                    ans = (int) Math.round(calIntake);
                                    textview_show.setText(String.valueOf(ans));
                                }
                                else {
                                    male.setError("");
                                    female.setError("");
                                    textview_label.setVisibility(View.INVISIBLE);
                                }
                                break;

                            case 2:
                                textview_label.setVisibility(View.VISIBLE);
                                textview_show.setVisibility(View.VISIBLE);

                                if (female.isChecked()) {
                                    calIntake = (fml * 1.375);
                                    ans = (int) Math.round(calIntake);
                                    textview_show.setText(String.valueOf(ans));
                                }
                                else if (male.isChecked()) {
                                    calIntake = ml * 1.375;
                                    ans = (int) Math.round(calIntake);
                                    textview_show.setText(String.valueOf(ans));
                                }
                                else {
                                    male.setError("");
                                    female.setError("");
                                    textview_label.setVisibility(View.INVISIBLE);
                                }
                                break;

                            case 3:
                                textview_label.setVisibility(View.VISIBLE);
                                textview_show.setVisibility(View.VISIBLE);

                                if (female.isChecked()) {
                                    calIntake = fml * 1.55;
                                    ans = (int) Math.round(calIntake);
                                    textview_show.setText(String.valueOf(ans));
                                }
                                else if (male.isChecked()) {
                                    calIntake = ml * 1.55;
                                    ans = (int) Math.round(calIntake);
                                    textview_show.setText(String.valueOf(ans));
                                }
                                else {
                                    male.setError("");
                                    female.setError("");
                                    textview_label.setVisibility(View.INVISIBLE);
                                }
                                break;

                            case 4:
                                textview_label.setVisibility(View.VISIBLE);
                                textview_show.setVisibility(View.VISIBLE);

                                if (female.isChecked()) {
                                    calIntake = fml * 1.725;
                                    ans = (int) Math.round(calIntake);
                                    textview_show.setText(String.valueOf(ans));
                                }
                                else if (male.isChecked()) {
                                    calIntake = ml * 1.725;
                                    ans = (int) Math.round(calIntake);
                                    textview_show.setText(String.valueOf(ans));
                                }
                                else {
                                    male.setError("");
                                    female.setError("");
                                    textview_label.setVisibility(View.INVISIBLE);
                                }
                                break;

                            case 5:
                                textview_label.setVisibility(View.VISIBLE);
                                textview_show.setVisibility(View.VISIBLE);

                                if (female.isChecked()) {
                                    calIntake = fml * 1.9;
                                    ans = (int) Math.round(calIntake);
                                    textview_show.setText(String.valueOf(ans));
                                }
                                else if (male.isChecked()) {
                                    calIntake = ml * 1.9;
                                    ans = (int) Math.round(calIntake);
                                    textview_show.setText(String.valueOf(ans));
                                }
                                else {
                                    male.setError("");
                                    female.setError("");
                                    textview_label.setVisibility(View.INVISIBLE);
                                }
                                break;

                            default:

                        }
                    }
                });

                Button clear = findViewById(R.id.clear_text);
                clear.setOnClickListener(v -> {
                    edit_weight.getText().clear();
                    edit_height.getText().clear();
                    edit_age.getText().clear();
                    textview_show.setText("");
                    textview_label.setVisibility(View.INVISIBLE);
                    mySpinner.setSelection(0);
                    sexRadioGroup.clearCheck();
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
                        return true;

                }
                return false;
            }
        });

    }
}