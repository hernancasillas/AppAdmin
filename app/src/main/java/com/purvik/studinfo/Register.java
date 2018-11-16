package com.purvik.studinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    DBHandler db;
    String name;
    String usuario;
    String passwd;
    String tel;
    String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBHandler(this);

        final Button registrarse = (Button) findViewById(R.id.btnRegister);
        final EditText nombre = (EditText) findViewById(R.id.etName);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.etPassword2);
        final EditText phone = (EditText) findViewById(R.id.etPhone);
        final EditText email = (EditText) findViewById(R.id.etEmail);

        final int userType = 1;


        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nombre.getText().toString();
                usuario = username.getText().toString();
                passwd = password.getText().toString();
                tel = phone.getText().toString();
                mail = email.getText().toString();

                db.addNewUser(new Usuario(name, usuario, mail, passwd, tel, userType));

                Intent intent = new Intent(Register.this, Login2.class);
                startActivity(intent);
            }
        });
    }
}
