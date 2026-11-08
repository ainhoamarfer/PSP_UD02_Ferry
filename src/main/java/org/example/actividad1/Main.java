package org.example.actividad1;
/**
 *
 * @author Ainhoa Martínez Fernández
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

    /**
     * Como yo lo entiendo
     * Empiezo por el muelle que es el recurso compartido, con un buffer de tamaño fijo y métodos synchronized para añadir y quitar vehículos.
     * Luego la carretera es el productor, que genera vehículos y los añade al muelle.
     * por ultimo el consumidor, el ferry, que intenta coger vehículos del muelle hasta llenarse y luego vacía en la otra orilla.
     */
    public static void main(String[] args) throws InterruptedException {
        // ======================= COMPLETAR =======================
        // creas el muelle: new Muelle("Norte")
        // creas el productor: new Carretera("CARRETERA", muelle, 1, 3)
        // creas el consumidor: new Ferry("FERRY", muelle, "Sur", 20, 3, 3)
        // haces la carretera daemon (setDaemon) para que el programa acabe cuando acabe el ferry
        // lanzas los dos hilos (start)
        // esperas a que termine el ferry (join) y muestras un mensaje final
        // =========================================================

        // creo el muelle
        Muelle muelle = new Muelle("Norte");

        //creo el productor, le doy el muelle que acabo de crear
        Carretera carretera = new Carretera("CARRETERA", muelle, 1, 3);

        // creo el consumidor
        Ferry ferry = new Ferry("FERRY", muelle, "Sur", 20, 3, 3);

        //haces la carretera daemon (setDaemon), para cuando el ferry termine sus tres viajes, el programa pueda finalizar
        // aunque la carretera siga generando vehículos
        carretera.setDaemon(true);

        //inicio los hilos
        carretera.start();
        ferry.start();

        // join porque para que el hilo principal espere hasta que el hilo del ferry termine.
        ferry.join();
        //este mensaje aparece cuando ferry.join haya terminado.
        System.out.println("El ferry ha terminado su servicio.");
    }
}

