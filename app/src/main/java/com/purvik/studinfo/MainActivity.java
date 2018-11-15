package com.purvik.studinfo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AddStudentDialog.AddStudentDialogListener,
                                UpdateStudentInfo.UpdateStudentDialogListener, DeleteStudentDialog.DeleteStudentDialogListener, AddJobDialog.AddJobDialogListener {

    Button btnAddPlumber, btnUpdateInfo, btnShowDetails, btnDeleteInfo, btnAddJob;
    TextView tvStdInfo;
    private String TAG = "StudInfo";
    SQLiteDatabase dtb;
    int btnBackPressCounter = 0;
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHandler(this);

        btnAddPlumber = (Button)findViewById(R.id.btnAddPlumber);
        btnShowDetails = (Button)findViewById(R.id.btnShowDetails);
        btnUpdateInfo = (Button)findViewById(R.id.btnUpdateInfo);
        btnDeleteInfo = (Button)findViewById(R.id.btnDeleteInfo);
        btnAddJob = (Button)findViewById(R.id.addJobButton);

        btnAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddJobDialog dialog = new AddJobDialog();
                dialog.show(getFragmentManager(), TAG);
            }
        });

        btnAddPlumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddStudentDialog dialog = new AddStudentDialog();
                dialog.show(getFragmentManager(), TAG);
            }
        });

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStudentInfo updateDialog = new UpdateStudentInfo();
                updateDialog.show(getFragmentManager(),TAG);
            }
        });

        btnDeleteInfo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                DeleteStudentDialog deleteDialog = new DeleteStudentDialog();
                deleteDialog.show(getFragmentManager(),TAG);

            }
        });

        btnShowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //View Block Number List in the Text View Widget
                tvStdInfo = (TextView) findViewById(R.id.tvStdList);

                tvStdInfo.setMovementMethod(ArrowKeyMovementMethod.getInstance());

                tvStdInfo.setText("");	//	clear text area at each button press
               //tvStdInfo.setTextColor(Color.MAGENTA);
                tvStdInfo.setPadding(5, 2, 5, 2);



                List<Job> jobList = db.getAllJobsList();

                for (Job job : jobList) {

                    String stdDetail = "\n\nID: " + job.getId()+ "\n\tJOB: " + job.getName();
                    tvStdInfo.append("\n"+ stdDetail);
                }

                List<Worker> workerList = db.getAllStudentList();	//	fetch List of BlockedNumbers form DB  method - 'getAllBlockedNumbers'

                for (Worker std : workerList) {

                    String stdDetail = "\n\nID: " + std.get_id()+ "\n\tJOB: " + std.get_id_job() +"\n\tNombre: " + std.get_name() + "\n\tTel: "+ std.get_phone_number()
                            + "\n\tDir: " + std.get_address();
                    tvStdInfo.append("\n"+ stdDetail);

                    Log.i("TAG", stdDetail);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAddButtonClick(DialogFragment dialog){
        EditText jobName = (EditText) dialog.getDialog().findViewById(R.id.workName);
        String job = jobName.getText().toString();


        db.addNewJob(new Job(job));

        Log.d("HOLA", "AGRGADO");

    }

    @Override
    public void onSaveButtonClick(DialogFragment dialog) {

        //		Get enrollNumber
        /*EditText entEnrolNo = (EditText) dialog.getDialog().findViewById(R.id.edtEnrollNo);
        String enrollNo = entEnrolNo.getText().toString();
        int idJob =Integer.parseInt(entEnrolNo.getText().toString());*/

        EditText etEmpleo = (EditText) dialog.getDialog().findViewById(R.id.etEmpleoName);
        String nombreEmpleo = etEmpleo.getText().toString();

        int idJOB = db.getIdJob(nombreEmpleo);

//		Get Name
        EditText entName = (EditText) dialog.getDialog().findViewById(R.id.edtName);
        String name = entName.getText().toString();

        //		Get Phone Number
        EditText entPhnNo = (EditText) dialog.getDialog().findViewById(R.id.edtPhoneNo);
        String  phnNo = entPhnNo.getText().toString();

        EditText entAddress = (EditText) dialog.getDialog().findViewById(R.id.edtAddress);
        String address = entAddress.getText().toString();


       // boolean check_enrollNo = checkEnrollNo(enrollNo);

        boolean check_name = checkName(name);

        boolean check_phnNo = checkPhoneNo(phnNo);

        if(check_name == false || check_phnNo == false || idJOB == 0){

            Toast.makeText(getApplicationContext(),"Tel 10 Digitos!!!!!!",Toast.LENGTH_LONG).show();
        }else{

            db.addNewWorker(new Worker(idJOB, name, phnNo, address));

            Toast.makeText(getApplicationContext(),  "Trabajador agregado",Toast.LENGTH_LONG).show();
        }



        Toast.makeText(getApplicationContext(),"\nNo: " + idJOB + "\nNombre: " + name + "\nTelefono: " + phnNo + "\nDireccion: " + address,Toast.LENGTH_LONG).show();



    }

    //Check Id Number
    public boolean checkIdno(String Id_No){

        if(Id_No == ""){
            return false;
        }else{
            return true;
        }

    }

    //Check Enrollment number
    public boolean checkEnrollNo(String enr_No){

        if(enr_No == ""){

            return false;
        }else{
            return true;
        }

    }

    //Check Name
    public boolean checkName(String stdName){

        if(stdName == ""){
            return false;
        }else{
            return true;
        }
    }

    //Check Phone Number
    public boolean checkPhoneNo(String phn_No){

        if(phn_No == "" || phn_No.length() != 10){

            return false;
        }else{
            return true;
        }

    }

    @Override
    public void onUpdateButtonClick(DialogFragment dialog) {


//		Get ID
        EditText entId = (EditText) dialog.getDialog().findViewById(R.id.edt_UpdateId);
        String idNo = entId.getText().toString();
        int int_idNo =Integer.parseInt(entId.getText().toString());

        //		Get enrollNumber
        EditText entEnrolNo = (EditText) dialog.getDialog().findViewById(R.id.edt_UpdateEnrollNo);
        String enrollNo = entEnrolNo.getText().toString();
        int int_enrollNo =Integer.parseInt(entEnrolNo.getText().toString());


//		Get Name
        EditText entName = (EditText) dialog.getDialog().findViewById(R.id.edt_UpdateName);
        String name = entName.getText().toString();

        //		Get Phone Number
        EditText entPhnNo = (EditText) dialog.getDialog().findViewById(R.id.edt_UpdatePhoneNo);
        String  phnNo = entPhnNo.getText().toString();

        EditText entAddress = (EditText) dialog.getDialog().findViewById(R.id.edt_UpdateAddress);
        String  address = entAddress.getText().toString();

        boolean check_idNo = checkIdno(idNo);

        boolean check_enrollNo = checkEnrollNo(enrollNo);

        boolean check_name = checkName(name);

        boolean check_phnNo = checkPhoneNo(phnNo);

        if(check_idNo == false  || check_enrollNo == false || check_name == false || check_phnNo == false){

            Toast.makeText(getApplicationContext(),"Tel 10 Digitos!!!!!!",Toast.LENGTH_LONG).show();
        }else{

            boolean updateCheck = db.updateStudentInfo(int_idNo, int_enrollNo, name, phnNo, address);

            if(updateCheck == true){

            Toast.makeText(getApplicationContext(),"Plomero Actualizado",Toast.LENGTH_LONG).show();}
            else{

                Toast.makeText(getApplicationContext(),"No se pudo actualizar",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void callTemp(View v){

        startActivity(new Intent(MainActivity.this, SplashScreen.class));
    }

    @Override
    public void onDeleteButtonClick(DialogFragment dialog) {

        //		Get ID
        EditText entId = (EditText) dialog.getDialog().findViewById(R.id.edt_deleteID);
        String idNo = entId.getText().toString();
        int int_idNo =Integer.parseInt(entId.getText().toString());

        boolean check_idNo = checkIdno(idNo);

        if(check_idNo == false){

            Toast.makeText(getApplicationContext(),"Ingresa el ID correcto",Toast.LENGTH_LONG).show();

        }else{

            boolean deleCheck = db.deleteStudent(int_idNo);

            if(deleCheck == true){

                Toast.makeText(getApplicationContext(),"Plomero eliminado",Toast.LENGTH_LONG).show();}
            else{

                Toast.makeText(getApplicationContext(),"No eliminado",Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onBackPressed() {

        //CODE FOR EXIT ONLY IF DOUBLE BACK PRESSED - NOT WORKING
        ++btnBackPressCounter;
        if(btnBackPressCounter == 1){

            Toast.makeText(getBaseContext(), "Pulsa de nuevo para salir.", Toast.LENGTH_SHORT).show();

        }
        else {
			/*super.onBackPressed();
            if (interAd.isLoaded()) {
                interAd.show();*/
            finish();
            }

        }
}
