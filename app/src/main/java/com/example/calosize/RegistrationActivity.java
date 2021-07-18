package com.example.calosize;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText eRegName;
    private EditText eRegEmail;
    private EditText eRegPassword;
    private Button eRegister;

    public static Credentials credentials;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eRegName = findViewById(R.id.etRegName);
        eRegEmail = findViewById(R.id.etRegEmail);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.etRegButton);

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();



        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regName = eRegName.getText().toString();
                String regEmail = eRegEmail.getText().toString();
                String regPassword = eRegPassword.getText().toString();

                if (validate(regName, regPassword)){
                    credentials = new Credentials(regName, regPassword);
                    sharedPreferencesEditor.putString("Username", regName);
                    sharedPreferencesEditor.putString("Password", regPassword);

                    sharedPreferencesEditor.apply();


                    Intent intent = new Intent(RegistrationActivity.this, com.example.calosize.MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validate(String username, String password){

        if (username.isEmpty() || password.length() < 8){
            Toast.makeText(this, "Please enter all details. Password should be at least 8 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}