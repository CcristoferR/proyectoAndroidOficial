package com.proyectoandroid.pruebafirebase;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button; // Asegúrate de importar Button
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ActivityHistorial extends AppCompatActivity {

    private RecyclerView recyclerViewHistorial;
    private TareaAdapter tareaAdapter;
    private List<Tarea> listaTareas;
    private Button buttonVolver; // Declarar el botón

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        // Inicializar RecyclerView
        recyclerViewHistorial = findViewById(R.id.recyclerViewHistorial);
        recyclerViewHistorial.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar la lista de tareas
        listaTareas = new ArrayList<>();

        // Configurar el adaptador
        tareaAdapter = new TareaAdapter(listaTareas);
        recyclerViewHistorial.setAdapter(tareaAdapter);

        // Cargar historial de tareas desde Firebase
        cargarHistorial();

        // Inicializar el botón para volver al Home
        buttonVolver = findViewById(R.id.buttonVolver);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAlHome(); // Llama al método para volver al Home
            }
        });
    }

    private void cargarHistorial() {
        // Obtener el ID del usuario autenticado
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Referencia a la base de datos
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("tareas");

        // Filtrar tareas por el ID del usuario
        databaseReference.orderByChild("usuarioId").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaTareas.clear(); // Limpiar la lista antes de agregar nuevos datos
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Tarea tarea = snapshot.getValue(Tarea.class);
                    if (tarea != null) {
                        listaTareas.add(tarea);
                    }
                }
                tareaAdapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores
            }
        });
    }

    // Método para volver al Home
    private void volverAlHome() {
        Intent intent = new Intent(ActivityHistorial.this, HomeActivity.class); // Cambia MainActivity por el nombre de tu actividad principal
        startActivity(intent);
        finish(); // Opcional: termina esta actividad
    }
}
