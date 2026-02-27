/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CMD;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jerem
 */

public class Comandos2 extends Comandos1 {

    public Comandos2(String pathInicial) {
        super(pathInicial);
    }

    public boolean cdBack() {
        File padre = pathActual.getParentFile();
        if (padre == null) {
            return false;
        }
        pathActual = padre;
        return true;
    }

    public String dir(String nombre) {
        File archivoDir;

        if (nombre == null || nombre.trim().isEmpty() || ".".equals(nombre)) {
            archivoDir = pathActual;
        } else {
            archivoDir = new File(pathActual, nombre);
        }

        if (!archivoDir.exists() || !archivoDir.isDirectory()) {
            return "No es un directorio válido.";
        }

        StringBuilder contenido = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        File[] hijos = archivoDir.listFiles();
        if (hijos != null) {
            for (File child : hijos) {
                String fecha = sdf.format(new Date(child.lastModified()));
                contenido.append(fecha)
                         .append("  ")
                         .append(child.isDirectory() ? "<DIR> " : "FILE ")
                         .append(child.getName())
                         .append("\n");
            }
        }

        return contenido.toString();
    }

    public String date() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String time() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public String leerTexto(String nombre) {
        File target = new File(pathActual, nombre);

        if (!target.exists()) {
            return "El archivo no existe.";
        }

        StringBuilder contenido = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            return "Error al leer.";
        }

        return contenido.toString();
    }
}