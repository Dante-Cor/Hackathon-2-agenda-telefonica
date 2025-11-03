package org.generation.model;

import java.util.Objects;

public class Contacto implements Comparable<Contacto> {
    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }
    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //Representación en String para la impresión
    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + telefono;
    }
    //Implementación de equals: Contactos iguales si tienen el mismo nombre y apellido (insensible a mayúsculas/minúsculas).
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;

        return nombre.equalsIgnoreCase(contacto.nombre) &&
                apellido.equalsIgnoreCase(contacto.apellido);
    }
    @Override
    public  int hashCode() {
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }
    // Implementación de Comparable: Ordena alfabéticamente por nombre y luego por apellido.
    @Override
    public int compareTo(Contacto otro) {
        int comparacionNombre = this.nombre.compareToIgnoreCase(otro.nombre);
        if (comparacionNombre != 0) {
            return comparacionNombre;
        }
        return this.apellido.compareToIgnoreCase(otro.apellido);
    }

}

