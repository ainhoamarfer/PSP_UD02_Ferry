package org.example.actividad2;
/**
 *
 * @author PON_TU_NOMBRE_COMPLETO_AQUÍ
 */

import java.util.ArrayList;
import java.util.List;

// consumidor: carga en el muelle de origen, cruza, descarga al otro lado y vuelve
public class Ferry extends Thread {

    //Para este ejercicio necesito poder pasarle dos muelles, para que pueda cargar y descargar en los dos, y saber donde esta, para eso el boolean
    private final Muelle muelleOrigen;
    private final Muelle muelleDestino;
    private boolean estaEnOrigen = true; // true si está en el muelle1, false si está en el muelle2

    private final String destino;
    private final int capacidad;
    private final int viajes;
    private final int tiempoTrayecto;
    private final List<Integer> cargados = new ArrayList<>();
    private int espacioLibre;

    //añado al constructor los nuevos parámetros para poder pasarle los dos muelles
    public Ferry(String nombre, Muelle muelleOrigen, Muelle muelleDestino, boolean estaEnOrigen, String destino, int capacidad, int viajes, int tiempoTrayecto) {
        super(nombre);
        this.muelleOrigen = muelleOrigen;
        this.muelleDestino = muelleDestino;
        this.estaEnOrigen = true; // el ferry empieza en el muelle de origen
        this.destino = destino;
        this.capacidad = capacidad;
        this.viajes = viajes;
        this.tiempoTrayecto = tiempoTrayecto;
        this.espacioLibre = capacidad;
    }

    @Override
    public void run() {
        try {
            for (int viaje = 1; viaje <= viajes; viaje++) {

                //Veo en que muelle estoy
                Muelle muelleActual = estaEnOrigen ? muelleOrigen : muelleDestino;

                //Veo a cual voy luego
                Muelle muelleSiguiente = estaEnOrigen ? muelleDestino : muelleOrigen;

                log("viaje " + viaje + "/" + viajes
                        + ": atracado en " + muelleActual.getNombre()
                        + ", hay " + muelleActual.enEspera()
                        + " vehículos esperando");

                cargar(muelleActual);

                navegar(muelleActual.getNombre(), muelleSiguiente.getNombre());

                // Descargamos en el muelle al que acabamos de llegar
                descargar(muelleSiguiente);

                // Cambio la posición del ferry
                if (estaEnOrigen) {
                    estaEnOrigen = false;
                } else {
                    estaEnOrigen = true;
                }

            }
            log("fin de servicio, has hecho " + viajes + " viajes");
        } catch (InterruptedException e) {
            log("interrumpido, dejas el servicio");
        }
    }

    // cargas hasta llenarte; si no cabe nada, el muelle te deja dormido hasta que llegue algo
    //aquile paso el muelle en el que esta
    private void cargar(Muelle muelleDondeEstas) throws InterruptedException {

        while (espacioLibre > 0) {

            //cargo en el muelle donde este
            int v = muelleDondeEstas.embarcar(espacioLibre);
            cargados.add(v);
            espacioLibre -= v;
            log("carga " + Carretera.nombreVehiculo(v) + " -> huecos libres: " + espacioLibre + "/" + capacidad);
        }
        log("carga completa, zarpas");
    }

    // simulas la travesía con una espera
    private void navegar(String desde, String hasta) throws InterruptedException {
        log("~~~ navegando de " + desde + " a " + hasta + " ~~~");
        Thread.sleep(tiempoTrayecto * 1000L);
    }

    // vacías el ferry en destino: los vehículos salen de la simulación
    //aquile paso el muelle en el que esta
    private void descargar(Muelle muelleDondeDescargar) {
        while (!cargados.isEmpty()) {
            int v = cargados.remove(0);
            espacioLibre += v;

            log("descarga " + Carretera.nombreVehiculo(v) + " en " + muelleDondeDescargar.getNombre());
        }
    }

    private void log(String msg) {
        System.out.println("[" + getName() + "] " + msg);
    }
}
