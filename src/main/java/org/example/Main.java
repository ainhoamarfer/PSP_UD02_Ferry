package org.example;
/**
 *
 * @author PON_TU_NOMBRE_COMPLETO_AQUÍ
 *
 * Ejercicio 1) Se trata de implementar un modelo de carretera/muelle/ferry, que funciona así:
 *
 * La clase carretera genera vehículos al azar y en tiempos al azar (son simplemente enteros que indican su tamaño).  YA TE LA DOY HECHA.
 * La clase Ferry intenta llenarse hasta el máximo de su capacidad, cogiendo vehículos del muelle que le quepan. YA TE LA DOY HECHA.
 * Muelle es un simple buffer (un array de enteros, cada entero es el tamaño de un vehículo.
 * Main es simplemente la clase principal que lanza todo.
 * Vuestra misión es:
 *
 * Lanzar todos los hilos necesarios y que el programa funcione correctamente.
 * El ferry parte de una orilla, espera hasta llenarse, vacía en la otra y vuelve.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // ======================= COMPLETAR =======================
        // creas el muelle: new Muelle("Norte")
        // creas el productor: new Carretera("CARRETERA", muelle, 1, 3)
        // creas el consumidor: new Ferry("FERRY", muelle, "Sur", 20, 3, 3)
        // haces la carretera daemon (setDaemon) para que el programa acabe cuando acabe el ferry
        // lanzas los dos hilos (start)
        // esperas a que termine el ferry (join) y muestras un mensaje final
        // =========================================================

        Muelle muelle = new Muelle("Norte");
        Carretera carretera = new Carretera("CARRETERA", muelle, 1, 3);
        Ferry ferry = new Ferry("FERRY", muelle, "Sur", 20, 3, 3);

        carretera.setDaemon(true);

        carretera.start();
        ferry.start();

        ferry.join();
        System.out.println("El ferry ha terminado su viaje.");
    }
}
