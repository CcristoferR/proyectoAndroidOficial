package com.proyectoAndroid.proyectoandroidoficial;

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
    private Button botonCancelar; // Declaración del botón Cancelar
    private Button botonVolver; // Declaración del botón Volver

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eliminar_tarea);

        // Inicializar las variables
        listaTareas = findViewById(R.id.listaTareas);
        botonEliminar = findViewById(R.id.botonEliminar);
        botonCancelar = findViewById(R.id.botonCancelar); // Inicializar botón Cancelar
        botonVolver = findViewById(R.id.botonVolver); // Inicializar botón Volver

        // Ajustar los insets de la vista
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar el listener para el botón Cancelar
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // No hacer nada en el botón Cancelar
                // Si deseas que no haga nada, simplemente no incluyas ningún código aquí.
            }
        });

        // Configurar el listener para el botón Volver
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cerrar la actividad y volver a la anterior
            }
        });
    }
}
