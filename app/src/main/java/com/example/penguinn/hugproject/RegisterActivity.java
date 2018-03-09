package com.example.penguinn.hugproject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.util.Log;
import android.widget.Toast;
import android.app.Dialog;
import android.app.ProgressDialog;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by delaroy on 3/27/17.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final String TAG = "MainActivity";

    private ProgressDialog progressDialog;
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
    public String sFirstName,sLestName,sPhone,pFirstName,pLastName,pPhone,pEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

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
                isServicesOK();
                break;
            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    private void postDataToSQLite() {
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setTitle("Error! ");
        ad.setIcon(android.R.drawable.btn_star_big_on);
        ad.setPositiveButton("Close", null);

        if (!inputValidation.isInputEditTextFilled(textInputEditTextFirstName, textInputLayoutFirstName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextLastName, textInputLayoutLastName, getString(R.string.error_message_lname))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPhone, textInputLayoutPhone, getString(R.string.error_message_phone))) {
            return;
        }
        if (textInputEditTextPhone.length() != 10) {
            textInputEditTextPhone.setError("กรุณาเบอร์โทรให้ครบ 10 หลัก");
            textInputEditTextPhone.requestFocus();
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (textInputEditTextPassword.length() < 4) {
            textInputEditTextPassword.setError("กรอกรหัสผ่านอย่างน้อย 4 ตัว");
            textInputEditTextPassword.requestFocus();
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
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
        if (textInputEditTextParentPhone.length() != 10) {
            textInputEditTextParentPhone.setError("กรุณาเบอร์โทรให้ครบ 10 หลัก");
            textInputEditTextParentPhone.requestFocus();
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextParentEmail, textInputLayoutParentEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextParentEmail, textInputLayoutParentEmail, getString(R.string.error_message_email))) {
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

            Intent accountsIntent = new Intent(activity,MapsActivity.class);
            accountsIntent.putExtra("phone", textInputEditTextPhone.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);

        } else {
            // Snack Bar tแนo show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


        // Dialog

        String url = "http://172.27.106.219/HugProject/view/saveAddData.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("stu_first_name", textInputEditTextFirstName.getText().toString()));
        params.add(new BasicNameValuePair("stu_last_name", textInputEditTextLastName.getText().toString()));
        params.add(new BasicNameValuePair("stu_phone", textInputEditTextPhone.getText().toString()));
        params.add(new BasicNameValuePair("stu_password", textInputEditTextPassword.getText().toString()));

        params.add(new BasicNameValuePair("parent_first_name", textInputEditTextParentFirstName.getText().toString()));
        params.add(new BasicNameValuePair("parent_last_name", textInputEditTextParentLastName.getText().toString()));
        params.add(new BasicNameValuePair("parent_phone", textInputEditTextParentPhone.getText().toString()));
        params.add(new BasicNameValuePair("parent_email", textInputEditTextParentEmail.getText().toString()));


        /** Get result from Server (Return the JSON Code)
         * StatusID = ? [0=Failed,1=Complete]
         * Error	= ?	[On case error return custom error message]
         *
         * Eg Save Failed = {"StatusID":"0","Error":"Email Exists!"}
         * Eg Save Complete = {"StatusID":"1","Error":""}
         */

        String resultServer  = getHttpPost(url,params);

        /*** Default Value ***/
        String strStatusID = "0";
        String strError = "Unknow Status!";

        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strStatusID = c.getString("StatusID");
            strError = c.getString("Error");
            Log.d("Awesome Tag", c.toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Prepare Save Data
        if(strStatusID.equals("0"))
        {
            ad.setMessage(strError);
            ad.show();
        }
        else
        {
            Toast.makeText(RegisterActivity.this, "Save Data Successfully", Toast.LENGTH_SHORT).show();
            textInputEditTextFirstName.setText("");
            textInputEditTextLastName.setText("");
            textInputEditTextPhone.setText("");
            textInputEditTextPassword.setText("");

            textInputEditTextParentFirstName.setText("");
            textInputEditTextParentLastName.setText("");
            textInputEditTextParentPhone.setText("");
            textInputEditTextParentEmail.setText("");

        }
    }

        public String getHttpPost(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
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

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(RegisterActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(RegisterActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean checkEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

}