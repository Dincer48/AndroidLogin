package com.example.asus.loginactivity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button loginButton;
    TextInputLayout usernameBox;
    TextInputLayout passwordBox;
    Boolean loginRequest=false;

    static Map<String,String > users=new HashMap<String,String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        users.put("dincer","123456");
        usernameBox = findViewById(R.id.usernameInput);
        passwordBox=findViewById(R.id.passwordInput);
        loginButton=findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameBox.getEditText().getText().toString().equals(""))
                    usernameBox.setError("User field cannot be empty");
                if(passwordBox.getEditText().getText().toString().equals(""))
                    passwordBox.setError("Password field cannot be empty!");

                        if (usernameBox.getEditText().getText().toString().equals("dincer")
                            && passwordBox.getEditText().toString().equals("123456")){
                            Intent gecisYap = new Intent(MainActivity.this, ListActivity.class);

                            startActivity(gecisYap);

                    }
                else {
                    Toast.makeText(MainActivity.this, "User login process is unsuccessful!", Toast.LENGTH_SHORT).show();
                }
                }

        });

        usernameBox.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passwordBox.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            passwordBox.setError(null);
            }
        });

    }
}

