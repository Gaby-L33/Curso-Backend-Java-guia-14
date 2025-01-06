package ejercicio.libreria.persistencia;

import ejercicio.libreria.entidades.Prestamo;

public class PrestamoDAO extends DAO<Prestamo>{
    
    public void ingresarPrestamo(Prestamo prestamo){
        try {
            super.guardar(prestamo);
        } catch (Exception ex) {
            System.out.println("Error al ingresar Prestamo en BD");
        }
    }

}
