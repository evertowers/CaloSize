package com.example.calosize;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BMIResult extends AppCompatActivity {

    TextView result_bmistr, result_bminum;
    ImageView result_img;
    String bmi, height, weight;
    float bmi_float, bmi_height, bmi_weight ;
    RelativeLayout bmi_color;
    Intent intent;
    Button bmirecalculate;
    Button saveBMIData;

    public static Credentials credentials;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);

        intent=getIntent();

        result_bminum=findViewById(R.id.result_text2);
        result_bmistr=findViewById(R.id.result_text1);
        bmi_color=findViewById(R.id.result_layout);
        result_img=findViewById(R.id.result_image);
        bmirecalculate=findViewById(R.id.layout_recalculate);
        saveBMIData=findViewById(R.id.layout_save);

        height=intent.getStringExtra("height");
        bmi_height=Float.parseFloat(height);

        weight=intent.getStringExtra("weight");
        bmi_weight=Float.parseFloat(weight);

        bmi_height=bmi_height/100;
        bmi_float=bmi_weight/(bmi_height*bmi_height);

        bmi=Float.toString(bmi_float);

        if(bmi_float<=18){
            result_bmistr.setText("UNDERWEIGHT");
            bmi_color.setBackgroundColor(Color.rgb(68,142,228));
            result_img.setImageResource(R.drawable.body);
        }
        else if(bmi_float<=24){
            result_bmistr.setText("HEALTHY");
            bmi_color.setBackgroundColor(Color.rgb(68,203,23));
            result_img.setImageResource(R.drawable.body);
        }
        else if(bmi_float<=29){
            result_bmistr.setText("OVERWEIGHT");
            bmi_color.setBackgroundColor(Color.rgb(239,253, 95));
            result_img.setImageResource(R.drawable.body);
        }
        else if(bmi_float<=39){
            result_bmistr.setText("OBESE");
            bmi_color.setBackgroundColor(Color.rgb(255,102,0));
            result_img.setImageResource(R.drawable.body);
        }
        else{
            result_bmistr.setText("EXTREMELY OBESE");
            bmi_color.setBackgroundColor(Color.rgb(255,0,0));
            result_img.setImageResource(R.drawable.body);
        }

        result_bminum.setText(bmi);

        bmirecalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMIResult.this,BMIMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        saveBMIData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
                sharedPreferencesEditor = sharedPreferences.edit();

                String bmiRes = result_bminum.getText().toString();
                String bmiEqu = result_bmistr.getText().toString();
                sharedPreferencesEditor.putString("BMIResult", bmiRes);
                sharedPreferencesEditor.putString("BMIEquivalent", bmiEqu);

                sharedPreferencesEditor.apply();

                Toast.makeText(BMIResult.this, "Result saved", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(BMIResult.this);

        builder.setMessage("Do you want to exit?");
        builder.setTitle("Alert!");
        builder.setCancelable(false);
        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                Intent a = new Intent(Intent.ACTION_MAIN);
                                a.addCategory(Intent.CATEGORY_HOME);
                                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(a);
                            }
                        });
        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}