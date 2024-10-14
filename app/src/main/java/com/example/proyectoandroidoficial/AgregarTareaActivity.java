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
    private Button botonGuardar;

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
        botonGuardar = findViewById(R.id.botonGuardar);

        // Configurar el listener para el botón "Guardar"
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para guardar la tarea
                String titulo = editarTitulo.getText().toString();
                String descripcion = editarDescripcion.getText().toString();
                String fechaLimite = editarFechaLimite.getText().toString();
                String prioridad = spinnerPrioridad.getSelectedItem().toString();

                // Aquí puedes agregar el código para guardar la tarea en la base de datos
                guardarTarea(titulo, descripcion, fechaLimite, prioridad);
            }
        });

        // Ajustar los insets de la vista
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void guardarTarea(String titulo, String descripcion, String fechaLimite, String prioridad) {
        // Implementa aquí la lógica para guardar la tarea
        // Por ejemplo, insertar en la base de datos o en una lista
    }
}
