package com.proyectoandroid.pruebafirebase;

public class Tarea {
    private String id; // Identifier field for Firebase
    private String titulo;
    private String descripcion;
    private String fechaLimite;
    private String prioridad;
    private String etiqueta;
    private String estado; // Field for the task status
    private String usuarioId; // Field for the user ID

    // Default constructor required for Firebase
    public Tarea() {
    }

    // Constructor to initialize all fields including ID
    public Tarea(String id, String titulo, String descripcion, String fechaLimite, String prioridad, String etiqueta, String estado, String usuarioId) {
        this.id = id; // Initialize ID
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad; // Ensure priority is valid if needed
        this.etiqueta = etiqueta;
        this.estado = estado; // Initialize the state
        this.usuarioId = usuarioId; // Initialize the user ID
    }

    // Method to validate that the priority is one of the allowed values
    private void validarPrioridad(String prioridad) {
        if (!prioridad.equals("alta") && !prioridad.equals("media") && !prioridad.equals("baja")) {
            throw new IllegalArgumentException("La prioridad debe ser alta, media o baja.");
        }
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public String getEstado() {
        return estado; // Getter for the state
    }

    public void setEstado(String estado) { // Setter for the state
        this.estado = estado;
    }

    public boolean isCompletada() {
        return "completada".equals(estado);
    }

    public void marcarComoCompletada() {
        this.estado = "completada";
    }

    public void marcarComoPendiente() {
        this.estado = "pendiente";
    }

    public String getUsuarioId() {
        return usuarioId; // Getter for user ID
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId; // Setter for user ID
    }

    // Optional: Override toString for better logging
    @Override
    public String toString() {
        return "Tarea{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaLimite='" + fechaLimite + '\'' +
                ", prioridad='" + prioridad + '\'' +
                ", etiqueta='" + etiqueta + '\'' +
                ", estado='" + estado + '\'' +
                ", usuarioId='" + usuarioId + '\'' +
                '}';
    }
}
