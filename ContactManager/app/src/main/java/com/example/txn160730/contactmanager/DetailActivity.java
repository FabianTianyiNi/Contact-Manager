package com.example.txn160730.contactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by tianyini on 03/04/2017.
 */

public class DetailActivity extends AppCompatActivity {
    private EditText firstName, lastName, email, birthday,phoneNumber;
    ArrayList<Contact> Cntacts = new ArrayList<Contact>();
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        firstName = (EditText)findViewById(R.id.firstnameInput);
        lastName = (EditText)findViewById(R.id.lastnameInput);
        email = (EditText)findViewById(R.id.emailInput);
        phoneNumber = (EditText)findViewById(R.id.phonenumberInput);
        birthday = (EditText)findViewById(R.id.birthdayInput);
        Intent intent = this.getIntent();
        if(intent != null){
            firstName.setText(intent.getStringExtra("firstname"));
            lastName.setText(intent.getStringExtra("lastname"));
            email.setText(intent.getStringExtra("email"));
            phoneNumber.setText(intent.getStringExtra("phonenumber"));
            birthday.setText(intent.getStringExtra("birthday"));
            position = intent.getIntExtra("position",-1);
        }



        final Button submitBtn = (Button)findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Cntacts.add(new Contact(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), phoneNumber.getText().toString(), birthday.getText().toString()));

                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("_fname", firstName.getText().toString());
                intent.putExtra("_lname", lastName.getText().toString());
                intent.putExtra("_email", email.getText().toString());
                intent.putExtra("_phone", phoneNumber.getText().toString());
                intent.putExtra("_birth", birthday.getText().toString());
                intent.setAction("action");
                intent.putExtra("recordList", Cntacts);

                setResult(1,intent);
                finish();
            }
        });

        final Button delBtn = (Button)findViewById(R.id.delBtn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("position", position);
                intent.setAction("action");
                setResult(2,intent);
                finish();
            }
        });
        final Button modifyBtn = (Button)findViewById(R.id.modifyBtn);
        modifyBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("firstname", firstName.getText().toString());
                intent.putExtra("lastname", lastName.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("phoneNumber", phoneNumber.getText().toString());
                intent.putExtra("birthday", birthday.getText().toString());
                intent.setAction("action");
                setResult(3,intent);
                finish();

            }
        });

        final Button cancelBtn = (Button)findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                setResult(4,intent);
                finish();
            }
        });

        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                submitBtn.setEnabled(!firstName.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}
