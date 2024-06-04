package Modelo;

import java.util.ArrayList;
import java.util.Random;

import Controlador.Controlador;

/**
 * La clase Modelo representa el modelo en el patrón MVC. Genera palabras y
 * mantiene un historial de las palabras generadas.
 */
public class Modelo {
	private Controlador controlador;

	private int contador;

	private ArrayList<String> historial = new ArrayList<>();

	private Random numAleatorio = new Random();
	private StringBuilder palabra = new StringBuilder();

	private int numLetras;

	private String consonantes = "bdcfghjklmnñpqrstvwxyz";
	private String vocales = "aeiou";

	private char vocal;
	private char consonante;

	private boolean esCorrecto;
	private int ultimaAccion = 0;

	/**
	 * Establece el controlador para este modelo.
	 * 
	 * @param controlador El controlador a establecer.
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	/*
	 * Convierte el caracter añadido a mayuscula
	 * 
	 */
	private void convertirEnMayus() {
		palabra.setCharAt(0, Character.toUpperCase(palabra.charAt(0)));
		System.out.println("Consonante: '" + palabra.charAt(0) + "' pasada a mayuscula");
	}

	/**
	 * Genera una consonante aleatoria y la adjunta al StringBuilder de la palabra.
	 * 
	 */
	private void consonantes() {
		ultimaAccion = 0;
		// TODO CONSONANTES DOBLES Y COMBINADAS
		// TODO REGLAS DE Qu, Gu
		int numConsonante = 0;

		do {
			esCorrecto = true;
			numConsonante = numAleatorio.nextInt(consonantes.length());
			consonante = consonantes.charAt(numConsonante);
			System.out.println("Generada la consonante: " + consonante);

			// Reduce la tasa de aparición de la palabras menos comunes del castellano
			if (consonante == 'k' || consonante == 'w') {
				esCorrecto = numAleatorio.nextInt(1000) == 0 ? true : false;
			} else if (consonante == 'ñ' || consonante == 'x' || consonante == 'y') {
				esCorrecto = numAleatorio.nextInt(100) == 0 ? true : false;
			}

			// Evita que la primera consonante sea una de las poco comunes en el castellano
			if (contador == 0 && (consonante == 'k' || consonante == 'ñ' || consonante == 'w' || consonante == 'x'
					|| consonante == 'y')) {
				esCorrecto = false;
			}

			// Trata de que ultima letra sea una consonante común en el castellano
			// solo si la palabra tiene más de una letra
			if (numLetras > 1 && contador == numLetras - 1
					&& (consonante != 'n' && consonante != 'r' && consonante != 's' && consonante != 'l')) {
				esCorrecto = false;
			}

			if (!esCorrecto) {
				System.out.println("Consonante no válida");
			}

		} while (!esCorrecto);

		palabra.append(consonante);
		System.out.println("Añadida la consonante: " + consonante);

		if (palabra.length() == 1) {
			convertirEnMayus();
		}

		System.out.println("Estado de la palabra: " + palabra);
	}

	/**
	 * Genera una, dos o tres  vocales aleatorias y las adjunta al StringBuilder de la
	 * palabra.
	 */
	private void vocales() {
		ultimaAccion = 1;
		int numVocales = 0;

		/*
		 * Si no es la penúltima letra, permite que haya la probabilidad de que se
		 * genere una vocal triple, doble y única. Si no es la última letra, permite la
		 * probabildad de que se genere una vocal doble y única. Si en cualquier otro
		 * caso, solo se puede generar una vocal única
		 */
		if (numLetras > 1 && !(contador >= numLetras - 2)) {
			numVocales = numAleatorio.nextInt(30);
		} else if (numLetras > 1 && !(contador == numLetras - 1)) {
			numVocales = numAleatorio.nextInt(27);
		}

		// Una vocal
		if (numVocales < 20) {
			System.out.println("Vocal única:");
			int vocalAleatoria = numAleatorio.nextInt(vocales.length());
			vocal = vocales.charAt(vocalAleatoria);
			System.out.println("Generada la vocal: " + vocal);
			palabra.append(vocal);
			System.out.println("Añadida vocal: " + vocal);

			if (contador == 0) {
				convertirEnMayus();
			}
			// Vocal doble (TODO distinguir diptongo de hiato)
		} else if (numVocales >= 20 && numVocales < 28) {
			System.out.println("Vocal doble:");
			for (int i = 0; i < 2; i++) {
				int vocalAleatoria = numAleatorio.nextInt(vocales.length());
				vocal = vocales.charAt(vocalAleatoria);
				System.out.println("Generada la vocal: " + vocal);
				palabra.append(vocal);
				System.out.println("Añadida vocal: " + vocal);

				// Convierte a mayusucula si es la primera letra de la palabra y del par
				if (contador == 0 && i == 0) {
					convertirEnMayus();
				}
			}
			contador++;
			// Vocal triple (TODO distinguir triptongos)
		} else {
			System.out.println("Vocal triple:");
			for (int i = 0; i < 3; i++) {
				int vocalAleatoria = numAleatorio.nextInt(vocales.length());
				vocal = vocales.charAt(vocalAleatoria);
				System.out.println("Generada la vocal: " + vocal);
				palabra.append(vocal);
				System.out.println("Añadida vocal: " + vocal);

				// Convierte a mayusucula si es la primera letra de la palabra y del par
				if (contador == 0 && i == 0) {
					convertirEnMayus();
				}
			}
			contador += 2;
		}

		System.out.println("Estado de la palabra: " + palabra);
	}

	/**
	 * Genera una palabra con el número de sílabas especificado. La palabra generada
	 * sigue reglas básicas de combinación de consonantes y vocales.
	 * 
	 * @param numLetras El número de sílabas de la palabra a generar.
	 */
	public String generarPalabra(int numLetras) {
		this.numLetras = numLetras;

		System.out.println("___________________________________");
		ultimaAccion = numAleatorio.nextInt(2);
		if (ultimaAccion == 0) {
			System.out.println("Consonante primero");
		} else {
			System.out.println("Vocal primero");
		}

		System.out.println("-------------------------------");
		for (contador = 0; contador < numLetras; contador++) {
			if (ultimaAccion == 1) {
				consonantes();
			} else {
				vocales();
			}
			System.out.println("-------------------------------");
		}

		if (numLetras != palabra.length()) {
			System.out.println("\u001B[33mEL NUMERO DE LETRAS NO ES CORRECTO:\n - nº pedido: " + numLetras
					+ "\n - nº generado: " + palabra.length() + "\u001B[0m");
			System.exit(0);
		}

		System.out.println("___________________________________");
		String palabraFinal = palabra.toString();
		historial.add(palabraFinal);
		palabra.setLength(0);

		return palabraFinal;
	}

	/**
	 * Obtiene el historial de palabras generadas.
	 * 
	 * @return Una cadena de texto con el historial de palabras generadas, separadas
	 *         por saltos de línea excepto en el último elemento.
	 */
	public String getHistorial() {
		String lista = "";
		for (int i = 0; i < historial.size(); i++) {
			lista += historial.get(i);
			if (i != historial.size() - 1) {
				lista += "\n";
			}
		}

		return lista;
	}
}
