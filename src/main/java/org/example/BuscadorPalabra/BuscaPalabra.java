package org.example.BuscadorPalabra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BuscaPalabra {

    static void main(String[] args) {

        String ruta = args[0];
        String palabra = args[1];


        ArrayList<String> lineas = new ArrayList<>();
        int contadorPalabra = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.contains(palabra)) {
                    System.out.println("La línea en la que se encuentra la palabra " + palabra + " es " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("No se pudo leer el fichero: " + e.getMessage());
        }


    }


}
