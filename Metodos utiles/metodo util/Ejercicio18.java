/*18.Se tiene una matriz MAT de caracteres letras minúsculas precargada, se pide:
–cargar un arreglo ARR_CANT_VOCALES_FILAS_ORD los índices de las filas de MAT y ordenarlo de forma tal que permita mantener un 
orden decreciente de MAT por la cantidad de vocales de sus filas.
–dada una fila ingresada por el usuario desde teclado, eliminar la fila de la matriz MAT y actualizar el arreglo
 ARR_CANT_VOCALES_FILAS_ORD (no hay que aplicar método de ordenamiento).*/
package Clase8;

import java.util.Random;

public class Ejercicio18 {
	public static final int MAXFILA = 4;
	public static final int MAXCOLUMNA = 20;
	public static final double probabilidad_letra = 0.4;
	
	public static void cargar_arreglo_aleatorio_secuencias_char(char[] arr) {
		Random r = new Random();
		arr[0] = ' ';
		arr[MAXCOLUMNA-1] = ' ';
		for (int pos = 1; pos < MAXCOLUMNA-1; pos++){
			if(r.nextDouble()>probabilidad_letra){
				arr[pos]=(char)(r.nextInt(26) + 'a');
			}
			else{
				arr[pos]=' ';
			}
		}
	}
	
	public static void cargar_matriz_aleatorio_char(char [][] matriz) {
		for (int fila = 0; fila < MAXFILA; fila++){
			cargar_arreglo_aleatorio_secuencias_char(matriz[fila]);
		}
	}
	
	public static void imprimir_matriz(char [][] matriz) {
		for (int fila = 0; fila < MAXFILA; fila++) {
			for (int columna = 0; columna < MAXCOLUMNA; columna++) {
				System.out.print("|"+matriz[fila][columna]);
			}
			System.out.println("|");
			System.out.print("\n");
		}
	}
	
	public static void imprimir_arreglo (int [] arr) {
		System.out.print("Arreglo de secuencias int:\n|");
		for (int pos = 0; pos < MAXFILA; pos++){
			System.out.print(arr[pos]+"|");
		}
		System.out.print("\n");
	}
	
	public static void cargar_arreglo_indice(int [] arreglo) {
		for (int fila = 0; fila < MAXFILA; fila++) {
			arreglo[fila] = fila;
		}
	}
	
	public static boolean hayVocal(char caracter) {
		if (caracter == 'a' || caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u')
			return true;
		else
			return false;
	}
	
	public static int cantidadVocalesFila(char [][] matriz, int fila) {
		int cantidad = 0;
		for (int col = 0; col < MAXCOLUMNA; col++) {
			if (hayVocal(matriz[fila][col])) {
				cantidad++;
			}
		}
		return cantidad;
	}
	
	public static void ordenar_arreglo_indices_vocales(char [] [] matriz, int [] arrIndice) {
		int temp;
		for (int i = 1;	i < MAXFILA; i++) {
			for (int j = 0 ; j < MAXFILA -1; j++) {
				if ( cantidadVocalesFila(matriz,arrIndice[j]) > cantidadVocalesFila(matriz,arrIndice[j+1])) {
					temp= arrIndice[j];
					arrIndice[j] = arrIndice[j+1];
					arrIndice[j+1] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		char [][] matChar = new char [MAXFILA][MAXCOLUMNA];
		int [] arreglo = new int [MAXFILA];
		cargar_matriz_aleatorio_char(matChar);
		imprimir_matriz(matChar);
		cargar_arreglo_indice(arreglo);
		imprimir_arreglo(arreglo);
		ordenar_arreglo_indices_vocales(matChar,arreglo);
		System.out.println();
		System.out.println("Arreglo de indices ordenado");
		imprimir_arreglo(arreglo);
	}
}
