package com.doron.watchvault.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.doron.watchvault.MainActivity;
import com.doron.watchvault.R;
import com.doron.watchvault.network.AuthApi;
import com.doron.watchvault.network.AuthApiService;
import com.doron.watchvault.network.models.AuthModel;
import com.doron.watchvault.network.models.LoginResponseModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText email_et, pass_et;
    AppCompatButton login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView signup_tv = findViewById(R.id.signup_tv);

        email_et = findViewById(R.id.email_et);
        pass_et = findViewById(R.id.pass_et);

        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(v -> {
            String email = String.valueOf(email_et.getText());
            String pass = pass_et.getText().toString();

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                authentication(email, pass);
            }
        });

        signup_tv.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private void authentication(String email, String pass) {
        AuthApiService authApiService = AuthApi.authApiGetter();

        AuthModel authModel = new AuthModel();
        authModel.setUemail(email);
        authModel.setUpassword(pass);

        Call<LoginResponseModel> call = authApiService.loginUser(authModel);

        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                if (response.isSuccessful() && Objects.requireNonNull(response.body()).isFlag()) {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    // Get the user id
                    Long userId = response.body().getId();

                    SharedPreferences sharedPreferences = getSharedPreferences("Auth", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putLong("userId", userId);
                    editor.apply();

                    LoginActivity.this.finish();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable throwable) {
                String errorMessage = throwable.getMessage();
                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}
