package com.purvik.studinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.method.ArrowKeyMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class UsuarioMain extends AppCompatActivity {

    DBHandler db;
    TextView tvStdInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_main);

        db = new DBHandler(this);
        final EditText search = (EditText) findViewById(R.id.etSearch);
        final Button boton = (Button) findViewById(R.id.btnSearch);
        final TextView texto = (TextView) findViewById(R.id.chambapp);


        //final String busqueda = search.getText().toString();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busqueda = search.getText().toString();


                tvStdInfo = (TextView) findViewById(R.id.lv);
                //tvStdInfo.setVisibility(View.VISIBLE);

                tvStdInfo.setMovementMethod(ArrowKeyMovementMethod.getInstance());

                tvStdInfo.setText("");	//	clear text area at each button press
                //tvStdInfo.setTextColor(Color.MAGENTA);
                tvStdInfo.setPadding(5, 2, 5, 2);

                List<Worker> workerList = db.getWorkersByJob(busqueda);	//	fetch List of BlockedNumbers form DB  method - 'getAllBlockedNumbers'

                if(workerList.size() == 0)
                {
                    Toast.makeText(getApplicationContext(),"El trabajador que buscas, no existe. \n Intenta con otro.",Toast.LENGTH_LONG).show();
                }
                else{
                    for (Worker std : workerList) {
                        String stdDetail = "\n\tNombre: " + std.get_name() + "\n\tTelefono: "+ std.get_phone_number()
                                + "\n\tDireccion: " + std.get_address() + "\n\tRating:"+std.get_rating();
                        tvStdInfo.append("\n"+ stdDetail);
                    }
                    search.setVisibility(View.INVISIBLE);
                    boton.setVisibility(View.INVISIBLE);
                }




            }
        });
    }
}
