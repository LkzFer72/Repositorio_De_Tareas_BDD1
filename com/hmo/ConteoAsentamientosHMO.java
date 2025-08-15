package com.hmo;  // Definimos el paquete para organizar el código

import java.io.BufferedReader;  // Leer el archivo línea por línea
import java.io.FileReader;  // Acceder al archivo CSV
import java.io.IOException;  // Manejar errores de entrada/salida
import java.util.HashMap;  // Almacenar códigos postales y sus respectivos conteos
import java.util.Map;  // Trabajar con HashMap

public class ConteoAsentamientosHMO {

    public static void main(String[] args) {
        //Ruta del archivo CSV donde se encuentran los datos
        String rutaArchivo = "com/hmo/codigos_postales_hmo.csv";

        //HashMap para almacenar el conteo de asentamientos por código postal
        HashMap<String, Integer> conteoCodigosPostales = new HashMap<>();

        //Bloque try-with-resources para asegurarnos de que el archivo se cierre automáticamente
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {

            String linea;  //Almacenar cada línea leída del archivo

            //Leer y saltar la primera línea que contiene los encabezados
            br.readLine();

            //Leer el archivo línea por línea hasta que no haya más líneas
            while ((linea = br.readLine()) != null) {

                //Separar cada línea por la coma, obteniendo un arreglo con los valores
                String[] partes = linea.split(",");

                //Si la línea tiene menos de 2 elementos, la ignoramos
                if (partes.length < 2) continue;

                //Extraer el código postal
                String codigoPostal = partes[1].trim();  //trim() para eliminar espacios en blanco

                //Añadir el código postal al HashMap, aumentando su conteo si ya existe
                conteoCodigosPostales.put(codigoPostal, conteoCodigosPostales.getOrDefault(codigoPostal, 0) + 1);
            }

        } catch (IOException e) {  // Si ocurre algún error al leer el archivo, lo captura
            System.out.println("Error al leer el archivo: " + e.getMessage());  // Imprimir el mensaje de error
        }

        // Recorrer el HashMap para imprimir los códigos postales y el número de asentamientos
        for (Map.Entry<String, Integer> entry : conteoCodigosPostales.entrySet()) {
            // Imprimir el código postal y su respectivo conteo de asentamientos
            System.out.println("Código postal: " + entry.getKey() + " - Número de asentamientos: " + entry.getValue());
        }
    }
}