package com.example.proyectoandroidoficial;

import android.content.Intent;
import android.content.SharedPreferences;
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

        // Acción para el botón de inicio de sesión
        buttonLogin.setOnClickListener(v -> {
            // Validar las credenciales (esto debe incluir tu lógica de autenticación)
            // Suponiendo que la validación fue exitosa:
            SharedPreferences preferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
            preferences.edit().putBoolean("isLoggedIn", true).apply();

            // Inicia la actividad principal
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Opcional: cerrar esta actividad
        });
    }
}
