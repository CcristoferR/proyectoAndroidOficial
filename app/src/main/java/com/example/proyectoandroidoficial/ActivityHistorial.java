package com.example.proyectoandroidoficial;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ActivityHistorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historial);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Lista de tareas completadas (puedes obtenerla desde tu base de datos o lógica)
        List<String> completedTasks = getCompletedTasks();

        // Agregar tareas como TextView al LinearLayout
        LinearLayout taskContainer = findViewById(R.id.taskContainer);
        for (String task : completedTasks) {
            addTaskTextView(taskContainer, task);
        }
    }

    // Método para agregar TextView de una tarea al layout
    private void addTaskTextView(LinearLayout container, String taskText) {
        TextView textView = new TextView(this);
        textView.setText(taskText);
        textView.setTextSize(18); // Tamaño de texto
        textView.setPadding(16, 16, 16, 16); // Margen para el TextView
        textView.setTextColor(getResources().getColor(android.R.color.black)); // Color del texto
        container.addView(textView); // Agregar el TextView al layout
    }

    // Este es un ejemplo de cómo obtener tareas completadas (puedes reemplazarlo por datos reales)
    private List<String> getCompletedTasks() {
        // Aquí podrías obtener las tareas desde una base de datos o cualquier fuente
        List<String> tasks = new ArrayList<>();
        tasks.add("Tarea 1 completada");
        tasks.add("Tarea 2 completada");
        tasks.add("Tarea 3 completada");
        return tasks;
    }
}