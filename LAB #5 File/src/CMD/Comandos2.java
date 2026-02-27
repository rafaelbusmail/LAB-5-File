/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CMD;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.io.File;

/**
 *
 * @author jerem
 */
public class Comandos2 extends Comandos1{
  public Comandos2(String pathInicial){
      super(pathInicial);
  }
  public String retroceder(){
      File dirActual = new File(getPathActual());
      File dirPadre = dirActual.getParentFile();
      //validacion para cuando no hay a donde retroceder
      if(dirPadre == null){
      return "ERROR: Ya se encuentra en el directorio principal";
  }
     if(!dirPadre.exists()){
         return "ERROR: El directorio padre no existe";
     }
    
      return cd("..");
  }
  public String dir(){
      File dirActual = new File(getPathActual());
      File [] entradas = dirActual.listFiles();
      String resultado = " Directorio de " + getPathActual() + "\n\n";
      
      if(entradas == null || entradas.length == 0){
          resultado = resultado + "  (directorio vacio)\n";
          return resultado;
      }
      int carpetasEncontradas = 0;
      int archivosEncontrados = 0;
      
      for (int i = 0; i < entradas.length; i++) {
          File entrada = entradas[i];
          
          if(entrada.isDirectory()){
              resultado = resultado + " <DIR>  " + entrada.getName() + "\n";
              carpetasEncontradas = carpetasEncontradas + 1;
          }
      }
      
       //propablemente el que recorre el posible array de Archivos heredadas de comandos1
       for (int i = 0; i < entradas.length; i++) {
          File entrada = entradas[i];
         
          if (entrada.isFile()) {
              resultado = resultado + "      " + entrada.getName() + "  ("+ entrada.length() + " bytes)\n";
              archivosEncontrados = archivosEncontrados + 1;
               
           }
      }
       // si no se encontro nada avisa que el directorio esta vacio
       if(carpetasEncontradas == 0 && archivosEncontrados == 0){
           resultado = resultado + " (Directorio Vacio)\n";
       }
       // mensaje final
       resultado = resultado + "\n " + carpetasEncontradas + " carpeta(s)  "+ archivosEncontrados + " archivo(s)";
       
      
      return resultado;  
  }
  public String date(){
      // obtiene la fecha actual y la formatea en espaniol
      String fecha = new SimpleDateFormat("EEEE dd/MM/yyyy", new Locale("es","ES")).format(new Date());
      
      return " La fecha actual es: " + fecha;
  }
  public String time(){
      //Obtiene la hora actual en formato de 24 horas
      String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
      return " La hora del sistema es: " + hora;
  }
}
