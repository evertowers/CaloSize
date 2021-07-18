package com.example.calosize;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BMIResult extends AppCompatActivity {

    TextView result_bmistr, result_bminum;
    ImageView result_img;
    String bmi, height, weight;
    float bmi_float, bmi_height, bmi_weight ;
    RelativeLayout bmi_color;
    Intent intent;
    android.widget.Button bmirecalculate;
    android.widget.Button bmigoback;


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
        bmigoback=findViewById(R.id.layout_goback);

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

        bmigoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMIResult.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}