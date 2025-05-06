package com.example.edulearn.SignInUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import com.example.edulearn.moodle.DatabaseHelper;
import com.example.edulearn.moodle.R;

public class SignupActivity extends AppCompatActivity {

    EditText username, password, re_password, email;
    Button signup, login;
    DatabaseHelper databaseHelper;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        re_password = findViewById(R.id.repassword);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.signin);

        databaseHelper = new DatabaseHelper(this);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser fUser = mAuth.getCurrentUser();
        String mail = email.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("users").document(fUser.getUid());

        Map<String, Object> userData = new HashMap<>();
        userData.put("email", mail);
        userRef.set(userData, SetOptions.merge());

        signup.setOnClickListener(view -> {
            String user = username.getText().toString();
            String Email = email.getText().toString();
            String pass = password.getText().toString();
            String repass = re_password.getText().toString();

            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                Toast.makeText(SignupActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
            else{
                if(pass.equals(repass)){

                    mAuth.createUserWithEmailAndPassword(Email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignupActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(SignupActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(SignupActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });


        login.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}