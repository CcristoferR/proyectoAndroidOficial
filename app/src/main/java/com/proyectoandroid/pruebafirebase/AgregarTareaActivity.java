package com.proyectoandroid.pruebafirebase;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AgregarTareaActivity extends AppCompatActivity {

    private EditText editarTitulo, editarDescripcion, editarFechaLimite;
    private Spinner spinnerPrioridad, spinnerEtiquetas;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarea);

        // Inicialización de vistas
        editarTitulo = findViewById(R.id.editarTitulo);
        editarDescripcion = findViewById(R.id.editarDescripcion);
        editarFechaLimite = findViewById(R.id.editarFechaLimite);
        spinnerPrioridad = findViewById(R.id.spinnerPrioridad);
        spinnerEtiquetas = findViewById(R.id.spinnerEtiquetas);

        // Configuración de los adaptadores para los Spinners
        ArrayAdapter<CharSequence> adapterPrioridad = ArrayAdapter.createFromResource(
                this, R.array.prioridades, android.R.layout.simple_spinner_item
        );
        adapterPrioridad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridad.setAdapter(adapterPrioridad);

        ArrayAdapter<CharSequence> adapterEtiqueta = ArrayAdapter.createFromResource(
                this, R.array.etiquetas, android.R.layout.simple_spinner_item
        );
        adapterEtiqueta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEtiquetas.setAdapter(adapterEtiqueta);

        // Inicialización de Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("tareas");

        // Botón Guardar
        Button botonGuardar = findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarTarea();
            }
        });

        // Botones Cancelar y Volver
        Button botonCancelar = findViewById(R.id.botonCancelar);
        botonCancelar.setOnClickListener(v -> finish());

        Button botonVolver = findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(v -> finish());
    }

    private void guardarTarea() {
        String titulo = editarTitulo.getText().toString().trim();
        String descripcion = editarDescripcion.getText().toString().trim();
        String fechaLimite = editarFechaLimite.getText().toString().trim();
        String prioridad = spinnerPrioridad.getSelectedItem().toString();
        String etiqueta = spinnerEtiquetas.getSelectedItem().toString();

        // Validación de campos vacíos
        if (TextUtils.isEmpty(titulo) || TextUtils.isEmpty(descripcion) || TextUtils.isEmpty(fechaLimite)) {
            Toast.makeText(this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener el ID del usuario autenticado
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Crear el objeto de tarea con los datos
        Map<String, Object> tarea = new HashMap<>();
        tarea.put("titulo", titulo);
        tarea.put("descripcion", descripcion);
        tarea.put("fechaLimite", fechaLimite);
        tarea.put("prioridad", prioridad);
        tarea.put("etiqueta", etiqueta);
        tarea.put("estado", "pendiente");
        tarea.put("usuarioId", userId); // Asociar la tarea al usuario

        // Enviar la tarea a Firebase
        databaseReference.push().setValue(tarea).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(AgregarTareaActivity.this, "Tarea guardada exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(AgregarTareaActivity.this, "Error al guardar la tarea", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
