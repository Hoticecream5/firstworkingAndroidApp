package joe.com.steveapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyNumberActivity extends AppCompatActivity {

    String codeSent,phone ;
    private EditText edtPhone, editTextCode;
    FirebaseAuth mAuth;
    private Button  getCodeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_number);

        mAuth = FirebaseAuth.getInstance();
        edtPhone = findViewById(R.id.phone_number_input);
        editTextCode = findViewById(R.id.edtCode);

        getCodeBtn = findViewById(R.id.get_vCode_btn);

        getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });

    }


    private void sendVerificationCode(){


        String phoneNumber =  edtPhone.getText().toString();

        if(phoneNumber.charAt(0) == 0){
             phone  = phoneNumber.replace("0","+27");
        }



        if(phoneNumber.isEmpty()){
            edtPhone.setError("Phone Number is required");
            edtPhone.requestFocus();
            return;
        }

        if(phoneNumber.length() < 10 ){
            edtPhone.setError("Enter Valid Phone Number");
            edtPhone.requestFocus();
            return;
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            editTextCode.setText(code);
            verifySignInCode();

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
        }
    };

    private void verifySignInCode(){
        String code = editTextCode.getText().toString();

        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
            signInWithPhoneAuthCredential(credential);
        }catch (Exception e){
            Toast toast = Toast.makeText(this, "wrong Verification Code", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(VerifyNumberActivity.this, "Number Authenticated continue with registration", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(VerifyNumberActivity.this, RegisterActivity.class);
                            startActivity(intent);
                        } else {

                            Toast.makeText(VerifyNumberActivity.this, "Failed to verify your number", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
