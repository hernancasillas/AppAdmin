package com.purvik.studinfo;

/**
 * Created by 3537 on 06-11-2015.
 * Purvik Rana - Student Main Class
 * Attributes: id, enroll_no, name, phone_number;
 * Constructor: empty, all parameter, three parameter
 * Getter & Setters: all attributes
 */
public class Worker {

    //Private Variable
    int _id;
    int _id_job;
    String _name;
    String _phone_number;
    String _address;

    //empty constructor
    public Worker() {
    }

    //all parameter in Constructor
    public Worker(int _id, String _name, int _id_job, String _phone_number, String _address) {
        this._id = _id;
        this._name = _name;
        this._id_job = _id_job;
        this._phone_number = _phone_number;
        this._address = _address;
    }

    //three parameter Constructor
    public Worker(int _id_job, String _name, String _phone_number, String _address) {
        this._id_job = _id_job;
        this._name = _name;
        this._phone_number = _phone_number;
        this._address = _address;
    }


    //Getters for  all fields

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_id_job() {
        return _id_job;
    }

    public void set_id_job(int _id_job) {
        this._id_job = _id_job;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phone_number() {
        return _phone_number;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }
}
