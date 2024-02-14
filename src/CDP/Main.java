package CDP;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		constructores constructor = new constructores();
		Scanner in = new Scanner(System.in);
		int numLetras = 1;
		System.out.println("Introduce '0' cuando quieras salir");
		while (numLetras != 0) {
			StringBuilder palabra = new StringBuilder();

			System.out.print("Introduce el número de cuantas sílabas quieres que tenga tu palabra: ");
			numLetras = in.nextInt();

			for (int i = 0; i < numLetras; i++) {
				//Repite el bucle tantas veces como sílabas determinadas por el usuario
				palabra.append(constructor.consonantes());
				palabra.append(constructor.vocales());
			}

			System.out.println(palabra);
			System.out.println("");

		}
		in.close();
	}

}