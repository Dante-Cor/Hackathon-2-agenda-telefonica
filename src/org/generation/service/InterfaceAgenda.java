package org.generation.service;

import org.generation.model.Contacto;
import org.generation.exceptions.ContactoException;
import org.generation.exceptions.AgendaLlenaException;


public interface InterfaceAgenda {

    // Métodos que manipulan el estado (pueden lanzar excepciones)
    void añadirContacto(Contacto c) throws ContactoException, AgendaLlenaException;
    void eliminarContacto(Contacto c) throws ContactoException;
    void modificarTelefono(String nombre, String apellido, String nuevoTelefono) throws ContactoException;

    // Métodos de consulta
    boolean existeContacto(Contacto c);
    String listarContactos();
    String buscaContacto(String nombre, String apellido);

    // Métodos de estado
    boolean agendaLlena();
    int espaciosLibres();
}
