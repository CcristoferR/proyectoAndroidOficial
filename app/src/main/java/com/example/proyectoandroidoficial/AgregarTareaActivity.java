package com.example.proyectoandroidoficial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AgregarTareaActivity extends AppCompatActivity {

    // Variables para los elementos del layout
    private EditText editarTitulo;
    private EditText editarDescripcion;
    private EditText editarFechaLimite;
    private Spinner spinnerPrioridad;
    private Spinner spinnerEtiquetas;
    private Button botonGuardar;
    private Button botonCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_tarea);

        // Inicializar las variables
        editarTitulo = findViewById(R.id.editarTitulo);
        editarDescripcion = findViewById(R.id.editarDescripcion);
        editarFechaLimite = findViewById(R.id.editarFechaLimite);
        spinnerPrioridad = findViewById(R.id.spinnerPrioridad);
        spinnerEtiquetas = findViewById(R.id.spinnerEtiquetas);
        botonGuardar = findViewById(R.id.botonGuardar);
        botonCancelar = findViewById(R.id.botonCancelar);

        // Ajustar los insets de la vista
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
