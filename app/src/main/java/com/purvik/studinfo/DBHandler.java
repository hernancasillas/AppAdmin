package com.purvik.studinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 3537 on 06-11-2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 95;

    // Database Name
    private static final String DATABASE_NAME = "Chambapp";

    // Contacts table name
    private static final String TABLE_STUDENT_DETAIL = "studentDetails";
    private static final String TABLE_JOB_DETAIL = "Oficio";
    private static final String TABLE_WORKER_DETAIL = "Trabajador";
    private static final String TABLE_USER_DETAIL = "Usuario";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ENROLL_NO = "enroll_no";
    private static final String KEY_NAME = "fullName";
    private static final String KEY_PHONE_NO = "phone_number";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_RATING = "rating";

    private static final String KEY_ID_JOB = "id_job";
    private static final String KEY_JOB_NAME = "job_name";

    private static final String KEY_ID_USER = "id_user";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_FULLNAME = "user_fullname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USER_TYPE = "user_type";


    public DBHandler(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_JOB_TABLE = "CREATE TABLE " + TABLE_JOB_DETAIL + "("
                + KEY_ID_JOB + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_JOB_NAME + " TEXT)";

        String CREATE_WORKER_TABLE = "CREATE TABLE " + TABLE_WORKER_DETAIL + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_ID_JOB + " INTEGER,"
                + KEY_NAME + " TEXT,"
                + KEY_PHONE_NO + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_RATING + " INTEGER,"
                + " FOREIGN KEY(" + KEY_ID_JOB + ") REFERENCES " + TABLE_JOB_DETAIL + "(" + KEY_ID_JOB + "))";

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER_DETAIL + "("
                + KEY_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FULLNAME + " TEXT,"
                + KEY_USERNAME + " TEXT, "
                + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_PHONE_NO + " TEXT,"
                + KEY_USER_TYPE + " INTEGER)";


        db.execSQL(CREATE_JOB_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_WORKER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOB_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKER_DETAIL);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Student Information
    void addNewWorker(Worker newWorker) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID_JOB, newWorker.get_id_job());
        values.put(KEY_NAME, newWorker.get_name());
        values.put(KEY_PHONE_NO, newWorker.get_phone_number());
        values.put(KEY_ADDRESS, newWorker.get_address());
        values.put(KEY_RATING, newWorker.get_rating());


        // Inserting Row
        db.insert(TABLE_WORKER_DETAIL, null, values);
        db.close(); // Closing database connection
    }

    void addNewJob(Job newJob)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //values.put(KEY_ID_JOB, newJob.getId());
        values.put(KEY_JOB_NAME, newJob.getName());

        db.insert(TABLE_JOB_DETAIL, null, values);
        db.close();
    }

    void addNewUser(Usuario newUser)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_FULLNAME, newUser.getUser_Name());
        values.put(KEY_USERNAME, newUser.getUser_Username());
        values.put(KEY_EMAIL, newUser.getUserEmail());
        values.put(KEY_PASSWORD, newUser.getUserPassword());
        values.put(KEY_PHONE_NO, newUser.getUserPhone());
        values.put(KEY_USER_TYPE, newUser.getUserType());

        db.insert(TABLE_USER_DETAIL, null, values);
        db.close();
    }




    public boolean updateStudentInfo(int updId, int updEnrolNo, String updName, String updPhoneNo, String updAddress) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_ID_JOB, updEnrolNo);
        args.put(KEY_NAME, updName);
        args.put(KEY_PHONE_NO, updPhoneNo);
        args.put(KEY_ADDRESS, updAddress);

        return db.update(TABLE_WORKER_DETAIL, args, KEY_ID + "=" + updId, null) > 0;
    }


    public boolean deleteStudent(int delID){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_WORKER_DETAIL, KEY_ID + "=" + delID, null) > 0;

    }



    // Getting All Students
    public List<Worker> getAllStudentList() {


        List<Worker> studentList = new ArrayList<Worker>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_WORKER_DETAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Worker worker = new Worker();
                worker.set_id(Integer.parseInt(cursor.getString(0)));
                worker.set_id_job(Integer.parseInt(cursor.getString(1)));
                worker.set_name(cursor.getString(2));
                worker.set_phone_number(cursor.getString(3));
                worker.set_address(cursor.getString(4));
                worker.set_rating(Integer.parseInt(cursor.getString(5)));

                // Adding contact to list
                studentList.add(worker);

            } while (cursor.moveToNext());
        }

        // return contact list
        return studentList;
    }

    public List<Job> getAllJobsList() {


        List<Job> jobList = new ArrayList<Job>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_JOB_DETAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Job job = new Job();

                job.setId(Integer.parseInt(cursor.getString(0)));
                job.setName(cursor.getString(1));

                // Adding contact to list
                jobList.add(job);

            } while (cursor.moveToNext());
        }

        // return contact list
        return jobList;
    }

    public int getIdJob(String nombreEmpleo)
    {
        String selectQuery = "SELECT  * FROM " + "Oficio WHERE job_name="+"'"+nombreEmpleo+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int idJOB;

        if (cursor.moveToFirst()) {
            do {
                idJOB=Integer.parseInt(cursor.getString(0));
                return idJOB;
            } while (cursor.moveToNext());
        }

        return 0;
    }

    public String getJobName(int id)
    {

        int idJob;
        String jobName;

        String selectQuery2 = "SELECT " + KEY_JOB_NAME + " FROM " + TABLE_JOB_DETAIL + " WHERE " + KEY_ID_JOB + "=" + id ;

        /*String selectQuery = "SELECT " + KEY_JOB_NAME + " FROM " + TABLE_JOB_DETAIL
                + " INNER JOIN " + TABLE_WORKER_DETAIL + " ON " + TABLE_JOB_DETAIL
                +"."+KEY_ID_JOB+"="+TABLE_WORKER_DETAIL+"."+ KEY_ID_JOB;*/

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery2, null);

        if(cursor.moveToFirst())
        {
            do {
                jobName = cursor.getString(0);
                return jobName;
            }while(cursor.moveToNext());
        }

        return "";
    }

    public List<Worker> getWorkersByJob(String name)
    {
        int id = 0;

        String selectQuery2 = "SELECT " + KEY_ID_JOB + " FROM " + TABLE_JOB_DETAIL + " WHERE(" + KEY_JOB_NAME+ "='"+ name+"')";
        List<Worker> workerList = new ArrayList<Worker>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor2 = db.rawQuery(selectQuery2, null);

        if(cursor2.moveToFirst())
        {
            do{
                id = Integer.parseInt(cursor2.getString(0));
            }while(cursor2.moveToNext());
        }

        /*String selectQuery = "SELECT " + KEY_NAME  + "," + KEY_PHONE_NO + "," + KEY_ADDRESS + "," + KEY_RATING + " FROM " + TABLE_WORKER_DETAIL
                + " WHERE(" + KEY_ID_JOB + " = SELECT " +KEY_ID_JOB + " FROM " + TABLE_JOB_DETAIL + " WHERE(" + KEY_JOB_NAME + "='" + name+"'))";*/
        String selectQuery = "SELECT " + KEY_NAME  + "," + KEY_PHONE_NO + "," + KEY_ADDRESS + "," + KEY_RATING + " FROM " + TABLE_WORKER_DETAIL
                + " WHERE(" + KEY_ID_JOB + "=" + id+")";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do {

                Worker worker = new Worker();
                //worker.set_id(Integer.parseInt(cursor.getString(0)));
                //worker.set_id_job(Integer.parseInt(cursor.getString(0)));
                worker.set_name(cursor.getString(0));
                worker.set_phone_number(cursor.getString(1));
                worker.set_address(cursor.getString(2));
                worker.set_rating(Integer.parseInt(cursor.getString(3)));

                workerList.add(worker);

            }while(cursor.moveToNext());
        }
        return workerList;
    }


}
