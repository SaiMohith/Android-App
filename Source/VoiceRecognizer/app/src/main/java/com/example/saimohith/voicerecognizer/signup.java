package com.example.saimohith.voicerecognizer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by saimohith on 2/13/17.
 */

public class signup extends Activity implements View.OnClickListener{

    private Button Register;

    private EditText Username;
    private EditText email;
    private EditText Mobile;
    private EditText Password;
    private EditText password;

    private Button login;


    private ProgressDialog PD;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        firebaseAuth = FirebaseAuth.getInstance();
        PD = new ProgressDialog(this);


        Register = (Button) findViewById(R.id.Register);

        Username = (EditText)findViewById(R.id.RUsername);
        email = (EditText)findViewById(R.id.REmail);
        Mobile = (EditText)findViewById(R.id.RPhone);
        password = (EditText)findViewById(R.id.RPassword);
        Password = (EditText)findViewById(R.id.RPassword2);

        login = (Button)findViewById(R.id.login);

        Register.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    private void regUser(){
        String uname = Username.getText().toString().trim();
        String mail = email.getText().toString().trim();
        String pass = Password.getText().toString().trim();
        String num = Mobile.getText().toString().trim();
        String pass1 = password.getText().toString().trim();

        if (!pass.equals(pass1))
        {
            Toast.makeText(signup.this, "Password Doesn't Match!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(uname)){
            //Username is empty
            Toast.makeText(this,"Please enter a Username", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(mail)){
            //email is empty
            Toast.makeText(this, "Please Enter the Email id", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pass1)){
            //Username is empty
            Toast.makeText(this,"Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            //pass is empty
            Toast.makeText(this, "Please retype the Password", Toast.LENGTH_SHORT).show();
            return;
        }


        if(TextUtils.isEmpty(num)){
            //Mobile is empty
            Toast.makeText(this, "Please Enter the Mobile Number", Toast.LENGTH_SHORT).show();
            return;
        }

        PD.setMessage("Registering User..");
        PD.show();

        firebaseAuth.createUserWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            Toast.makeText(signup.this, "Registration Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                        PD.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View p){

        if(p == Register){
            regUser();
        }

        if(p == login){

            //login
            Intent k = new Intent(signup.this, Login.class);
            startActivity(k);
        }

    }
}
