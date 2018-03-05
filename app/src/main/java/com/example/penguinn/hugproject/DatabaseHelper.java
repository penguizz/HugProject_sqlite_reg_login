package com.example.penguinn.hugproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.XMLFormatter;

/**
 * Created by delaroy on 3/27/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Student7.db";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_STU_FIRSTNAME = "stu_first_name";
    private static final String COLUMN_USER_STU_LASTNAME = "stu_last_name";
    private static final String COLUMN_USER_STU_PHONE = "stu_phone";
    private static final String COLUMN_USER_PASSWORD = "stu_password";

    private static final String COLUMN_USER_PARENT_FIRSTNAME = "perent_first_name";
    private static final String COLUMN_USER_PARENT_LASTNAME = "perent_last_name";
    private static final String COLUMN_USER_PARENT_PHONE = "perent_phone";
    private static final String COLUMN_USER_PARENT_EMAIL = "perent_email";

    private static final String COLUMN_USER_LATITUDE = "stu_latitude";
    private static final String COLUMN_USER_LONGITUDE = "stu_longitude";
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    private String[] allColumns = {
            COLUMN_USER_PARENT_PHONE,
            COLUMN_USER_STU_FIRSTNAME,
            COLUMN_USER_STU_LASTNAME,
            COLUMN_USER_PASSWORD,
            COLUMN_USER_PARENT_FIRSTNAME,
            COLUMN_USER_PARENT_LASTNAME,
            COLUMN_USER_PARENT_PHONE,
            COLUMN_USER_PARENT_EMAIL
    };

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_STU_PHONE + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_STU_FIRSTNAME + " TEXT,"
            + COLUMN_USER_STU_LASTNAME + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_USER_PARENT_FIRSTNAME + " TEXT,"
            + COLUMN_USER_PARENT_LASTNAME + " TEXT," + COLUMN_USER_PARENT_PHONE + " TEXT,"
            + COLUMN_USER_PARENT_EMAIL + " TEXT," + COLUMN_USER_LATITUDE + " REAL,"
            + COLUMN_USER_LONGITUDE + " REAL" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
//        db.execSQL("INSERT INTO " + TABLE_USER + " (" + COLUMN_USER_STU_PHONE
//                + ", " + COLUMN_USER_STU_FIRSTNAME
//                + ", " + COLUMN_USER_STU_LASTNAME
//                + ", " + COLUMN_USER_PASSWORD
//                + ", " + COLUMN_USER_PARENT_FIRSTNAME
//                + ", " + COLUMN_USER_PARENT_LASTNAME
//                + "," + COLUMN_USER_PARENT_PHONE
//                + "," + COLUMN_USER_PARENT_EMAIL
//                + "," + COLUMN_USER_LATITUDE
//                + "," + COLUMN_USER_LONGITUDE
//                + ") VALUES ('0989307068', 'warawut', 'namhard', '140540559', 'wara', 'nam', '0999999', 'wara@gmail.com', '111 ', '222');");
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_STU_FIRSTNAME, user.getStu_fname());
        values.put(COLUMN_USER_STU_LASTNAME, user.getStu_lname());
        values.put(COLUMN_USER_STU_PHONE, user.getPhone());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        values.put(COLUMN_USER_PARENT_FIRSTNAME, user.getParent_fname());
        values.put(COLUMN_USER_PARENT_LASTNAME, user.getParent_lname());
        values.put(COLUMN_USER_PARENT_PHONE, user.getParent_phone());
        values.put(COLUMN_USER_PARENT_EMAIL, user.getParent_email());

        values.put(COLUMN_USER_LATITUDE, user.getLatitude());
        values.put(COLUMN_USER_LONGITUDE, user.getLongitude());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void updateData(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_STU_FIRSTNAME, user.getStu_fname());
        values.put(COLUMN_USER_STU_LASTNAME, user.getStu_lname());
        values.put(COLUMN_USER_STU_PHONE, user.getPhone());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        values.put(COLUMN_USER_PARENT_FIRSTNAME, user.getParent_fname());
        values.put(COLUMN_USER_PARENT_LASTNAME, user.getParent_lname());
        values.put(COLUMN_USER_PARENT_PHONE, user.getParent_phone());
        values.put(COLUMN_USER_PARENT_EMAIL, user.getParent_email());

        //   values.put(COLUMN_USER_LATITUDE, user.getLatitude());
        //    values.put(COLUMN_USER_LONGITUDE, user.getLongitude());

        db.update(TABLE_USER, values, COLUMN_USER_STU_PHONE + "=" + user.getPhone(),null);
    }

    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_USER_STU_PHONE
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_STU_PHONE + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_USER_STU_PHONE
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_STU_PHONE + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_USER,null);
        return res;
    }

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<User>();
        Cursor cursor = database.query(TABLE_USER,allColumns,null,null,null,null,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            User comment = cursorToUser(cursor);
            userList.add(comment);
            cursor.moveToNext();
        }
        cursor.close();
        return userList;
    }

    private User cursorToUser(Cursor cursor){
        User user = new User();
        user.setPhone(cursor.getString(0));
        user.setStu_fname(cursor.getString(1));
        user.setStu_lname(cursor.getString(2));
        user.setPassword(cursor.getString(3));
        user.setParent_fname(cursor.getString(4));
        user.setParent_lname(cursor.getString(5));
        user.setParent_phone(cursor.getString(6));
        user.setParent_email(cursor.getString(7));

        return user;
    }
}