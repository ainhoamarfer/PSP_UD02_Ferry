package org.example.actividadProcesos1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EjercicioClase2 {

    static void main() throws IOException, InterruptedException {
        List<Process> listProcesos = new ArrayList<>();
        System.out.println("Introduce 2 números a sumar");
        Scanner sc = new Scanner(System.in);

        String numero1 = sc.nextLine();
        String numero2 = sc.nextLine();


        String classpath = System.getProperty("java.class.path");
        String rutaClaseHija = "ejercicios.ejerciciosAulaVirtual.UD1.EjercicioClase2Contador";

        ProcessBuilder constructor = new ProcessBuilder("java", "-cp", classpath, rutaClaseHija, numero1, numero2);

        constructor.inheritIO();

        Process proceso = constructor.start();

        listProcesos.add(proceso);
        proceso.waitFor();
    }
}

