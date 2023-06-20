package pe.com.ejecutar;

import pe.com.datos.Consulta;
import pe.com.dominio.Persona;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Consulta consulta = new Consulta();

        // SELECT
        List<Persona> listaPersonas = consulta.select();
        for (Persona persona : listaPersonas) {
            System.out.println("persona = " + persona);
        }

        // INSERT
        Persona personaInsertar = new Persona();
        personaInsertar.setId(3);
        personaInsertar.setNombre("CRISTIANO");
        personaInsertar.setApellido("RONALDO");
        consulta.insert(personaInsertar);

        // UPDATE
        Persona personaActualizar = new Persona();
        personaActualizar.setId(3);
        personaActualizar.setNombre("TEST");
        personaActualizar.setApellido("ABC");
        consulta.update(personaActualizar);

        // DELETE
        Persona personaEliminar = new Persona();
        personaEliminar.setId(3);
        consulta.delete(personaEliminar);
    }
}