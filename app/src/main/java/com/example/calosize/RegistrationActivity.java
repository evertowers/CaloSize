package com.example.calosize;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private EditText eRegName;
    private EditText eNewPassword;
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
        eNewPassword = findViewById(R.id.etNewPassword);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.etRegButton);

        credentials = new Credentials();

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        if(sharedPreferences != null){

            Map<String, ?> preferencesMap = sharedPreferences.getAll();

            if(preferencesMap.size() != 0){
                credentials.loadCredentials(preferencesMap);
            }

        }

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regName = eRegName.getText().toString();
                String regNewPassword = eNewPassword.getText().toString();
                String regPassword = eRegPassword.getText().toString();

                if (validate(regName, regPassword, regNewPassword)){
                    if (!regPassword.equals(regNewPassword)){
                        Toast.makeText(RegistrationActivity.this,"Password did not match", Toast.LENGTH_SHORT).show();
                    }else if(credentials.checkUsername(regName)){
                        Toast.makeText(RegistrationActivity.this,"Username already taken", Toast.LENGTH_SHORT).show();
                    }else {
                        credentials.addCredentials(regName, regPassword);
                        sharedPreferencesEditor.putString(regName, regPassword);
                        sharedPreferencesEditor.apply();


                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

    }

    private boolean validate(String username, String password, String newPassword){

        if (username.isEmpty() || password.length() < 8 || newPassword.length()<8){
            Toast.makeText(this, "Please enter all details. Password should be at least 8 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}