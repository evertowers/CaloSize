package com.example.calosize;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private EditText eRegName;
    private EditText eNewPassword;
    private EditText eRegPassword;
    private Button eRegister;
    private TextView alreadySignUp;

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
        alreadySignUp = findViewById(R.id.tvAlreadySign);

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
                        credentials.addCredentials(regName, regPassword, "", "", "");
                        sharedPreferencesEditor.putString(regName, regPassword);
                        sharedPreferencesEditor.apply();


                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        alreadySignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
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
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(RegistrationActivity.this);

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