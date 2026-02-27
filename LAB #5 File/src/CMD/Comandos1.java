import java.io.File;
import java.io.IOException;

public class Comandos1 {
    
    private File pathActual;
    
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
                return "Error: nombre no valido.";
            }
            
            if (pathActual == null || !pathActual.exists() || !pathActual.isDirectory()) {
                return "Error: ruta actual no valida.";
            }
            
            File objetivo = new File(pathActual, nombre);
            
            if (objetivo.exists()) {
                return "Ya existe un subdirectorio o archivo " + nombre + ".";
            }
            
            if (objetivo.mkdir()) {
                return "Carpeta creada: " + nombre;
            } else {
                return "Error al crear la carpeta " + nombre + ".";
            }
            
        } catch (SecurityException e) {
            return "Acceso denegado.";
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
                return "Ya existe un archivo con el nombre " + nombre + ".";
            }
            
            File parent = objetivo.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            
            if (objetivo.createNewFile()) {
                return "Archivo creado: " + nombre;
            } else {
                return "Error al crear el archivo " + nombre + ".";
            }
            
        } catch (IOException e) {
            return "Error de E/S: No se pudo crear el archivo.";
        } catch (SecurityException e) {
            return "Acceso denegado.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    public String rm(String nombre) {
        
    }
    
    private boolean borrarRecursivo(File file) {
       
    }
    
    public String cd(String nombre) {
        
    }
}