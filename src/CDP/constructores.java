package CDP;

import java.util.Random;

public class constructores {

	char consonantes() {
		String consonantes = "bdcfghjklmnñpqrstvwxyz";
		StringBuilder consonante = new StringBuilder(consonantes);
		Random numAleatorio = new Random();
		// Una sola consonante
		int consonanteAleatoria = numAleatorio.nextInt(22);
		return consonante.charAt(consonanteAleatoria);
	}

	StringBuilder vocales() {
		String vocales = "aeiou";
		StringBuilder vocal = new StringBuilder();
		Random numAleatorio = new Random();
		int numVocales = numAleatorio.nextInt(4);
		if (numVocales != 0) {
			// Una sola vocal
			int vocalAleatoria = numAleatorio.nextInt(5);
			vocal.append(vocales.charAt(vocalAleatoria));
		} else if (numVocales == 0) {
			// Dos vocales
			for (int i = 0; i < 2; i++) {
				int vocalAleatoria = numAleatorio.nextInt(5);
				vocal.append(vocales.charAt(vocalAleatoria));
			}
		}
		return vocal;
	}
}
