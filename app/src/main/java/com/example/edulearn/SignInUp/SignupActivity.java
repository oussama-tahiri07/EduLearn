package com.example.edulearn.SignInUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.edulearn.R;
import com.example.edulearn.SupabaseHelper;

import io.github.jan.supabase.gotrue.providers.builtin.Email;

public class SignupActivity extends AppCompatActivity {

    EditText username, password, re_password, email;
    Button signup, login;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_signup);

        /*username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        re_password = findViewById(R.id.repassword);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.signin);

        signup.setOnClickListener(view -> {
            String user = username.getText().toString();
            String Email = email.getText().toString();
            String pass = password.getText().toString();
            String repass = re_password.getText().toString();

            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                Toast.makeText(SignupActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
            else {
                if(pass.equals(repass)) {
                    // Sign up with Supabase
                    SupabaseHelper.getClient().getGoTrue().signUpWith(Email.INSTANCE, new Email(Email, pass), result -> {
                        if (result instanceof Email.Result.Success) {
                            Toast.makeText(SignupActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignupActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(SignupActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });*/
    }
}