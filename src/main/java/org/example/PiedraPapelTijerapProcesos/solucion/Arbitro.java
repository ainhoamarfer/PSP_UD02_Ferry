package org.example.PiedraPapelTijerapProcesos.solucion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Arbitro {

    static final String MESA = "mesa.txt";
    static final int PUNTOS_PARA_GANAR = 3;

    public static void main(String[] args) throws IOException, InterruptedException {
        limpiarMesa();

        String cp = System.getProperty("java.class.path");
        Process[] p = new Process[2];
        PrintWriter[] aHijo = new PrintWriter[2];
        BufferedReader[] deHijo = new BufferedReader[2];

        for (int i = 0; i < 2; i++) {
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", cp, "org.example.PiedraPapelTijerapProcesos.solucion.Jugador", String.valueOf(i + 1));
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            p[i] = pb.start();
            aHijo[i] = new PrintWriter(p[i].getOutputStream(), true);
            deHijo[i] = new BufferedReader(new InputStreamReader(p[i].getInputStream()));
        }

        int[] puntos = new int[2];
        int ronda = 1;
        boolean fin = false;

        while (!fin) {
            for (int i = 0; i < 2; i++) {
                String m = deHijo[i].readLine();
                if (!"LISTO".equals(m)) {
                    throw new IllegalStateException("J" + (i + 1) + " ha enviado: " + m);
                }
            }

            String[] jugadas = leerYLimpiarMesa();
            int g = ganadorRonda(jugadas[0], jugadas[1]);
            if (g >= 0) {
                puntos[g]++;
            }
            System.out.println("Ronda " + ronda++ + ": J1=" + jugadas[0] + " J2=" + jugadas[1]
                    + " -> " + (g < 0 ? "empate" : "gana J" + (g + 1)));

            String msg;
            if (puntos[0] == PUNTOS_PARA_GANAR || puntos[1] == PUNTOS_PARA_GANAR) {
                msg = "GANADOR: J" + (puntos[0] == PUNTOS_PARA_GANAR ? 1 : 2);
                fin = true;
            } else {
                msg = "J1: " + puntos[0] + ", J2: " + puntos[1];
            }
            System.out.println(msg);
            for (PrintWriter w : aHijo) {
                w.println(msg);
            }
        }

        for (int i = 0; i < 2; i++) {
            System.out.println("org.example.PiedraPapelTijerapProcesos.solucion.Jugador " + (i + 1) + " termina con código " + p[i].waitFor());
        }
    }

    static String[] leerYLimpiarMesa() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(MESA, "rw");
             FileChannel ch = raf.getChannel();
             FileLock lock = ch.lock()) {
            String j1 = raf.readLine().substring(3);
            String j2 = raf.readLine().substring(3);
            raf.setLength(0);
            raf.writeBytes("J1=\nJ2=\n");
            return new String[]{j1, j2};
        }
    }

    static void limpiarMesa() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(MESA, "rw");
             FileChannel ch = raf.getChannel();
             FileLock lock = ch.lock()) {
            raf.setLength(0);
            raf.writeBytes("J1=\nJ2=\n");
        }
    }

    // -1 empate, 0 gana J1, 1 gana J2
    static int ganadorRonda(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return gana(a, b) ? 0 : 1;
    }

    static boolean gana(String a, String b) {
        return (a.equals("PIEDRA") && b.equals("TIJERA"))
                || (a.equals("PAPEL") && b.equals("PIEDRA"))
                || (a.equals("TIJERA") && b.equals("PAPEL"));
    }
}
