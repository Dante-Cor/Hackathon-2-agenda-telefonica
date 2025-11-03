package org.generation.service;

import org.generation.model.Contacto;
import org.generation.exceptions.AgendaLlenaException;
import org.generation.exceptions.ContactoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Agenda implements InterfaceAgenda{
    //lista de contactos
    private List<Contacto> contactos;
    private final int sizeMax;
    private static final int sizeDefault = 10;

    // Constructor con tamaño máximo especificado
    public Agenda(int sizeMax) {
        this.sizeMax = sizeMax > 0 ? sizeMax : sizeDefault;
        // Usamos ArrayList para la lista interna, que implementa List
        this.contactos = new ArrayList<>(this.sizeMax);
    }
    // Constructor con tamaño por defecto (10 contactos)
    public Agenda() {
        this(sizeDefault);
    }

    //Validar campos de nombre y apellido
    private void validarDatos(Contacto c) {
        if (c.getNombre() == null || c.getNombre().trim().isEmpty() ||
                c.getApellido() == null || c.getApellido().trim().isEmpty()) {
            throw new ContactoException("Error: El nombre y el apellido no pueden estar vacíos.");
        }
    }

    //Metodo añadirContacto(Contacto c): Añade un contacto a la agenda. Si no hay espacio suficiente, se debe indicar al usuario que la agenda está llena.
    // Antes de añadir el contacto, se debe comprobar que no exista ya en la agenda (contactos con el mismo nombre y apellido se consideran duplicados).
    public void añadirContacto(Contacto c) throws ContactoException, AgendaLlenaException {
        validarDatos(c);
        if (agendaLlena()) {
            throw new AgendaLlenaException("Error: La agenda está llena (Máximo: " + sizeMax + ").");
        }
        if (existeContacto(c)) {
            throw new ContactoException("Error: El contacto (" + c.getNombre() + " " + c.getApellido() + ") ya existe en la agenda.");
        }

        contactos.add(c);
    }

    //Metodo existeContacto(Conctacto c): Verifica si un contacto ya existe en la agenda.
    // Los contactos se consideran iguales si tienen el mismo nombre y apellido, sin importar el teléfono.
    public boolean existeContacto(Contacto c)   {
        return contactos.contains(c);
    };

    //Metodo listarContactos(): Muestra todos los contactos de la agenda en el siguiente formato: Nombre Apellido - Teléfono.
    //Ordena los contactos alfabéticamente por nombre y apellido antes de mostrarlos.
    public String listarContactos() {
        if (contactos.isEmpty()) {
            return "La Agenda está vacía.";
        }
        List<Contacto> contactosOrdenados = new ArrayList<>(this.contactos);
        Collections.sort(contactosOrdenados);

        StringBuilder sb = new StringBuilder();
        sb.append("--- Lista de Contactos (Total: ").append(contactos.size()).append(") ---\n");
        for (Contacto c : contactosOrdenados) {
            sb.append(c.toString()).append("\n");
        }
        sb.append("------------------------------------------");
        return sb.toString();
    }

    //Metodo buscaContacto(String nombre): Permite buscar un contacto por nombre y apellido.
    //Si el contacto existe, muestra el teléfono. Si no existe, muestra un mensaje indicando que no se ha encontrado.
    public String buscaContacto(String nombre, String apellido) {
        Contacto contactoBusqueda = new Contacto(nombre, apellido, "");

        for (Contacto c : contactos) {
            if (c.equals(contactoBusqueda)) {
                return "Contacto encontrado: " + c.getNombre() + " " + c.getApellido() + "- Teléfono: " + c.getTelefono();
            }
        }
        return "Contacto no encontrado: No existe un contacto con ese nombre y apellido.";
    }


    //Metodo eliminarContacto(Contacto c):Elimina un contacto de la agenda. Muestra un mensaje indicando si la eliminación ha sido exitosa o no.
    //Si se intenta eliminar un contacto que no existe, debe indicarse al usuario.
    public void eliminarContacto(Contacto c) throws ContactoException {
        if (!contactos.remove(c)) {
            throw new ContactoException("Error: No se puede eliminar. El contacto no existe en la agenda.");
        }
    }

    //Metodo modificarTelefono(String nombre, String apellido, String nuevoTelefono):
    //Permite modificar el teléfono de un contacto existente.
    //Si el contacto no existe, debe mostrar un mensaje.
    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) throws ContactoException {
        Contacto contactoBusqueda = new Contacto(nombre, apellido, "");

        for (Contacto c : contactos) {
            if (c.equals(contactoBusqueda)) {
                c.setTelefono(nuevoTelefono);
                return;
            }
        }
        throw new ContactoException("Error: No se pudo modificar. El contacto (" + nombre + " " + apellido + ") no existe en la agenda.");
    }

    //Metodo agendaLlena():
    //Indica si la agenda está llena.
    //Muestra un mensaje indicando que no hay espacio disponible para nuevos contactos.
    @Override
    public boolean agendaLlena() {
        return contactos.size() >= sizeMax;
    };

    //Metodo espacioLibres():
    //Muestra cuántos contactos más se pueden agregar a la agenda.
    //Esto debe basarse en el tamaño máximo definido al crear la agenda.
    public int espaciosLibres(){
        return sizeMax - contactos.size();}


}
