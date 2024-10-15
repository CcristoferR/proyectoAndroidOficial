package com.example.proyectoandroidoficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configura el botón para abrir la actividad de crear tarea
        Button btnIrCrear = findViewById(R.id.btn_ir_crear);
        btnIrCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAgregarTareaActivity();
            }
        });

        // Configura el botón para abrir la actividad de editar tarea
        Button btnIrEditar = findViewById(R.id.btn_ir_editar);
        btnIrEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditarTareaActivity();
            }
        });

        // Configura el botón para abrir la actividad de eliminar tarea
        Button btnIrEliminar = findViewById(R.id.btn_ir_eliminar);
        btnIrEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEliminarTareaActivity();
            }
        });

        // Ajustar los insets de la vista
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void openAgregarTareaActivity() {
        Intent intent = new Intent(MainActivity.this, AgregarTareaActivity.class);
        startActivity(intent);
    }

    private void openEditarTareaActivity() {
        Intent intent = new Intent(MainActivity.this, EditarTareaActivity.class);
        startActivity(intent);
    }

    private void openEliminarTareaActivity() {
        Intent intent = new Intent(MainActivity.this, EliminarTareaActivity.class);
        startActivity(intent);
    }
}
