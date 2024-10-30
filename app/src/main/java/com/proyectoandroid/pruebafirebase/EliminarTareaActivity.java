package com.proyectoandroid.pruebafirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EliminarTareaActivity extends AppCompatActivity {

    private ListView listaTareas;
    private Button botonEliminar;
    private Button botonCancelar;
    private List<Tarea> tareas;
    private ArrayAdapter<String> adapter;
    private Tarea tareaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_tarea);

        listaTareas = findViewById(R.id.listaTareas);
        botonEliminar = findViewById(R.id.botonEliminar);
        botonCancelar = findViewById(R.id.botonCancelar);
        tareas = new ArrayList<>();

        // Configure the ListView and load tasks
        setupListView();
        cargarTareas();
    }

    private void setupListView() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, new ArrayList<>());
        listaTareas.setAdapter(adapter);
        listaTareas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listaTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tareaSeleccionada = tareas.get(position);
                Toast.makeText(EliminarTareaActivity.this, "Tarea seleccionada: " + tareaSeleccionada.getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });

        botonEliminar.setOnClickListener(v -> eliminarTareaSeleccionada());
        botonCancelar.setOnClickListener(v -> finish()); // Go back to the previous activity
    }

    private void cargarTareas() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("tareas");

        databaseReference.orderByChild("usuarioId").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tareas.clear();
                adapter.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Tarea tarea = snapshot.getValue(Tarea.class);
                    if (tarea != null) {
                        tarea.setId(snapshot.getKey()); // Set the ID from Firebase
                        tareas.add(tarea);
                        adapter.add(tarea.getTitulo());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EliminarTareaActivity.this, "Error al cargar las tareas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eliminarTarea(Tarea tarea) {
        if (tarea.getId() == null) {
            Toast.makeText(EliminarTareaActivity.this, "Error: Tarea no seleccionada", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("EliminarTarea", "Intentando eliminar tarea con ID: " + tarea.getId());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("tareas").child(tarea.getId());

        databaseReference.removeValue()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(EliminarTareaActivity.this, "Tarea eliminada", Toast.LENGTH_SHORT).show();
                        cargarTareas(); // Reload tasks after deletion
                    } else {
                        Toast.makeText(EliminarTareaActivity.this, "Error al eliminar la tarea", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(EliminarTareaActivity.this, "Fallo en la eliminación: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

    private void eliminarTareaSeleccionada() {
        if (tareaSeleccionada != null) {
            eliminarTarea(tareaSeleccionada);
        } else {
            Toast.makeText(EliminarTareaActivity.this, "Selecciona una tarea para eliminar", Toast.LENGTH_SHORT).show();
        }
    }
}
