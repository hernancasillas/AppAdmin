package com.purvik.studinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class Table extends AppCompatActivity {

    TextView tvStdInfo;

    //DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        //db = new DBHandler(this);

        //View Block Number List in the Text View Widget
        tvStdInfo = (TextView) findViewById(R.id.tvStdList);

        tvStdInfo.setMovementMethod(ArrowKeyMovementMethod.getInstance());

        tvStdInfo.setText("");	//	clear text area at each button press
        //tvStdInfo.setTextColor(Color.MAGENTA);
        tvStdInfo.setPadding(5, 2, 5, 2);

        /*List<Student> studentsList = db.getAllStudentList();	//	fetch List of BlockedNumbers form DB  method - 'getAllBlockedNumbers'

        for (Student std : studentsList) {

            String stdDetail = "\n\nID: " + std.get_id()+ "\n\tENO: " + std.get_enroll_no() +"\n\tNombre: " + std.get_name() + "\n\tTel: "+ std.get_phone_number()
                    + "\n\tDir: " + std.get_address();
            tvStdInfo.append("\n"+ stdDetail);
        }*/
    }
}
