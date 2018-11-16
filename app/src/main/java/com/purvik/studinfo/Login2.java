package com.purvik.studinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login2 extends AppCompatActivity {


    //final TextView registerLink = (TextView) findViewById(R.id.tvRegisterLink);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        final EditText username2 = (EditText) findViewById(R.id.editText3);
        final EditText password2 = (EditText) findViewById(R.id.editText4);
        final Button loginButton = (Button) findViewById(R.id.botonLogin);
        final TextView registro = (TextView) findViewById(R.id.tvRegistro);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Registro = new Intent(Login2.this, Register.class);
                startActivity(Registro);
            }
        });



        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String username = username2.getText().toString();
                String password = password2.getText().toString();

                if(username.equals("admin") && password.equals("admin"))
                {
                    Intent Admin = new Intent(Login2.this, MainActivity.class);
                    startActivity(Admin);
                }
                else if(username.equals("usuario"))
                {
                    Intent User = new Intent(Login2.this, UsuarioMain.class);
                    startActivity(User);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Usuario/Contraseña Incorrectos",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
