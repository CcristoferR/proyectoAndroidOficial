package com.proyectoandroid.pruebafirebase;

public class Tarea {
    private String titulo;
    private String descripcion;
    private String fechaLimite;
    private String prioridad;
    private String etiqueta;
    private String estado; // Nuevo campo para el estado

    // Constructor vacío requerido por Firebase
    public Tarea() {
    }

    public Tarea(String titulo, String descripcion, String fechaLimite, String prioridad, String etiqueta, String estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.etiqueta = etiqueta;
        this.estado = estado; // Inicialización del nuevo campo
    }

    // Getters y setters
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

    public String getEstado() { // Getter para el nuevo campo
        return estado;
    }

    public void setEstado(String estado) { // Setter para el nuevo campo
        this.estado = estado;
    }
}
