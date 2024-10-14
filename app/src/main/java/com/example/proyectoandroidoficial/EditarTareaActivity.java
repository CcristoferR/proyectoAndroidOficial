package com.example.proyectoandroidoficial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditarTareaActivity extends AppCompatActivity {

    // Variables para los elementos del layout
    private EditText editarTitulo;
    private EditText editarDescripcion;
    private Button botonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_tarea);

        // Inicializar las variables
        editarTitulo = findViewById(R.id.editarTitulo);
        editarDescripcion = findViewById(R.id.editarDescripcion);
        botonGuardar = findViewById(R.id.botonGuardar);

        // Configurar el listener para el botón "Guardar"
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para editar la tarea
                String titulo = editarTitulo.getText().toString();
                String descripcion = editarDescripcion.getText().toString();

                // Aquí puedes agregar el código para actualizar la tarea en la base de datos
                actualizarTarea(titulo, descripcion);
            }
        });

        // Ajustar los insets de la vista
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void actualizarTarea(String titulo, String descripcion) {
        // Implementa aquí la lógica para actualizar la tarea
        // Por ejemplo, actualizar en la base de datos
    }
}
