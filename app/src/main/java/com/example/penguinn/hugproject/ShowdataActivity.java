package com.example.penguinn.hugproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowdataActivity extends AppCompatActivity {
    //    TextView profile,stu_name,stu_lastname,stu_phone,parent_name,parent_lastname,parent_phone,parent_email;
    private EditText edit_stu_name, edit_stu_lastname, edit_stu_pass, edit_parent_name,
            edit_parent_lastname, edit_parent_phone, edit_parent_email;
    private TextView edit_stu_phone;
    private Button btn_edit, btn_save;
    private String sFirstName, sLestName, sPhone, pFirstName, pLastName, pPhone, pEmail;
    private RegisterActivity registerActivity;
    private ListView listView;
    private List userList;
    private DatabaseHelper databaseHelper;
    private User user;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);

        databaseHelper = new DatabaseHelper(ShowdataActivity.this);
        user = new User();
        SQLiteDatabase db;
        db = databaseHelper.getWritableDatabase();

        Bundle args = getIntent().getExtras();
        user.setPhone(args.getString("phone"));

        edit_stu_name = (EditText) findViewById(R.id.edit_stu_name);
        edit_stu_lastname = (EditText) findViewById(R.id.edit_stu_lastname);
        edit_stu_phone = (TextView) findViewById(R.id.edit_stu_phone);
        edit_stu_pass = (EditText) findViewById(R.id.edit_stu_pass);
        edit_parent_name = (EditText) findViewById(R.id.edit_parent_name);
        edit_parent_lastname = (EditText) findViewById(R.id.edit_parent_lastname);
        edit_parent_phone = (EditText) findViewById(R.id.edit_parent_phone);
        edit_parent_email = (EditText) findViewById(R.id.edit_parent_email);
        mWebView = (WebView) findViewById(R.id.web_view);
        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_save = (Button) findViewById(R.id.btn_save);

        String TABLE_NAME = "user";
        String COL_PHONE = "stu_phone";

        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
                + " WHERE " + COL_PHONE + "=" + user.getPhone(),null);

        Log.i("Data Count", String.valueOf(mCursor.getCount()));

        mCursor.moveToFirst();


        String st_name = mCursor.getString(mCursor.getColumnIndex("stu_first_name"));
        String st_lastname = mCursor.getString(mCursor.getColumnIndex("stu_last_name"));
        String st_phone = mCursor.getString(mCursor.getColumnIndex("stu_phone"));
        String st_password = mCursor.getString(mCursor.getColumnIndex("stu_password"));
        String pa_name = mCursor.getString(mCursor.getColumnIndex("perent_first_name"));
        String pa_lastname = mCursor.getString(mCursor.getColumnIndex("perent_last_name"));
        String pa_phone = mCursor.getString(mCursor.getColumnIndex("perent_phone"));
        String pa_email = mCursor.getString(mCursor.getColumnIndex("perent_email"));

        edit_stu_name.setText("" + st_name);
        edit_stu_lastname.setText("" + st_lastname);
        edit_stu_phone.setText("0" + st_phone);
        edit_stu_pass.setText("" + st_password);
        edit_parent_name.setText("" + pa_name);
        edit_parent_lastname.setText("" + pa_lastname);
        edit_parent_phone.setText("" + pa_phone);
        edit_parent_email.setText("" + pa_email);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSqlite();

                Intent intent = new Intent(ShowdataActivity.this, DetailStudent.class);
                intent.putExtra("phone", user.getPhone());
                startActivity(intent);
            }

            public void updateSqlite(){

                user.setStu_fname(edit_stu_name.getText().toString().trim());
                user.setStu_lname(edit_stu_lastname.getText().toString().trim());
                user.getPhone();
                user.setPassword(edit_stu_pass.getText().toString().trim());
                user.setParent_fname(edit_parent_name.getText().toString().trim());
                user.setParent_lname(edit_parent_lastname.getText().toString().trim());
                user.setParent_phone(edit_parent_phone.getText().toString().trim());
                user.setParent_email(edit_parent_email.getText().toString().trim());

                databaseHelper.updateData(user);

            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

}

