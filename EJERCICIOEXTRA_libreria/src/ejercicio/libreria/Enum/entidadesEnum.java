package ejercicio.libreria.Enum;

public enum entidadesEnum {
    
    LIBRO,
    EDITORIAL,
    AUTOR,
    CLIENTE,
    PRESTAMO;

    public static entidadesEnum getCLIENTE() {
        return CLIENTE;
    }

    public static entidadesEnum getPRESTAMO() {
        return PRESTAMO;
    }

    public static entidadesEnum getLIBRO() {
        return LIBRO;
    }

    public static entidadesEnum getEDITORIAL() {
        return EDITORIAL;
    }

    public static entidadesEnum getAUTOR() {
        return AUTOR;
    }
  
}
