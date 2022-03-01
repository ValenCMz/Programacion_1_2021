package ARREGLOS;
import java.util.Random;

public class EJ11_PRACTICO7 {

	public static final int MAX = 20;
	public static final int minValue = 0;
	public static final int maxValue = 9;
	public static final double zeroProbability = 0.4;
	
	public static void cargarArreglo (int arreglo[]) {
		Random r = new Random();
		arreglo[0] = 0;
		arreglo[MAX - 1] = 0;
		for (int i = 1; i < MAX - 1; i++)
			if (r.nextDouble() > zeroProbability)
				arreglo[i] = (r.nextInt(maxValue - minValue + 1) + minValue);
			else 
				arreglo[i] = 0;
		
		}
	
	public static void imprimirArreglo (int arreglo[]) {
				for (int i = 0; i < MAX; i++) {
		            System.out.print("[" + arreglo[i] + "]");
		        }
		        System.out.println("");
			}
	
	public static int inicioSecuencia (int arreglo[], int pos) {
		while((arreglo[pos] == 0)  && (pos < MAX-1)) {
			pos++;
		}
		return pos;
	}
	
	public static int finSecuencia (int arreglo [], int posInicio) {
		while ((arreglo[posInicio] != 0) && (posInicio < MAX-1)) {
			posInicio++;
		}
		return posInicio-1;
	}
	
	public static void compararSecuencias (int arreglo []) {
		int sumaMAX = 0;
		int inicioMAX = 0;
		int finMAX = 0;
		
		int pos = 0;
		int inicio = inicioSecuencia (arreglo, pos);
		while (inicio < MAX) {
			int fin = finSecuencia(arreglo, inicio);
			int suma = 0;
			for(int a = inicio; a <= fin; a++) {
				suma += arreglo[a];
			}
			if (suma > sumaMAX) {
				sumaMAX = suma;
				inicioMAX = inicio;
				finMAX = fin;
			}

			pos = fin + 1;
            if (pos < MAX - 1) {
                inicio = inicioSecuencia(arreglo,pos);
            } else {
                break;
            }
		}
		System.out.println("La suma de los valores de la secuencia entre posiciones " + inicioMAX + " y " + finMAX + " es la más grande.");
	}
	
	public static void main(String[] args) {
		//Hacer un programa que dado el arreglo definido y precargado permita
		//encontrar la posición de inicio y fin de la secuencia cuya suma de valores sea
		//mayor.
		
		int arreglo [] = new int [MAX];
		
		cargarArreglo(arreglo);
		imprimirArreglo(arreglo);
		compararSecuencias(arreglo);

	}

}
