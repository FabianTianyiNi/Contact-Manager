package com.example.txn160730.contactmanager;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by txn160730 on 4/1/2017.
 */
//class for saving contact information
public class Contact implements Comparable, Serializable{
    private String _firstname, _lastname, _email, _phonenumber, _birthdaydate, _name;


    public Contact(String _firstname, String _lastname, String _email, String _phonenumber, String _birthdaydate){
        this._firstname = _firstname;
        this._lastname = _lastname;
        this._email = _email;
        this._phonenumber = _phonenumber;
        this._birthdaydate = _birthdaydate;
        this._name = _firstname + "  " + _lastname + "  " + _phonenumber;
    }

    public String get_firstname() {
        return _firstname;
    }

    public String get_lastname() {
        return _lastname;
    }

    public String get_name(){return _name + "  " +_phonenumber; }

    public String get_email() {
        return _email;
    }

    public String get_phonenumber() {
        return _phonenumber;
    }

    public String get_birthdaydate() {
        return _birthdaydate;
    }

    public void set_firstname(String _firstname) {
        this._firstname = _firstname;
    }

    public void set_lastname(String _lastname) {
        this._lastname = _lastname;
    }

    public void set_name(){this._name = _firstname + _lastname; }

    public void reset(String _firstname, String _lastname, String _email, String _phonenumber, String _birthdaydate){
        this._firstname = _firstname;
        this._lastname = _lastname;
        this._email = _email;
        this._phonenumber = _phonenumber;
        this._birthdaydate = _birthdaydate;
        this._name = _firstname + "  "+ _lastname;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Contact c = (Contact) o;
        return this.get_firstname().compareTo(c.get_firstname());
    }
}
