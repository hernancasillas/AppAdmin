package com.purvik.studinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    final EditText username = (EditText) findViewById(R.id.username);
    final EditText password = (EditText) findViewById(R.id.etPassword2);
    final Button loginButton = (Button) findViewById(R.id.botonLogin);
    final TextView registerLink = (TextView) findViewById(R.id.tvRegisterLink);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Login.this, Register.class);
                startActivity(register);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if(username.toString() == "admin" && password.toString() == "admin")
                {
                    Intent Admin = new Intent(Login.this, Categories.class);
                    startActivity(Admin);
                }
                if(username.toString() == "usuario")
                {
                    Intent User = new Intent(Login.this, CategoriesUser.class);
                    startActivity(User);
                }*/

            }
        });
    }




}
