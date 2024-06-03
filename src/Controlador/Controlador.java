package Controlador;

import Modelo.Modelo;
import Vista.Vista;

/**
 * La clase Controlador actúa como el controlador en el patrón MVC. Coordina las
 * interacciones entre la vista y el modelo.
 */
public class Controlador {
	private Modelo modelo;
	private Vista vista;

	/**
	 * Establece el modelo para este controlador.
	 * 
	 * @param modelo El modelo a establecer.
	 */
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	/**
	 * Establece la vista para este controlador.
	 * 
	 * @param vista La vista a establecer.
	 */
	public void setVista(Vista vista) {
		this.vista = vista;
	}

	/**
	 * Genera una palabra utilizando el modelo y la muestra en la vista.
	 */
	public void generarPalabra() {
		int numSilabas = vista.getNumSilabas();
		vista.setPalabra(modelo.generarPalabra(numSilabas));
	}

	/**
	 * Actualiza la vista con el historial de palabras generado por el modelo.
	 */
	public void setHistorial() {
		vista.setHistorial(modelo.getHistorial());
	}
}
