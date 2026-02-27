package CMD;
import java.io.IOException;
import java.io.File;

public class Comandos1 {
    
    private File carpetaActual;
    public Comandos1(String rutaInicial) {
        this.carpetaActual = new File(rutaInicial);
        if (!carpetaActual.exists()) {
            carpetaActual.mkdirs();
        }
        
    }
    
    public String getCarpetaActual() {
        return carpetaActual.getAbsolutePath();
    }
    
    public String mkdir(String nombreCarpeta) {
        return;
    }
    
    public String mfile (String nombreArchivo) {
        
    }
    
    public String rm(String nombre) {
        
    }
    
    public String cd (String nombreCarpeta) {
        
    }
}
