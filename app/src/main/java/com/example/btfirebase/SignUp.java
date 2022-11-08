package com.example.btfirebase;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private EditText edtEmail,edtPass,edtRePass,edtName;
    private Button btnSignup,btnLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth=FirebaseAuth.getInstance();
        edtEmail=findViewById(R.id.edt_email);
        edtPass=findViewById(R.id.edt_pass);
        edtRePass=findViewById(R.id.edt_repass);
        btnSignup=findViewById(R.id.btn_signup);
        progressDialog=new ProgressDialog(this);
        btnLogin=findViewById(R.id.btn_login);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void Register(){
        String email=edtEmail.getText().toString().trim();
        String password1=edtPass.getText().toString().trim();
        String password2=edtRePass.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            edtEmail.setError("Nhập email");
            return;
        }
        else if(TextUtils.isEmpty(password1)){
            edtPass.setError("Nhập password");
            return;
        }
        else if(TextUtils.isEmpty(password2)){
            edtRePass.setError("Xác nhận password");
            return;
        }
        else if(!password1.equals(password2)){
            edtRePass.setError("Different password");
            return;
        }
        else if(password1.length()<4){
            edtPass.setError("Độ dài phải lớn hơn 6");
            return;
        }
        else if(!isVallidEmail(email)){
            edtEmail.setError("invalid email");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUp.this, com.example.btfirebase.Login.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(SignUp.this,"Đăng nhập thất bai!",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    private Boolean isVallidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
