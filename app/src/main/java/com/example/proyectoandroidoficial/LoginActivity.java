package com.example.proyectoandroidoficial;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referencias a los elementos del layout
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Acción para el botón de registro
        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(intent);
        });

        // Aquí podrías agregar la lógica para el botón de login
    }
}
