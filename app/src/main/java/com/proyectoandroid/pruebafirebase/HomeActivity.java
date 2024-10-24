package com.proyectoandroid.pruebafirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        Button btnCrearTarea = findViewById(R.id.btn_ir_crear);
        Button btnEditarTarea = findViewById(R.id.btn_ir_editar);
        Button btnEliminarTarea = findViewById(R.id.btn_ir_eliminar);
        Button btnVerHistorial = findViewById(R.id.btn_ir_historial);

        btnCrearTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, com.proyectoandroid.pruebafirebase.AgregarTareaActivity.class);
                startActivity(intent);
            }
        });

        btnEditarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EditarTareaActivity.class);
                startActivity(intent);
            }
        });

        btnEliminarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EliminarTareaActivity.class);
                startActivity(intent);
            }
        });

        btnVerHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ActivityHistorial.class);
                startActivity(intent);
            }
        });
    }
}
