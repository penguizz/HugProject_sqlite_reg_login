package com.example.penguinn.hugproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.logging.XMLFormatter;

/**
 * Created by delaroy on 3/27/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Student.db";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_STU_FIRSTNAME = "stu_first_name";
    private static final String COLUMN_USER_STU_LASTNAME = "stu_last_name";
    private static final String COLUMN_USER_STU_PHONE = "stu_phone";
    private static final String COLUMN_USER_PASSWORD = "stu_password";

    private static final String COLUMN_USER_PARENT_FIRSTNAME = "perant_first_name";
    private static final String COLUMN_USER_PARENT_LASTNAME = "perantlast_name";
    private static final String COLUMN_USER_PARENT_PHONE = "perant_phone";
    private static final String COLUMN_USER_PARENT_EMAIL = "perant_email";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_STU_FIRSTNAME + " TEXT,"
            + COLUMN_USER_STU_LASTNAME + " TEXT," + COLUMN_USER_STU_PHONE + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_USER_PARENT_FIRSTNAME + " TEXT,"
            + COLUMN_USER_PARENT_LASTNAME + " TEXT," + COLUMN_USER_PARENT_PHONE + " TEXT,"
            + COLUMN_USER_PARENT_EMAIL + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
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

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_USER_ID
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
                COLUMN_USER_ID
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
}
