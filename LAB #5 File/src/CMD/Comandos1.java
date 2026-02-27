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
        
    }
    
    public String rm(String nombre) {
        
    }
    
    private boolean borrarRecursivo(File file) {
       
    }
    
    public String cd(String nombre) {
        
    }
}