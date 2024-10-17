package com.example.proyectoandroidoficial;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private Button buttonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Referencia al botón "Volver"
        buttonVolver = findViewById(R.id.buttonVolver);

        // Acción para el botón "Volver"
        buttonVolver.setOnClickListener(v -> {
            // Finaliza la actividad actual y vuelve al LoginActivity
            finish();
        });
    }
}
