package Controlador;

import Modelo.Modelo;
import Vista.Vista;

public class Main {
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador();
		Vista vista = new Vista();

		modelo.setControlador(controlador);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		vista.setControlador(controlador);

		vista.setVisible(true);
	}
}