package com.example.penguinn.hugproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class DetailStudent extends AppCompatActivity {

    private TextView txt_st_name,txt_st_lastname,txt_st_phone,txt_pa_name,txt_pa_lastname,txt_pa_phone,txt_pa_email;
    private DatabaseHelper databaseHelper;
    private User user;
    private Button btn_edit,btn_back;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_student);

        databaseHelper = new DatabaseHelper(DetailStudent.this);
        SQLiteDatabase db;
        db = databaseHelper.getWritableDatabase();
        user = new User();

        Bundle args = getIntent().getExtras();
        user.setPhone(args.getString("phone"));

        txt_st_name = (TextView) findViewById(R.id.txt_st_name);
        txt_st_lastname = (TextView) findViewById(R.id.txt_st_lastname);
        txt_st_phone = (TextView) findViewById(R.id.txt_st_phone);
        txt_pa_name = (TextView) findViewById(R.id.txt_pa_name);
        txt_pa_lastname = (TextView) findViewById(R.id.txt_pa_lastname);
        txt_pa_phone = (TextView) findViewById(R.id.txt_pa_phone);
        txt_pa_email = (TextView) findViewById(R.id.txt_pa_email);
        btn_edit = (Button)findViewById(R.id.btn_edit);
        btn_back = (Button)findViewById(R.id.btn_back);
        mWebView = (WebView) findViewById(R.id.web_view);

        String TABLE_NAME = "user";
        String COL_PHONE = "stu_phone";

        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
                + " WHERE " + COL_PHONE + "=" + user.getPhone(),null);

        Log.i("Data Count", String.valueOf(mCursor.getCount()));

        mCursor.moveToFirst();

        String st_name = mCursor.getString(mCursor.getColumnIndex("stu_first_name"));
        String st_lastname = mCursor.getString(mCursor.getColumnIndex("stu_last_name"));
        String st_phone = mCursor.getString(mCursor.getColumnIndex("stu_phone"));
        String pa_name = mCursor.getString(mCursor.getColumnIndex("perent_first_name"));
        String pa_lastname = mCursor.getString(mCursor.getColumnIndex("perent_last_name"));
        String pa_phone = mCursor.getString(mCursor.getColumnIndex("perent_phone"));
        String pa_email = mCursor.getString(mCursor.getColumnIndex("perent_email"));

        txt_st_name.setText("" + st_name);
        txt_st_lastname.setText("" + st_lastname);
        txt_st_phone.setText("0" + st_phone);
        txt_pa_name.setText("" + pa_name);
        txt_pa_lastname.setText("" + pa_lastname);
        txt_pa_phone.setText("" + pa_phone);
        txt_pa_email.setText("" + pa_email);
        //    Log.i("GetData", data);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailStudent.this, ShowdataActivity.class);
                intent.putExtra("phone", user.getPhone());
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailStudent.this, MapsActivity.class);
                intent.putExtra("phone", user.getPhone());
                startActivity(intent);
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