/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CMD;


/**
 *
 * @author jerem
 */
public class Comandos2 extends Comandos1{
  public Comandos2(){
      super();
  }
  public String retroceder(){
      //validacion para cuando no hay a donde retroceder
      if(/*Ruta Actual*/.equals("C:")){
      return "ERROR: Ya se encuentra en el directorio principal C:\\";
  }
      //busca la posicion del ultimo en la ruta actual
      int separador = /*Ruta Actual*/.lastIndexOf('\\');
      
      //corta la ruta desde el inicio haasta donde este el ultimo
      
      /*Ruta Actual*/ = /*Ruta Actual*/.substring(0, separador);
      return "";
  }
  public String dir(){
      //muestra lo que hay hasta ahora en el directorio
      String resultado = " Directorio de "+ /*rutaActual*/ + "\\\n\n";
      int carpetasEncontradas = 0;
      int archivosEncontrados = 0;
      
      //propablemente el que recorre el posible array de carpetas heredadas de comandos1
      for (int i = 0; i < /*Carpetas totales*/; i++) {
          String ruta = /*Rutas de las carpetas*/[i];
          
          //ignora la raiz C: y muestra solo las del directorio actual
          if(!ruta.equals("C:") && /*metodo que revisa rutas que pertenecen al directorio actual*/(ruta)){
              resultado = resultado + " <DIR>  " + /*Metodo que extrae el nombre final de la ruta completa*/(ruta) + "\n";
              carpetasEncontradas = carpetasEncontradas + 1;
          }
      }
      
       //propablemente el que recorre el posible array de Archivos heredadas de comandos1
       for (int i = 0; i < /*Archivos totales*/; i++) {
          String ruta = /*Rutas de los archivos*/[i];
          
          //muestra solo los archivos del directorio actual;
          if (/*metodo que revisa rutas que pertenecen al directorio actual*/(ruta)) {
              int bytes = /*variable que guarda el contenido de cada archivo*/[i].getBytes().length;
              resultado = resultado + "      " + /*Metodo que extrae el nombre final de la ruta completa*/(ruta) + "  ("+ bytes + " bytes)\n";
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
      return "";
  }
  public String time(){
      return "";
  }
}
