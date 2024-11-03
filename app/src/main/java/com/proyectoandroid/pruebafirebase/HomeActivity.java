package com.proyectoandroid.pruebafirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

    // Nuevas variables para filtrado
    private Spinner spinnerEstado, spinnerPrioridad, spinnerCategoria;
    private List<Tarea> tareasFiltradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializar el RecyclerView
        recyclerViewTareas = findViewById(R.id.recyclerViewTareas);
        recyclerViewTareas.setLayoutManager(new LinearLayoutManager(this));
        listaTareas = new ArrayList<>();
        tareasFiltradas = new ArrayList<>();
        tareaAdapter = new TareaAdapter(tareasFiltradas); // Usamos la lista filtrada
        recyclerViewTareas.setAdapter(tareaAdapter);

        // Inicializar los Spinners
        spinnerEstado = findViewById(R.id.filtracion_estado);
        spinnerPrioridad = findViewById(R.id.filtracion_prioridad);
        spinnerCategoria = findViewById(R.id.filtracion_categoria);

        // Configurar los Spinners
        setupSpinners();

        // Inicializar la referencia de Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("tareas");

        // Cargar tareas de Firebase
        cargarTareas();

        // Configurar botón de cerrar sesión
        Button btnCerrarSesion = findViewById(R.id.btn_cerrar_sesion);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion(); // Llamar al método para cerrar sesión
            }
        });

        // Navegación a Agregar Tarea
        Button btnIrCrear = findViewById(R.id.btn_ir_crear);
        btnIrCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AgregarTareaActivity.class);
                startActivity(intent);
            }
        });

        // Navegación a Editar Tarea
        Button btnIrEditar = findViewById(R.id.btn_ir_editar);
        btnIrEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EditarTareaActivity.class);
                startActivity(intent);
            }
        });

        // Navegación a Eliminar Tarea
        Button btnIrEliminar = findViewById(R.id.botonIrEliminar);
        btnIrEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EliminarTareaActivity.class);
                startActivity(intent);
            }
        });

        // Navegación a Historial
        Button btnIrHistorial = findViewById(R.id.btn_ir_historial);
        btnIrHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ActivityHistorial.class);
                startActivity(intent);
            }
        });
    }

    private void setupSpinners() {
        // Ejemplo de datos para los Spinners
        ArrayAdapter<CharSequence> estadoAdapter = ArrayAdapter.createFromResource(this,
                R.array.estados_array, android.R.layout.simple_spinner_item);
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(estadoAdapter);

        ArrayAdapter<CharSequence> prioridadAdapter = ArrayAdapter.createFromResource(this,
                R.array.prioridades_array, android.R.layout.simple_spinner_item);
        prioridadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridad.setAdapter(prioridadAdapter);

        ArrayAdapter<CharSequence> categoriaAdapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_array, android.R.layout.simple_spinner_item);
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(categoriaAdapter);

        // Configurar listener para el filtrado
        AdapterView.OnItemSelectedListener filterListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtrarTareas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        };

        spinnerEstado.setOnItemSelectedListener(filterListener);
        spinnerPrioridad.setOnItemSelectedListener(filterListener);
        spinnerCategoria.setOnItemSelectedListener(filterListener);
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
                filtrarTareas(); // Filtrar después de cargar
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejo de errores si es necesario
            }
        });
    }

    private void filtrarTareas() {
        String estadoSeleccionado = spinnerEstado.getSelectedItem().toString();
        String prioridadSeleccionada = spinnerPrioridad.getSelectedItem().toString();
        String categoriaSeleccionada = spinnerCategoria.getSelectedItem().toString();

        tareasFiltradas.clear();

        for (Tarea tarea : listaTareas) {
            boolean coincidenEstado = tarea.getEstado().equals(estadoSeleccionado) || estadoSeleccionado.equals("Todos");
            boolean coincidenPrioridad = tarea.getPrioridad().equals(prioridadSeleccionada) || prioridadSeleccionada.equals("Todas");
            boolean coincidenCategoria = tarea.getEtiqueta().equals(categoriaSeleccionada) || categoriaSeleccionada.equals("Todas");

            if (coincidenEstado && coincidenPrioridad && coincidenCategoria) {
                tareasFiltradas.add(tarea);
            }
        }

        tareaAdapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
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
