package CMD;

import java.io.File;
import java.io.IOException;

public class Comandos1 {

    protected File pathActual;

    public Comandos1(String pathInicial) {
        this.pathActual = new File(pathInicial);
        if (!pathActual.exists()) {
            pathActual.mkdirs();
        }
    }

    public String getPathActual() {
        return pathActual.getAbsolutePath();
    }

    public String mkdir(String nombre) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                return "Error: nombre no válido.";
            }
            if (pathActual == null || !pathActual.exists() || !pathActual.isDirectory()) {
                return "Error: ruta actual no válida.";
            }

            File objetivo = new File(pathActual, nombre);
            if (objetivo.exists()) {
                return "Ya existe un subdirectorio o archivo: " + nombre;
            }

            if (objetivo.mkdirs()) { // mkdirs por si el padre no existe
                return "Carpeta creada: " + nombre;
            } else {
                return "Error al crear la carpeta: " + nombre;
            }

        } catch (SecurityException e) {
            return "Acceso denegado al crear la carpeta.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String mfile(String nombre) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                return "Error: nombre no valido.";
            }
            if (pathActual == null || !pathActual.exists() || !pathActual.isDirectory()) {
                return "Error: ruta actual no valida.";
            }

            File objetivo = new File(pathActual, nombre);
            if (objetivo.exists()) {
                return "Ya existe un archivo con el nombre: " + nombre;
            }

            File parent = objetivo.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (objetivo.createNewFile()) {
                return "Archivo creado: " + nombre;
            } else {
                return "Error al crear el archivo: " + nombre;
            }

        } catch (IOException e) {
            return "Error: no se pudo crear el archivo.";
        } catch (SecurityException e) {
            return "Acceso denegado al crear el archivo.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String rm(String nombre) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                return "Error: nombre no valido.";
            }
            if (pathActual == null) {
                return "Error: ruta actual no valida.";
            }

            File target = new File(pathActual, nombre);
            if (!target.exists()) {
                return "No se encuentra el archivo o carpeta: " + nombre;
            }

            if (borrarRecursivo(target)) {
                return nombre + " eliminado correctamente.";
            } else {
                return "No se pudo eliminar: " + nombre;
            }

        } catch (SecurityException e) {
            return "Acceso denegado al eliminar.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private boolean borrarRecursivo(File file) {
        if (file.isDirectory()) {
            File[] hijos = file.listFiles();
            if (hijos != null) {
                for (File child : hijos) {
                    if (!borrarRecursivo(child)) {
                        return false;
                    }
                }
            }
        }
        return file.delete();
    }

    public String cd(String nombre) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                return "Error: nombre no valido.";
            }

            File nuevaRuta = new File(pathActual, nombre);

            if (!nuevaRuta.exists()) {
                return "El sistema no puede encontrar la ruta especificada: " + nombre;
            }
            if (!nuevaRuta.isDirectory()) {
                return nombre + " no es un directorio.";
            }

            pathActual = nuevaRuta;
            return "Directorio actual: " + pathActual.getAbsolutePath();

        } catch (SecurityException e) {
            return "Acceso denegado al cambiar de directorio.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}