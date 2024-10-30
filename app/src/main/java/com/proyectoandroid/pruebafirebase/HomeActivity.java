package com.proyectoandroid.pruebafirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTareas;
    private TareaAdapter tareaAdapter;
    private List<Tarea> listaTareas;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializar el RecyclerView
        recyclerViewTareas = findViewById(R.id.recyclerViewTareas);
        recyclerViewTareas.setLayoutManager(new LinearLayoutManager(this));
        listaTareas = new ArrayList<>();
        tareaAdapter = new TareaAdapter(listaTareas);
        recyclerViewTareas.setAdapter(tareaAdapter);

        // Inicializar la referencia de Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("tareas");

        // Cargar tareas de Firebase
        cargarTareas();

        // Inicializar el botón para crear tarea
        Button btnCrearTarea = findViewById(R.id.btn_ir_crear);
        btnCrearTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de agregar tarea
                Intent intent = new Intent(HomeActivity.this, AgregarTareaActivity.class);
                startActivity(intent);
            }
        });

        // Inicializar el botón para ir al historial
        Button btnIrHistorial = findViewById(R.id.btn_ir_historial);
        btnIrHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de historial
                Intent intent = new Intent(HomeActivity.this, ActivityHistorial.class);
                startActivity(intent);
            }
        });

        // Inicializar el botón para cerrar sesión
        Button btnCerrarSesion = findViewById(R.id.btn_cerrar_sesion);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });
    }

    private void cargarTareas() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid(); // Obtener el ID del usuario autenticado

        databaseReference.orderByChild("usuarioId").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaTareas.clear(); // Limpiar la lista antes de cargar
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Tarea tarea = snapshot.getValue(Tarea.class);
                    listaTareas.add(tarea);
                }
                tareaAdapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejo de errores si es necesario
            }
        });
    }

    private void cerrarSesion() {
        FirebaseAuth.getInstance().signOut(); // Cerrar sesión de Firebase
        // Navegar a la pantalla de inicio de sesión
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Limpiar la pila de actividades
        startActivity(intent);
        finish(); // Finalizar esta actividad
    }
}
