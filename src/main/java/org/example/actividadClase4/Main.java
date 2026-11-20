package org.example.actividadClase4;

public class Main {

    public static int contador = 0;

    static void main() throws InterruptedException {
        String text= "Cinco actividades, 8 horas. Al terminarlas tendrás en MULTAGAL un componente de archivado jerárquico de expedientes, un servicio de purga por prescripción, un servicio que crea denuncias a partir de una petición (DenunciaRequest) con su repositorio, un vigilante de directorio que procesa en segundo plano los CSV de captación de los radares y registra las multas detectadas, y una jerarquía de excepciones propia del dominio, todo ello accesible a través de los controladores REST correspondientes y cubierto por pruebas unitarias y de integración.";

        Thread ContarVocal = new ContarVocal(text, 'a');

        ContarVocal.start();
        ContarVocal.join();

        System.out.println(contador);
    }



    static class ContarVocal extends Thread {

        private String texto;
        private Character vocal;


        public ContarVocal(String texto, Character vocal){
            this.texto = texto;
            this.vocal = vocal;
        }

        public synchronized int getCuenta(){
            for (int i = 0; i < texto.length(); i++) {
                if (vocal.equals(texto.charAt(i))) {
                    contador++;
                }
            }
            return contador;
        }

        @Override
        public void run() {
            getCuenta();
        }
    }
}










