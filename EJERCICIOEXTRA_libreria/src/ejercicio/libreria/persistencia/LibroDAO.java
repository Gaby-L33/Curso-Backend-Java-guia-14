package ejercicio.libreria.persistencia;

import ejercicio.libreria.entidades.Libro;;
import java.util.Collections;
import java.util.List;
import javax.persistence.NoResultException;

public class LibroDAO extends DAO<Libro> {

    public void guardarLibro(Libro libro) throws Exception {
        Libro libroExistente = buscarLibroNombre(libro.getTitulo());
        if (libroExistente != null) {
            System.out.println("Ya existe un libro con el mismo nombre");
        }
        super.guardar(libro);
    }

    public void editarLibro(Libro libro) throws Exception {
        super.editar(libro);
    }

    public void darDeAltaLibro(Integer id) throws Exception {

        Libro libro = buscarLibroId(id);
        if (libro != null) {
            libro.setAlta(true);
            super.editar(libro);
        }
    }

    public void darDeBajaLibro(Integer id) throws Exception {
        Libro libro = buscarLibroId(id);
        if (libro != null) {
            libro.setAlta(false);
            super.editar(libro);
        }
    }

    public Libro buscarLibroId(long id) throws Exception {

        System.out.println("[Buscando Libro]");

        Libro Libro;
        try {
            super.conectar();
            Libro = em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :id", Libro.class).setParameter("id", id).getSingleResult();
            System.out.println(Libro.toString());
            desconectar();
            return Libro;
        } catch (Exception e) {
            System.out.println(" Error al Buscar Editorial ");
            System.out.println(e);
            return null;
        }
    }

    public Libro buscarLibroNombre(String nombre) throws Exception {

        System.out.println("[Buscando Libro]");
        try {
            conectar();
            Libro libro = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :nombre", Libro.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            desconectar();
            return libro;
        } catch (NoResultException e) {
            System.out.println("No se encontró ningún libro con el nombre: " + nombre);
            return null;
        } catch (Exception e) {
            System.out.println("Error al buscar libro por nombre: " + e.getMessage());
            throw e;
        }
    }

    public List<Libro> buscarLibrosPorNombreAutor(String nombreAutor) {
        try {
            conectar();

            List<Libro> libros = em.createQuery(
                    "SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre LIKE :nombreAutor", Libro.class)
                    .setParameter("nombreAutor", nombreAutor)
                    .getResultList();

            desconectar();
            return libros;
        } catch (Exception e) {
            System.out.println("Error al buscar libros por nombre de autor: " + e.getMessage());
            return Collections.emptyList(); // Devuelve una lista vacía en caso de error
        }
    }

    public List<Libro> buscarLibrosPorNombreEditorial(String nombreEditorial) {
        try {
            conectar();

            List<Libro> libros = em.createQuery(
                    "SELECT l FROM Libro l JOIN l.editorial e WHERE e.nombre LIKE :nombreEditorial", Libro.class)
                    .setParameter("nombreEditorial", nombreEditorial)
                    .getResultList();

            desconectar();
            return libros;
        } catch (Exception e) {
            System.out.println("Error al buscar libros por nombre de editorial: " + e.getMessage());
            return Collections.emptyList(); // Devuelve una lista vacía en caso de error
        }
    }
    
    public List<Libro> listarLibros(){
         try {
            conectar();

            List<Libro> libros = em.createQuery(
                    "SELECT l FROM libro l", Libro.class).getResultList();
            desconectar();
            return libros;
        } catch (Exception e) {
            System.out.println("Error al listar los libros ");
            return Collections.emptyList(); // Devuelve una lista vacía en caso de error
        }
          
    }
}
