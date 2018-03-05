package com.example.penguinn.hugproject;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.penguinn.hugproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;


public class FirstActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "PhoneLogin";
    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private DatabaseReference databaseReference;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    ImageView imageLogo;
    EditText editTextphone_number,editTextOTP;
    Button buttonPhoneVerify,buttonOTPVerify,buttonback_to_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        editTextphone_number = (EditText) findViewById(R.id.editTextphone_number);
        buttonback_to_login = (Button) findViewById(R.id.buttonback_to_login);
        imageLogo = (ImageView)findViewById(R.id.imageView2);
        editTextOTP = (EditText) findViewById(R.id.editTextOTP);
        buttonOTPVerify = (Button)findViewById(R.id.buttonOTPVerify);
        mAuth = FirebaseAuth.getInstance();
        buttonPhoneVerify = (Button) findViewById(R.id.buttonPhoneVerify);
        buttonback_to_login = (Button) findViewById(R.id.buttonback_to_login);

        buttonPhoneVerify.setOnClickListener(this);
        buttonOTPVerify.setOnClickListener(this);
        buttonback_to_login.setOnClickListener(this);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // Log.d(TAG, "onVerificationCompleted:" + credential);
                mVerificationInProgress = false;
                Toast.makeText(FirstActivity.this,"Verification Complete",Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // Log.w(TAG, "onVerificationFailed", e);
                Toast.makeText(FirstActivity.this,"การตรวจสอบไม่สำเร็จ",Toast.LENGTH_SHORT).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Toast.makeText(FirstActivity.this,"หมายเลขโทรศัพท์ไม่ถูกต้อง กรุณาตรวจสอบ",Toast.LENGTH_SHORT).show();
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                }

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(FirstActivity.this,"กำลังส่งรหัสยืนยันไปยังหมายเลขโทรศัพท์ที่ระบุทาง SMS",Toast.LENGTH_SHORT).show();
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                editTextphone_number.setVisibility(View.GONE);
                buttonPhoneVerify.setVisibility(View.GONE);
                buttonback_to_login.setVisibility(View.GONE);
                imageLogo.setVisibility(View.GONE);
                editTextOTP.setVisibility(View.VISIBLE);
                buttonOTPVerify.setVisibility(View.VISIBLE);
                // ...

            }
        };
    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Log.d(TAG, "signInWithCredential:success");
                            startActivity(new Intent(FirstActivity.this,RegisterActivity.class));
                            Toast.makeText(FirstActivity.this,"ส่ง OTP",Toast.LENGTH_SHORT).show();
                            // ...
                        } else {
                            // Log.w (TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(FirstActivity.this,"ไม่สามารถส่งรหัส OTP ได้",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == buttonPhoneVerify){
            phoneNumberAuth();
        }

        if(v == buttonOTPVerify) {
            sentOTP();
        }
        if(v == buttonback_to_login){
            Intent intentLogin = new Intent(FirstActivity.this, LoginActivity.class);
            startActivity(intentLogin);
        }
    }

    private void phoneNumberAuth() {
        String phoneNumber = editTextphone_number.getText().toString().trim();
        if(phoneNumber.isEmpty()){
            editTextphone_number.setError("กรุณากรอกเบอร์โทรศัพท์");
            editTextphone_number.requestFocus();
            return;
        }
        if(phoneNumber.length() <10){
            editTextphone_number.setError("กรุณาเบอร์โทรให้ครบ 10 หลัก");
            editTextphone_number.requestFocus();
            return;
        }
        AlertDialog.Builder builder =
                new AlertDialog.Builder(FirstActivity.this);
        builder.setMessage("ระบบกำลังจะส่งรหัสผ่านไปยังหมายเลขโทรศัพท์");
        builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        editTextphone_number.getText().toString(),
                        60,
                        java.util.concurrent.TimeUnit.SECONDS,

                        FirstActivity.this,
                        mCallbacks);
            }
        });
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dialog.dismiss();
            }
        });
        builder.show();

    }

    private void sentOTP(){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, editTextOTP.getText().toString());
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);
    }
}