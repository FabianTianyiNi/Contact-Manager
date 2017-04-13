package com.example.txn160730.contactmanager;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView _firstName, _lastName;
    ArrayList<Contact> Contactlist  = new ArrayList<Contact>();
    ListView contactsListView;
    Contact contactRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsListView = (ListView) findViewById(R.id.contactLists);


        final Button addBtn = (Button)findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                populateList();
                //page transfer
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivityForResult(intent, 1);
                //startActivity(intent);
            }
        });
        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                Contact contact = (Contact)contactsListView.getItemAtPosition(position);
                bundle.putString("firstname", contact.get_firstname());
                bundle.putString("lastname", contact.get_lastname());
                bundle.putString("email", contact.get_email());
                bundle.putString("phonenumber", contact.get_phonenumber());
                bundle.putString("birthday", contact.get_birthdaydate());
                bundle.putInt("position", position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode){
            case 1:
                //data = getIntent();
                String _fname = data.getStringExtra("_fname");
                String _lname = data.getStringExtra("_lname");
                String _email = data.getStringExtra("_email");
                String _phone = data.getStringExtra("_phone");
                String _birth = data.getStringExtra("_birth");

                Toast.makeText(getApplicationContext(), _fname +" "+ _lname + " has been added into your Contacts!", Toast.LENGTH_SHORT).show();
                contactRecord = new Contact(_fname, _lname, _email, _phone, _birth);
                Contactlist.add(contactRecord);
                Collections.sort(Contactlist);
                populateList();
                break;
            case 2:
                int position = data.getIntExtra("position", -1);
                Contactlist.remove(position);
                Collections.sort(Contactlist);
                populateList();
                contactsListView.invalidate();
                break;
            case 3:
                int position2 = data.getIntExtra("position", -1);
                String fname = data.getStringExtra("firstname");
                String lname = data.getStringExtra("lastname");
                String email = data.getStringExtra("email");
                String birth = data.getStringExtra("birthday");
                String phone = data.getStringExtra("phoneNumber");
                Contactlist.get(position2).reset(fname, lname, email, phone, birth);
                Collections.sort(Contactlist);
                populateList();
                contactsListView.invalidate();
                break;
        }


    }

    private void populateList(){
        ArrayAdapter<Contact> adapter = new ContactListAdapter();
        contactsListView.setAdapter(adapter);
    }

    private class ContactListAdapter extends ArrayAdapter<Contact>{
        LayoutInflater inflater =  LayoutInflater.from(MainActivity.this);
        public ContactListAdapter(){
            super (MainActivity.this, R.layout.list_item, Contactlist);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            View view = inflater.inflate(R.layout.list_item,null);
            TextView displayname = (TextView) view.findViewById(R.id.displaceName);
            if(displayname != null){
                displayname.setText(Contactlist.get(position).get_name());
                //contactsListView.addView(displayname);
            }
            return view;
        }
    }

}
