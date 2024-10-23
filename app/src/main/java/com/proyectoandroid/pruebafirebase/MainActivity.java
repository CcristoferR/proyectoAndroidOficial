package com.proyectoandroid.pruebafirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;  // Botón de registro
    private Button recoverPasswordButton;  // Botón para recuperar contraseña

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Encuentra los elementos de UI
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);  // Encuentra el botón de registro
        recoverPasswordButton = findViewById(R.id.recoverPasswordButton);  // Encuentra el botón para recuperar contraseña

        // Configura el listener para el botón de inicio de sesión
        loginButton.setOnClickListener(v -> signIn());

        // Configura el listener para el botón de registro
        registerButton.setOnClickListener(v -> {
            // Redirige a RegisterActivity cuando se presiona el botón
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Configura el listener para el botón de recuperar contraseña
        recoverPasswordButton.setOnClickListener(v -> recoverPassword());
    }

    private void signIn() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validaciones de entrada
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor ingresa un correo y una contraseña.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Muestra un mensaje de progreso
        Toast.makeText(MainActivity.this, "Iniciando sesión...", Toast.LENGTH_SHORT).show();

        // Inicia sesión con el correo electrónico y la contraseña
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Error al iniciar sesión: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Redirigir a la siguiente actividad
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Cierra esta actividad
        }
    }

    private void recoverPassword() {
        String email = emailEditText.getText().toString().trim();

        // Validar si el correo electrónico no está vacío
        if (email.isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor ingresa tu correo electrónico.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Solicitar el enlace de restablecimiento de contraseña
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Enlace de recuperación enviado a tu correo.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error al enviar el enlace: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
