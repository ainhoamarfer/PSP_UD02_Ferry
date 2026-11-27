package org.example.PiedraPapelTijerapProcesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * El Árbitro (proceso padre) lanza 2 procesos org.example.PiedraPapelTijerapProcesos.solucion.Jugador con ProcessBuilder, pasándoles su id (1 o 2) como argumento. En cada ronda, cada jugador
 * elige su jugada al azar. Gana la partida quien llegue antes a 3 rondas ganadas.
 */
public class Main {

    static void main() throws IOException {

        //Formato de mesa.txt

        //J1=PIEDRA
        //J2=
        //Valores posibles: PIEDRA, PAPEL o TIJERA.
        //Con el bloqueo cogido, el jugado

        //Cada ronda

        //Cada jugador escribe su jugada en mesa.txt y envía LISTO.
        //Con los 2 LISTO, el árbitro bloquea, lee, deja J1= / J2= y suelta.
        //Resuelve la ronda (el empate no puntúa) y envía el marcador
        //o GANADOR.
        //Con GANADOR, cada jugador termina con código 0 y el árbitro hace
        //waitFor() de los dos.


        int numHijos = 2;

        Process[] procesos = new Process[numHijos];
        PrintWriter[] haciaHijo = new PrintWriter[numHijos];
        BufferedReader[] desdeHijo = new BufferedReader[numHijos];

        ProcessBuilder pbJugador0 = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), "Jugador1");
        ProcessBuilder pbJugador1 = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), "Jugador2");

        procesos[0] = pbJugador0.start();
        procesos[1] = pbJugador1.start();

        haciaHijo[0] = new PrintWriter(procesos[0].getOutputStream(), true);
        haciaHijo[1] = new PrintWriter(procesos[1].getOutputStream(), true);

        desdeHijo[0] = new BufferedReader(new InputStreamReader(procesos[0].getInputStream()));
        desdeHijo[1] = new BufferedReader(new InputStreamReader(procesos[1].getInputStream()));



    }
}
