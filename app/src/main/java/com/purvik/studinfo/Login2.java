package com.purvik.studinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login2 extends AppCompatActivity {


    //final TextView registerLink = (TextView) findViewById(R.id.tvRegisterLink);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        final EditText username2 = (EditText) findViewById(R.id.editText3);
        final EditText password2 = (EditText) findViewById(R.id.editText4);
        final Button loginButton = (Button) findViewById(R.id.botonLogin);



        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String username = username2.getText().toString();
                String password = password2.getText().toString();

                if(username.equals("admin") && password.equals("admin"))
                {
                    Intent Admin = new Intent(Login2.this, Categories.class);
                    startActivity(Admin);
                }
                if(username.equals("usuario"))
                {
                    Intent User = new Intent(Login2.this, CategoriesUser.class);
                    startActivity(User);
                }

            }
        });
    }
}
