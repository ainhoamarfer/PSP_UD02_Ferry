package org.example.PiedraPapelTijerapProcesos.solucion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Random;

public class Jugador {

    static final String MESA = "mesa.txt";
    static final String[] OPCIONES = {"PIEDRA", "PAPEL", "TIJERA"};

    public static void main(String[] args) throws IOException {
        int id = Integer.parseInt(args[0]);
        Random r = new Random();
        BufferedReader dePadre = new BufferedReader(new InputStreamReader(System.in));

        String msg;
        do {
            escribirJugada(id, OPCIONES[r.nextInt(OPCIONES.length)]);
            System.out.println("LISTO");
            System.out.flush();
            msg = dePadre.readLine();
        } while (msg != null && !msg.startsWith("GANADOR"));
    }

    static void escribirJugada(int id, String jugada) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(MESA, "rw");
             FileChannel ch = raf.getChannel();
             FileLock lock = ch.lock()) {
            String j1 = raf.readLine();
            String j2 = raf.readLine();
            if (id == 1) {
                j1 = "J1=" + jugada;
            } else {
                j2 = "J2=" + jugada;
            }
            // reescribo el fichero entero
            raf.setLength(0);
            raf.writeBytes(j1 + "\n" + j2 + "\n");
        }
    }
}
