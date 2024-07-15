package com.doron.watchvault.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.doron.watchvault.MainActivity;
import com.doron.watchvault.R;
import com.doron.watchvault.network.AuthApi;
import com.doron.watchvault.network.AuthApiService;
import com.doron.watchvault.network.models.AuthModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    EditText fullname_et, email_sign_et, pass_sign_et, pass_conf_et;
    AppCompatButton signup_btn;
    ImageView back_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullname_et = findViewById(R.id.fullname_et);
        email_sign_et = findViewById(R.id.email_sign_et);
        pass_sign_et = findViewById(R.id.pass_sign_et);
        pass_conf_et = findViewById(R.id.pass_conf_et);

        signup_btn = findViewById(R.id.signup_btn);
        back_btn = findViewById(R.id.back_img);

        signup_btn.setOnClickListener(v -> {
            String fullname = fullname_et.getText().toString();
            String email = email_sign_et.getText().toString();
            String pass = pass_sign_et.getText().toString();
            String pass_conf = pass_conf_et.getText().toString();

            if (fullname.isEmpty() || email.isEmpty() || pass.isEmpty()
                    || pass_conf.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG)
                        .show();
            }

            if (!pass.equals(pass_conf)) {
                Toast.makeText(this, "Passwords do not match",
                        Toast.LENGTH_LONG).show();
            }

            if (pass.length() < 8 || pass.length() > 20) {
                Toast.makeText(this, "Password must be at least 8 characters " +
                        "long and no more than 20", Toast.LENGTH_LONG).show();
            }

            if (pass.contains(" ") || pass_conf.contains(" ")) {
                Toast.makeText(this, "Password cannot contain spaces",
                        Toast.LENGTH_LONG).show();
            }

            if (email.contains(" ")) {
                Toast.makeText(this, "Email cannot contain spaces",
                        Toast.LENGTH_LONG).show();
            }

        });

        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void saveData(String name, String email, String password){
        AuthApiService authApiService = AuthApi.authApiGetter();

        AuthModel authModel = new AuthModel();
        authModel.setUname(name);
        authModel.setUemail(email);
        authModel.setUpassword(password);

        Call<AuthModel> call = authApiService.saveUser(authModel);

        call.enqueue(new Callback<AuthModel>() {
            @Override
            public void onResponse(@NonNull Call<AuthModel> call, @NonNull Response<AuthModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "User saved successfully",
                            Toast.LENGTH_SHORT).show();

                    // Get the user id
                    assert response.body() != null;
                    Long userId = response.body().getUid();

                    SharedPreferences sharedPreferences = getSharedPreferences("Auth", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putLong("userId", userId);
                    editor.apply();

                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(SignupActivity.this, "Failed to save user",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthModel> call, Throwable throwable) {
                String msg = throwable.getMessage();
                Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });

    }
}
