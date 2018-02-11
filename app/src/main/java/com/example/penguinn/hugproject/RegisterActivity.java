package com.example.penguinn.hugproject;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

/**
 * Created by delaroy on 3/27/17.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutLastName;
    private TextInputLayout textInputLayoutPhone;
    //ผู้ปกครอง
    private TextInputLayout textInputLayoutParentFirstName;
    private TextInputLayout textInputLayoutParentLastName;
    private TextInputLayout textInputLayoutParentPhone;
    private TextInputLayout textInputLayoutParentEmail;

    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextFirstName;
    private TextInputEditText textInputEditTextLastName;
    private TextInputEditText textInputEditTextPhone;
    //ผู้ปกครอง
    private TextInputEditText textInputEditTextParentFirstName;
    private TextInputEditText textInputEditTextParentLastName;
    private TextInputEditText textInputEditTextParentPhone;
    private TextInputEditText textInputEditTextParentEmail;

    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutFirstName = (TextInputLayout) findViewById(R.id.textInputLayoutFirstName);
        textInputLayoutLastName = (TextInputLayout) findViewById(R.id.textInputLayoutLastName);
        textInputLayoutPhone = (TextInputLayout) findViewById(R.id.textInputLayoutPhone);

        textInputLayoutParentFirstName = (TextInputLayout) findViewById(R.id.textInputLayoutParentFirstName);
        textInputLayoutParentLastName = (TextInputLayout) findViewById(R.id.textInputLayoutParentLastName);
        textInputLayoutParentPhone = (TextInputLayout) findViewById(R.id.textInputLayoutParentPhone);
        textInputLayoutParentEmail = (TextInputLayout) findViewById(R.id.textInputLayoutParentEmail);

        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextFirstName = (TextInputEditText) findViewById(R.id.textInputEditTextFirstName);
        textInputEditTextLastName = (TextInputEditText) findViewById(R.id.textInputEditTextLastName);
        textInputEditTextPhone = (TextInputEditText) findViewById(R.id.textInputEditTextPhone);

        textInputEditTextParentFirstName = (TextInputEditText) findViewById(R.id.textInputEditTextParentFirstName);
        textInputEditTextParentLastName = (TextInputEditText) findViewById(R.id.textInputEditTextParentLastName);
        textInputEditTextParentPhone = (TextInputEditText) findViewById(R.id.textInputEditTextParentPhone);
        textInputEditTextParentEmail = (TextInputEditText) findViewById(R.id.textInputEditTextParentEmail);

        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);
    }

    private void initListeners(){
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }

    private void initObjects(){
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    private void postDataToSQLite(){
        if (!inputValidation.isInputEditTextFilled(textInputEditTextFirstName, textInputLayoutFirstName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextLastName, textInputLayoutLastName, getString(R.string.error_message_lname))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPhone, textInputLayoutPhone, getString(R.string.error_message_phone))) {
            return;
        }

        ////////////////
        if (!inputValidation.isInputEditTextFilled(textInputEditTextParentFirstName, textInputLayoutParentFirstName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextParentLastName, textInputLayoutParentLastName, getString(R.string.error_message_lname))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextParentPhone, textInputLayoutParentPhone, getString(R.string.error_message_phone))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextParentEmail, textInputLayoutParentEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextParentEmail, textInputLayoutParentEmail, getString(R.string.error_message_email))) {
            return;
        }
        /////////

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextPhone.getText().toString().trim())) {

            user.setStu_fname(textInputEditTextFirstName.getText().toString().trim());
            user.setStu_lname(textInputEditTextLastName.getText().toString().trim());
            user.setPhone(textInputEditTextPhone.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            user.setParent_fname(textInputEditTextParentFirstName.getText().toString().trim());
            user.setParent_lname(textInputEditTextParentLastName.getText().toString().trim());
            user.setParent_phone(textInputEditTextParentPhone.getText().toString().trim());
            user.setParent_email(textInputEditTextParentEmail.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar tแนo show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    private void emptyInputEditText(){
        textInputEditTextFirstName.setText(null);
        textInputEditTextLastName.setText(null);
        textInputEditTextPhone.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);

        textInputEditTextParentFirstName.setText(null);
        textInputEditTextParentLastName.setText(null);
        textInputEditTextParentPhone.setText(null);
        textInputEditTextParentEmail.setText(null);
    }

}
