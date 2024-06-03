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
	private ArrayList<String> historial = new ArrayList<>();

	/**
	 * Establece el controlador para este modelo.
	 * 
	 * @param controlador El controlador a establecer.
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	/**
	 * Genera una consonante aleatoria.
	 * 
	 * @return Una consonante aleatoria.
	 */
	private char consonantes() {
		String consonantes = "bdcfghjklmnñpqrstvwxyz";
		StringBuilder consonante = new StringBuilder(consonantes);
		Random numAleatorio = new Random();
		int consonanteAleatoria = numAleatorio.nextInt(consonante.length());
		return consonante.charAt(consonanteAleatoria);
	}

	/**
	 * Genera una o dos vocales aleatorias.
	 * 
	 * @return Un StringBuilder con una o dos vocales aleatorias.
	 */
	private StringBuilder vocales() {
		String vocales = "aeiou";
		StringBuilder vocal = new StringBuilder();
		Random numAleatorio = new Random();

		int numVocales = numAleatorio.nextInt(4);
		if (numVocales != 0) {
			int vocalAleatoria = numAleatorio.nextInt(5);
			vocal.append(vocales.charAt(vocalAleatoria));
		} else {
			for (int i = 0; i < 2; i++) {
				int vocalAleatoria = numAleatorio.nextInt(5);
				vocal.append(vocales.charAt(vocalAleatoria));
			}
		}
		return vocal;
	}

	/**
	 * Genera una palabra con el número de sílabas especificado. La palabra generada
	 * sigue reglas básicas de combinación de consonantes y vocales.
	 * 
	 * @param numSilabas El número de sílabas de la palabra a generar.
	 * @return La palabra generada.
	 */
	public String generarPalabra(int numSilabas) {
		StringBuilder palabra = new StringBuilder();
		for (int i = 0; i < numSilabas; i++) {
			if (i == 0) {
				palabra.append(String.valueOf(consonantes()).toUpperCase());
			} else {
				palabra.append(consonantes());
			}
			palabra.append(vocales());
		}
		String palabraFinal = palabra.toString();
		historial.add(palabraFinal);
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
