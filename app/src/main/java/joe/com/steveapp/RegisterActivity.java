package joe.com.steveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import android.os.Bundle;
import android.renderscript.Int4;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity
{
    private Button CreateAccountButton,getCodeBtn;
    private EditText InputName, InputPhoneNumber, InputPassword,edtCode,email;
    private ProgressDialog loadingBar;
    String codeSent,phone,name, password,userEmail;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        CreateAccountButton = findViewById(R.id.register_btn);
        InputName =  findViewById(R.id.register_username_input);
        InputPassword =  findViewById(R.id.register_password_input);
        InputPhoneNumber =  findViewById(R.id.register_phone_number_input);
        getCodeBtn = findViewById(R.id.get_vCode_btn);
        edtCode = findViewById(R.id.edtCode);
        email = findViewById(R.id.register_email_input);

        loadingBar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        CreateAccountButton.setVisibility(View.GONE);
        InputPassword.setVisibility(View.GONE);
        InputName.setVisibility(View.GONE);
        email.setVisibility(View.GONE);

        getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendVerificationCode();
            }
        });
        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CreateAccount();
            }
        });
    }

    private void getEmailDetails(){
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                InputPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Email registered ",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(RegisterActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void sendVerificationCode(){

        Toast.makeText(RegisterActivity.this,"Wait For Verification",Toast.LENGTH_LONG).show();

        String phoneNumber =  InputPhoneNumber.getText().toString();

        if(phoneNumber.charAt(0) == '0'){
            phone  = phoneNumber.replace("0","+27");
        }
        else {
            phone = "+27" + phoneNumber;
        }


        if(phoneNumber.isEmpty()){
            InputPhoneNumber.setError("Phone Number is required");
            InputPhoneNumber.requestFocus();
            return;
        }

        if(phoneNumber.length() < 10 ){
            InputPhoneNumber.setError("Enter Valid Phone Number");
            InputPhoneNumber.requestFocus();
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
            edtCode.setText(code);
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
        String code = edtCode.getText().toString();

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
                            Toast.makeText(RegisterActivity.this, "Number Authenticated continue with registration", Toast.LENGTH_SHORT).show();

                            CreateAccountButton.setVisibility(View.VISIBLE);
                            InputPassword.setVisibility(View.VISIBLE);
                            InputName.setVisibility(View.VISIBLE);
                            edtCode.setVisibility(View.GONE);
                            getCodeBtn.setVisibility(View.GONE);
                            InputPhoneNumber.setEnabled(false);
                            email.setVisibility(View.VISIBLE);
                            InputPhoneNumber.setText(InputPhoneNumber.getText().toString());

                        } else {

                            Toast.makeText(RegisterActivity.this, "Failed to verify your number", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void CreateAccount()

    {
         name = InputName.getText().toString();
         password = InputPassword.getText().toString();
         userEmail = email.getText().toString();



        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }

        else
        if (TextUtils.isEmpty(userEmail))
        {
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validateEmailAddress(name, InputPhoneNumber.getText().toString(), password,userEmail);
        }
    }




    private void validateEmailAddress(final String name, final String phone, final String password, final String userEmail)
    {
        getEmailDetails();
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                   @Override
                                                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                       if (!(dataSnapshot.child("Users").child(InputPhoneNumber.getText().toString()).exists())) {
                                                           HashMap<String, Object> userdataMap = new HashMap<>();
                                                           userdataMap.put("phone", InputPhoneNumber.getText().toString());
                                                           userdataMap.put("password", password);
                                                           userdataMap.put("name", name);
                                                           userdataMap.put("email ", userEmail);

                                                           RootRef.child("Users").child(InputPhoneNumber.getText().toString()).updateChildren(userdataMap)
                                                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                       @Override
                                                                       public void onComplete(@NonNull Task<Void> task) {
                                                                           if (task.isSuccessful()) {
                                                                               Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                                                               loadingBar.dismiss();

                                                                               Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                                               intent.putExtra("contact",InputPhoneNumber.getText().toString());
                                                                               startActivity(intent);

                                                                           } else {
                                                                               loadingBar.dismiss();
                                                                               Toast.makeText(RegisterActivity.this, "Network Error: Make sure you are connected to the internet...", Toast.LENGTH_SHORT).show();
                                                                           }
                                                                       }
                                                                   });
                                                       } else {
                                                           Toast.makeText(RegisterActivity.this, "This " + InputPhoneNumber.getText().toString() + " already exists.", Toast.LENGTH_SHORT).show();
                                                           loadingBar.dismiss();
                                                           Toast.makeText(RegisterActivity.this, "Please try again using another Email address.", Toast.LENGTH_SHORT).show();

                                                           Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                           startActivity(intent);
                                                       }
                                                   }

            //validating phone number


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
