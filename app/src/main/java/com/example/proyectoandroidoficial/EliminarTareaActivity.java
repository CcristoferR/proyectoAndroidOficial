package com.example.proyectoandroidoficial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EliminarTareaActivity extends AppCompatActivity {

    // Variables para los elementos del layout
    private ListView listaTareas;
    private Button botonEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eliminar_tarea);

        // Inicializar las variables
        listaTareas = findViewById(R.id.listaTareas);
        botonEliminar = findViewById(R.id.botonEliminar);

        // Configurar el listener para el botón "Eliminar"
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para eliminar la tarea seleccionada
                eliminarTareaSeleccionada();
            }
        });

        // Ajustar los insets de la vista
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void eliminarTareaSeleccionada() {
        // Implementa aquí la lógica para eliminar la tarea seleccionada
        // Por ejemplo, eliminar de la base de datos
    }
}
