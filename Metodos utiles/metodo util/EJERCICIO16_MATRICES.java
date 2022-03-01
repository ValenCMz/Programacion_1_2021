package MATRICES;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class INTENTO_PARCIAL4 {
	
	public static final int maxFilas = 4;
	public static final int maxColumnas = 20;
	public static final double zeroProbability = 0.4;
	
	public static void cargarMatriz (char matriz[][]) {
		int posFilas = 0;
		matriz[posFilas][0] = ' ';
		matriz[posFilas][maxColumnas - 1] = ' ';
		while (posFilas < maxFilas) {
			Random r = new Random();
			for (int i = 1; i < maxColumnas-1; i++) {
				if (r.nextDouble() > zeroProbability)
					matriz[posFilas][i] = (char)(r.nextInt(26) + 'a');			
				else 
					matriz[posFilas][i] = ' ';	
			}
			posFilas++;
		}
	}

	public static void imprimirMatriz (char matriz[][]) {
		int posFilas = 0;
		while (posFilas < maxFilas) {
			for (int i = 0; i < maxColumnas; i++) {
	            System.out.print("[" + matriz[posFilas][i] + "]");
	        }
	        System.out.println("");
	        posFilas++;
		}
	}
	
	public static int solicitarNumero() {		
		BufferedReader captura = new BufferedReader(new InputStreamReader(System.in));
		try {
			int numeroUsuario = Integer.valueOf(captura.readLine());
			if(numeroUsuario >= 0 && numeroUsuario < maxFilas) {
				return numeroUsuario;				
			}
			else {
				return -1;
			}
		}
		catch (Exception E) {
			return -1;
		}
	}
	
	public static void recorrerFilas(char matrizA[][], char matrizB[][]) {
		for(int a = 0; a < maxFilas; a++) {
			int indiceSecuenciaMayor = buscarSecuenciaMayor(matrizA[a], a);
			int indicePrimerSecuencia = buscarInicio(matrizB[a], 0);
			eliminarPrimerVocal(matrizA[a]);
			eliminarPrimerVocal(matrizB[a]);
			agregarPrimerCaracter(matrizA[a], indiceSecuenciaMayor);
			intercambiarSecuencias(matrizA[a], matrizB[a], indicePrimerSecuencia, indiceSecuenciaMayor);
		}
	}
	
	public static int buscarSecuenciaMayor(char arreglo[], int fila) {
		int inicio = buscarInicio(arreglo, 0);
		int tamañoMax = 0;
		int indiceTamañoMax = 0;
		while(inicio != -1) {
			int fin = buscarFin(arreglo, inicio);
			if(fin != -1) {
				int tamañoSecuencia = fin - inicio + 1;
				if(tamañoSecuencia > tamañoMax) {
					tamañoMax = tamañoSecuencia;
					indiceTamañoMax = inicio;
				}
				inicio = buscarInicio(arreglo, fin+1);
			}
		}
		return indiceTamañoMax;
	}
	
	public static void eliminarPrimerVocal(char arreglo[]) {
		int inicio = buscarInicio(arreglo, 0);
		while(inicio != -1) {
			int fin = buscarFin(arreglo, inicio);
			if(fin != -1) {
				int posPrimerVocal = buscarPrimerVocal(arreglo, inicio, fin);
				if(posPrimerVocal != -1) {
					eliminarConCorrimiento(arreglo, posPrimerVocal);
					inicio = buscarInicio(arreglo, fin+1);
				}
				else {
					inicio = buscarInicio(arreglo, fin+1);
				}
			}
		}
	}
	
	public static int buscarPrimerVocal(char arreglo[], int inicio, int fin) {
		int pos = 0;
		for(int a = inicio; a <= fin; a++) {
			switch(arreglo[a]) {
			case 'a' :
				pos = a;
				break;
			case 'e' :
				pos = a;
				break;
			case 'i' :
				pos = a;
				break;
			case 'o' :
				pos = a;
				break;
			case 'u' :
				pos = a;
				break;
			default :
				pos = -1;
			}
		}
		return pos;
	}
	
	public static void eliminarConCorrimiento (char arreglo[], int posInicial) {
		for(int a = posInicial; a < maxColumnas; a++) {
			arreglo[a] = arreglo[a + 1];
		}
	}
	
	public static void agregarPrimerCaracter (char arreglo[], int primerCaracterSecuenciaMayor) {
		int inicio = buscarInicio(arreglo, 0);
		while(inicio != -1) {
			int fin = buscarFin(arreglo, inicio);
			if(fin != -1) {
				realizarCorrimientoDerecha(arreglo, inicio);
				arreglo[inicio] = arreglo[primerCaracterSecuenciaMayor];
			}
		}
	}
	
	public static void realizarCorrimientoDerecha(char arreglo[], int inicioSecuencia) {
		for(int a = maxColumnas-1; a > inicioSecuencia; a--) {
			arreglo[a] = arreglo[a-1];
		}
	}
	
	public static void intercambiarSecuencias (char arregloA[], char arregloB[], int indicePrimerSecuencia, int indiceSecuenciaMayor) {
		int finPrimerSecuencia = buscarFin(arregloB, indicePrimerSecuencia);
		int finSecuenciaMayor = buscarFin(arregloA, indiceSecuenciaMayor);
		int tamañoSecuenciaArregloB = finPrimerSecuencia - indicePrimerSecuencia + 1;
		int tamañoSecuenciaArregloA = finSecuenciaMayor - indiceSecuenciaMayor + 1;
		if(tamañoSecuenciaArregloA > tamañoSecuenciaArregloB) {
			int diferenciaTamaño = tamañoSecuenciaArregloA - tamañoSecuenciaArregloB;
			ampliarSecuenciaMenor(arregloB, diferenciaTamaño, indicePrimerSecuencia);
			
			int posSecuenciaA = indiceSecuenciaMayor;
			int posSecuenciaB = indicePrimerSecuencia;
			while((posSecuenciaA <= finSecuenciaMayor) && (posSecuenciaB <= finPrimerSecuencia)) {
				char temporal = arregloA[posSecuenciaA];
				arregloA[posSecuenciaA] = arregloB[posSecuenciaB];
				arregloB[posSecuenciaB] = temporal;
					posSecuenciaA++;			
					posSecuenciaB++;
			}
			achicarSecuenciaMayor(arregloA, diferenciaTamaño, finSecuenciaMayor);
		}
	}

	public static void ampliarSecuenciaMenor(char arreglo[], int diferenciaTamaño, int indicePrimerSecuencia) {
		int cantidadCorrimientosRestantes = diferenciaTamaño;
		while(cantidadCorrimientosRestantes > 0) {
			realizarCorrimientoDerecha(arreglo, indicePrimerSecuencia);
			cantidadCorrimientosRestantes--;
		}
	}

	public static void achicarSecuenciaMayor(char arreglo[], int diferenciaTamaño, int finSecuenciaMayor) {
		int cantidadCorrimientosRestantes = diferenciaTamaño;
		while(cantidadCorrimientosRestantes > 0) {
			eliminarConCorrimiento(arreglo, finSecuenciaMayor);
			cantidadCorrimientosRestantes--;
		}
	}
	
	public static void compararPrimerasSecuencias (char arregloA[], char arregloB[]) {
		int indicePrimeraSecuenciaA = buscarInicio(arregloA, 0);
		int FinPrimeraSecuenciaA = buscarFin(arregloA, indicePrimeraSecuenciaA);
		int tamañoSecuenciaA = FinPrimeraSecuenciaA - indicePrimeraSecuenciaA + 1;
		
		int indicePrimeraSecuenciaB = buscarInicio(arregloA, 0);
		int FinPrimeraSecuenciaB = buscarFin(arregloA, indicePrimeraSecuenciaB);
		int tamañoSecuenciaB = FinPrimeraSecuenciaB - indicePrimeraSecuenciaB + 1;
		
		if(tamañoSecuenciaB == tamañoSecuenciaA) {
			System.out.println("las primeras secuencias de la fila ingresada son iguales");
		}
		else {
			System.out.println("las primeras secuencias de la fila ingresada NO son iguales");
		}
	}
	
	public static int buscarInicio (char [] arr, int indice) {
		int pos = indice;
		while (pos < maxColumnas && arr[pos] == ' ') {
			pos++;
		}
		if (pos < maxColumnas)
			return pos;
		else
			return -1;
	}
	
	public static int buscarFin (char [] arr, int indice) {
		int pos = indice;
		while (pos < maxColumnas && arr[pos] != ' ') {
			pos++;
		}
		if (pos < maxColumnas)
			return pos - 1;
		else
			return -1;
	}
	
	public static void main(String[] args) {
		char matrizA[][] = new char [maxFilas][maxColumnas];
		cargarMatriz(matrizA);
		imprimirMatriz(matrizA);
		
		System.out.println("\nSEGUNDA MATRÍZ:");
		char matrizB[][] = new char [maxFilas][maxColumnas];
		cargarMatriz(matrizB);
		imprimirMatriz(matrizB);
		
		recorrerFilas(matrizA, matrizB);
		System.out.println("ingrese un numero de fila: ");
		int numeroUsuario = solicitarNumero();
		compararPrimerasSecuencias(matrizA[numeroUsuario], matrizB[numeroUsuario]);
		
	}
/*
 * Hay dos matrices MAT1 y MAT2 de secuencias de caracteres letras separados
por espacios de tamaño MAXF x MAXC que están precargadas. Ambas
matrices están precargadas y cada fila empieza y termina con caracteres
espacios. Además se tiene el siguiente método:

	– un método que retorna el índice inicial de la secuencia de mayor tamaño de un
	arreglo de secuencias (de caracteres letras minúsculas separados por espacios) de
	tamaño MAXFIL.
	
Se pide realizar un programa que:

	– contenga la definición de los encabezados de los métodos de carga de la matriz y
	del método mencionado en el enunciado (se supone que existen y no se requiere
	implementarlos).
	
	– para MAT1 y MAT2 elimine de cada secuencia el primer carácter vocal.
	
	– para MAT1 agregue al principio de cada secuencia el primer carácter de la
	secuencia de mayor tamaño de dicha fila.
	
	– en cada fila, si se verifica que la secuencia de mayor tamaño de la fila para MAT1 es
	mayor que la primer secuencia en dicha fila para MAT2, las intercambie (la que está
	en MAT1 pasa a MAT2 y la que esta en MAT2 pasa a MAT1) sin usar estructuras
	auxiliares (otros arreglos o matrices).
	
	– para un valor de fila ingresado por el usuario verifique e imprima si la primera
	secuencia de MAT1 en dicha fila es igual la primera secuencia de MAT2 en dicha fila.
 */
}
