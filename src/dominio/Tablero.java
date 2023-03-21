package dominio;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Tablero {
    private static int DIMENSION = 30;
    private int estadoActual[][] = new int[DIMENSION + 2][DIMENSION + 2];
    private int estadoSiguiente[][] = new int[DIMENSION + 2][DIMENSION + 2];

    public void leerEstadoActual() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("matriz.txt.txt");

            for (int i = 0; i <= DIMENSION + 1; i++) {
                for (int j = 0; j <= DIMENSION + 1; j++) {
                    estadoActual[i][j] = 0;
            }
        }

             for (int i = 1; i <= DIMENSION; i++) {

                 for (int j = 1; j <= DIMENSION; j++) {
                 estadoActual[i][j] = fr.read()== 49?1:0;
             }

             fr.read();
             fr.read();
         }



    }

    public void generarEstadoActualPorMontecarlo() {
        double numero;
        for (int i = 1; i <= DIMENSION; i++) {
            for (int j = 1; j <= DIMENSION; j++) {
                numero =  (Math.random() );
                if (numero < 0.7) {
                    estadoActual[i][j] = 0;
                } else {
                    estadoActual[i][j] = 1;
                }

            }
        }
    }

    public void transitarAlEstadoSiguiente() {
        int vecinos;
        for (int i = 1; i <= DIMENSION ; i++) {
            for (int j = 1; j <= DIMENSION ; j++) {
                vecinos = estadoActual[i - 1][j - 1] + estadoActual[i - 1][j] + estadoActual[i - 1][j + 1] + estadoActual[i][j - 1] + estadoActual[i][j + 1] + estadoActual[i + 1][j - 1] + estadoActual[i + 1][j] + estadoActual[i + 1][j + 1];
                if (estadoActual[i][j] == 1) {
                    if (vecinos == 2 || vecinos == 3) {
                        estadoSiguiente[i][j] = 1;
                    } else {
                        estadoSiguiente[i][j] = 0;
                    }


                } else if (estadoActual[i][j] == 0) {
                    if (vecinos == 3) {
                        estadoSiguiente[i][j] = 1;
                    } else {
                        estadoSiguiente[i][j] = 0;
                    }
                }
            }


        }
        estadoActual = estadoSiguiente;
    }

    public String toString() {
        String cadena = "";
        for (int i = 1; i <= DIMENSION; i++) {
            for (int j = 1; j <= DIMENSION; j++) {
                if (estadoActual[i][j] == 1) {
                    cadena += "1";
                } else {
                    cadena += "0";              }
            }
            cadena += "\n";

        }
        return cadena;
    }


}
