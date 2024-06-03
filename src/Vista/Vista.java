package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Base64;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import Controlador.Controlador;

/**
 * La clase Vista representa la interfaz gráfica de usuario para el generador de
 * palabras.
 */
public class Vista extends JFrame {
	private Controlador controlador;
	private JPanel mainPanel;
	private JLabel lblNumSilabas;
	private JSpinner spinnerNumSilabas;
	private JLabel lblSubTitle2;
	private JButton btnGenerar;
	private JPanel generationPanel;
	private JPanel historialPanel;
	private JLabel lblTitle;
	private JLabel lblSubTitle3;
	private JTextArea textArea;
	private JButton btnCopiar;
	private JPanel palabraPanel;
	private JLabel lblPalabra;
	private JScrollPane scrollPanePalabra;

	/**
	 * Constructor de la clase Vista. Configura la interfaz de usuario.
	 */
	public Vista() {
		// Configuración de la ventana
		setTitle("Generador de Palabras");
		setBounds(800, 200, 500, 305);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Crea el icono de la apliación a partir de un codigo en base 64
		ImageIcon iconoApp = new ImageIcon(Base64.getDecoder().decode(
				"iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAABGdBTUEAALGPC/xhBQAACklpQ0NQc1JHQiBJRUM2MTk2Ni0yLjEAAEiJnVN3WJP3Fj7f92UPVkLY8LGXbIEAIiOsCMgQWaIQkgBhhBASQMWFiApWFBURnEhVxILVCkidiOKgKLhnQYqIWotVXDjuH9yntX167+3t+9f7vOec5/zOec8PgBESJpHmomoAOVKFPDrYH49PSMTJvYACFUjgBCAQ5svCZwXFAADwA3l4fnSwP/wBr28AAgBw1S4kEsfh/4O6UCZXACCRAOAiEucLAZBSAMguVMgUAMgYALBTs2QKAJQAAGx5fEIiAKoNAOz0ST4FANipk9wXANiiHKkIAI0BAJkoRyQCQLsAYFWBUiwCwMIAoKxAIi4EwK4BgFm2MkcCgL0FAHaOWJAPQGAAgJlCLMwAIDgCAEMeE80DIEwDoDDSv+CpX3CFuEgBAMDLlc2XS9IzFLiV0Bp38vDg4iHiwmyxQmEXKRBmCeQinJebIxNI5wNMzgwAABr50cH+OD+Q5+bk4eZm52zv9MWi/mvwbyI+IfHf/ryMAgQAEE7P79pf5eXWA3DHAbB1v2upWwDaVgBo3/ldM9sJoFoK0Hr5i3k4/EAenqFQyDwdHAoLC+0lYqG9MOOLPv8z4W/gi372/EAe/tt68ABxmkCZrcCjg/1xYW52rlKO58sEQjFu9+cj/seFf/2OKdHiNLFcLBWK8ViJuFAiTcd5uVKRRCHJleIS6X8y8R+W/QmTdw0ArIZPwE62B7XLbMB+7gECiw5Y0nYAQH7zLYwaC5EAEGc0Mnn3AACTv/mPQCsBAM2XpOMAALzoGFyolBdMxggAAESggSqwQQcMwRSswA6cwR28wBcCYQZEQAwkwDwQQgbkgBwKoRiWQRlUwDrYBLWwAxqgEZrhELTBMTgN5+ASXIHrcBcGYBiewhi8hgkEQcgIE2EhOogRYo7YIs4IF5mOBCJhSDSSgKQg6YgUUSLFyHKkAqlCapFdSCPyLXIUOY1cQPqQ28ggMor8irxHMZSBslED1AJ1QLmoHxqKxqBz0XQ0D12AlqJr0Rq0Hj2AtqKn0UvodXQAfYqOY4DRMQ5mjNlhXIyHRWCJWBomxxZj5Vg1Vo81Yx1YN3YVG8CeYe8IJAKLgBPsCF6EEMJsgpCQR1hMWEOoJewjtBK6CFcJg4Qxwicik6hPtCV6EvnEeGI6sZBYRqwm7iEeIZ4lXicOE1+TSCQOyZLkTgohJZAySQtJa0jbSC2kU6Q+0hBpnEwm65Btyd7kCLKArCCXkbeQD5BPkvvJw+S3FDrFiOJMCaIkUqSUEko1ZT/lBKWfMkKZoKpRzame1AiqiDqfWkltoHZQL1OHqRM0dZolzZsWQ8ukLaPV0JppZ2n3aC/pdLoJ3YMeRZfQl9Jr6Afp5+mD9HcMDYYNg8dIYigZaxl7GacYtxkvmUymBdOXmchUMNcyG5lnmA+Yb1VYKvYqfBWRyhKVOpVWlX6V56pUVXNVP9V5qgtUq1UPq15WfaZGVbNQ46kJ1Bar1akdVbupNq7OUndSj1DPUV+jvl/9gvpjDbKGhUaghkijVGO3xhmNIRbGMmXxWELWclYD6yxrmE1iW7L57Ex2Bfsbdi97TFNDc6pmrGaRZp3mcc0BDsax4PA52ZxKziHODc57LQMtPy2x1mqtZq1+rTfaetq+2mLtcu0W7eva73VwnUCdLJ31Om0693UJuja6UbqFutt1z+o+02PreekJ9cr1Dund0Uf1bfSj9Rfq79bv0R83MDQINpAZbDE4Y/DMkGPoa5hpuNHwhOGoEctoupHEaKPRSaMnuCbuh2fjNXgXPmasbxxirDTeZdxrPGFiaTLbpMSkxeS+Kc2Ua5pmutG003TMzMgs3KzYrMnsjjnVnGueYb7ZvNv8jYWlRZzFSos2i8eW2pZ8ywWWTZb3rJhWPlZ5VvVW16xJ1lzrLOtt1ldsUBtXmwybOpvLtqitm63Edptt3xTiFI8p0in1U27aMez87ArsmuwG7Tn2YfYl9m32zx3MHBId1jt0O3xydHXMdmxwvOuk4TTDqcSpw+lXZxtnoXOd8zUXpkuQyxKXdpcXU22niqdun3rLleUa7rrStdP1o5u7m9yt2W3U3cw9xX2r+00umxvJXcM970H08PdY4nHM452nm6fC85DnL152Xlle+70eT7OcJp7WMG3I28Rb4L3Le2A6Pj1l+s7pAz7GPgKfep+Hvqa+It89viN+1n6Zfgf8nvs7+sv9j/i/4XnyFvFOBWABwQHlAb2BGoGzA2sDHwSZBKUHNQWNBbsGLww+FUIMCQ1ZH3KTb8AX8hv5YzPcZyya0RXKCJ0VWhv6MMwmTB7WEY6GzwjfEH5vpvlM6cy2CIjgR2yIuB9pGZkX+X0UKSoyqi7qUbRTdHF09yzWrORZ+2e9jvGPqYy5O9tqtnJ2Z6xqbFJsY+ybuIC4qriBeIf4RfGXEnQTJAntieTE2MQ9ieNzAudsmjOc5JpUlnRjruXcorkX5unOy553PFk1WZB8OIWYEpeyP+WDIEJQLxhP5aduTR0T8oSbhU9FvqKNolGxt7hKPJLmnVaV9jjdO31D+miGT0Z1xjMJT1IreZEZkrkj801WRNberM/ZcdktOZSclJyjUg1plrQr1zC3KLdPZisrkw3keeZtyhuTh8r35CP5c/PbFWyFTNGjtFKuUA4WTC+oK3hbGFt4uEi9SFrUM99m/ur5IwuCFny9kLBQuLCz2Lh4WfHgIr9FuxYji1MXdy4xXVK6ZHhp8NJ9y2jLspb9UOJYUlXyannc8o5Sg9KlpUMrglc0lamUycturvRauWMVYZVkVe9ql9VbVn8qF5VfrHCsqK74sEa45uJXTl/VfPV5bdra3kq3yu3rSOuk626s91m/r0q9akHV0IbwDa0b8Y3lG19tSt50oXpq9Y7NtM3KzQM1YTXtW8y2rNvyoTaj9nqdf13LVv2tq7e+2Sba1r/dd3vzDoMdFTve75TsvLUreFdrvUV99W7S7oLdjxpiG7q/5n7duEd3T8Wej3ulewf2Re/ranRvbNyvv7+yCW1SNo0eSDpw5ZuAb9qb7Zp3tXBaKg7CQeXBJ9+mfHvjUOihzsPcw83fmX+39QjrSHkr0jq/dawto22gPaG97+iMo50dXh1Hvrf/fu8x42N1xzWPV56gnSg98fnkgpPjp2Snnp1OPz3Umdx590z8mWtdUV29Z0PPnj8XdO5Mt1/3yfPe549d8Lxw9CL3Ytslt0utPa49R35w/eFIr1tv62X3y+1XPK509E3rO9Hv03/6asDVc9f41y5dn3m978bsG7duJt0cuCW69fh29u0XdwruTNxdeo94r/y+2v3qB/oP6n+0/rFlwG3g+GDAYM/DWQ/vDgmHnv6U/9OH4dJHzEfVI0YjjY+dHx8bDRq98mTOk+GnsqcTz8p+Vv9563Or59/94vtLz1j82PAL+YvPv655qfNy76uprzrHI8cfvM55PfGm/K3O233vuO+638e9H5ko/ED+UPPR+mPHp9BP9z7nfP78L/eE8/stRzjPAAAAIGNIUk0AAHomAACAhAAA+gAAAIDoAAB1MAAA6mAAADqYAAAXcJy6UTwAAAAJcEhZcwAACxMAAAsTAQCanBgAAALxSURBVGiB7ZrPSzJBHMafWa3th5pQHorAwC7rwVtHT4JQKkLQIeoUeffWoYvQXfobQtyD0CHypDfDgxCI5N6qmx4L7FK2O+9JIXnfZnac11XwAwu7y/c78zyzzo+dlSiKQiORCGaRVqsFomkaNQzDaS1ChMNhKE6LGJe5AaeZeQNuuwnVahWlUgnNZhO9Xg+KIq8NLMvC0tISotEorq6u4PP52EmaplEePj4+aCKRoAAmcmxtbdG3t7dfNWmaRrmar9/vIxKJoFwu8zWlBDqdDq6vr5lxXAbOz8/x8vIyribbtNttZgzTwPPzM25ubqQIssvi4iIzhmng9vZWihgReAYIZkS325Ui5n/BNGCa5iR0CMM04HK5JqFDGNsT2d/IZDK4uLiA2+0GpZQrhxACSinq9TpOTk6E65ZiYH9/H6FQSCh3Z2cHp6en3MZHkWLg6OgIh4eHWFhYsJ378PAgLB6QZMA0TZRKJRlF2UaKAZ/Ph7OzM6iqCsuyuHIGfaBSqaDZbArXLcWArus4ODgQzlcUxdk+UCgUsL6+bmsUGtBoNJzvA7quQ9d1GUXZZubfyOYGnGZuwGmm2gDPpDjVBr6+vpgxU21ge3ubGTO1BjweD7LZLDNurJlYZOnwLwgh+P7+Hl5ns1kEg0G2BpHKUqkU8vk8AoGAVAOxWAyPj48A+LZUAA4Doy/1Xq8Xd3d3AhLZrKysDM8/Pz+5cph9YHRvZmNjw6YsfnhGnVGYBgKBwI/r19dX3N/f266Ih9XV1eG5qqpcOcyfUDqdxuXl5Y97qVQKx8fH2N3dhWVZY/cDQggIIXh6ehre6/f7fMk82+vJZHJi2+qDY3l5mdZqNeb2OpeB9/d36vf7J24iHo/L+T6wtrYGwzCwt7fH91glwVMf9zywubmJRqOBYrGIYrEIwzDQ6/Wkbz2apglVVZFMJpHL5ZjxY33otiwLhBCh3N/K5G2UcDg83lJC5ge+AXaf6NQu5niZG3AaMut/t/kD5dX5oT4UFxMAAAAASUVORK5CYII="));
		setIconImage(iconoApp.getImage());

		// Panel principal
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		// Crea el icono del historial a partir de un codigo en base 64
		ImageIcon iconoHistorialOg = new ImageIcon(Base64.getDecoder().decode(
				"iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAAXNSR0IArs4c6QAABH1JREFUaEPtmVuIVlUUx3+jpUi9pCimJeblSX1RLIWEIqSLgUqhKHgHUTRfJAIVTSQRu/ig9uKF8AIqVFoiqE9qiWZeyiK0CLto9pASJHnrsv/DObK+Pef7zt7nfDPDyLdg5mHO2muv/173NU10cGrq4PrTANDeFmxY4H60wBPAM8CTwCCgL/BQAvQv4DLwPfAFcAS4VOYR6uVCDwDjnTIzgRGRCn0JbHXnPgX+iTxblyz0HLAK6B97ucf/A7AMOBojp4wFugGrgckxFwbw7gCWAzcDeAtboCewExiaccl/wHngIHAS+B34LeHrA/QCRgHPA8OqKHkOmAb8kQeiiAWk/MfAAE+4FJcfrwV+zLs4+T4QeAMYBy0eUy41MQ9ELAC5zb6Ml1cmWeAC+Wyg4j6bAn+jC+Z+3gdZQiBuVZMbC2Bdhs9/DswFrhdUPj3W3aXcTc71RntytidWyhQfA0DZRsIsSfmpwJ2SyqfHHwR2ZYBQojiWdUcoAOV5pTebKuU28t2yL+/rJUsc8NxJ8fBsVp0IBfAKsN7cpIB9OdDnr5hzykIhpJj4xAvs+Un8VZwPBbDfCRxuTkr4vBBNXHwUASDRigdZOKVTSbWPBiC3Oe69/piIVFkUgPooW5VldQX4zxZBiAVmAW+ZQ1+5AvVi4OuLrSgAnT3kpWzVjIpEEgLgfWCCUfhtQOk0lMoAWAzoJ6WPgIWxFjgMDDGHXvVcKg9IGQBPA3vMBWpR1ILcoxALfAs8Ys5IaGirUNaF1GrY/K/eqKJ/CgHwk4sBFZiUBgM38p7dfC9jAQ1CGn5Suu237RaA728ROvIuNP9kkQWgtBjTLz3sKv3FUADiKwKilvK+C/0L7E7miNxW2WWcQi4UAyJPeQEY6yrqSs/0f7o5Wdlsm5vC7tYwtR/EXwMvhARxCIgQ5dO7urjBRq3AIkAteUrfuUZwqVPqRBUQvh4fuvngtRAAee4Uo7y9TxuKFUkflf5dFVYdqM336Tc/hUcXsixLvAe8ExPhGbxyDVV3ZTSR5EmuJX3T2sUC1Sj6S6gFUj5NWjKzSFOTbSvK4FCLrjXMlMQif3vCtngti/ZItiNoZg+pA+J73Zlepi778lmAO7kthLKTpZHAXk8/db/qgisoFID4BKAtqEcy0DxuLlMt0ETYYvEVCqCW4p2LbNSqCFTFV+/zlPd9EvBZ1pmyABTkmg3m5K0/Akynl9+cofwHwJJq58sAsBlKmUFtrqamIiSf3wBYt5Gc04C637qtVVLl/CEn/buG8TWAhvAQUqpUbn8pg1lNnHZC12oJKmoBtdeajOycbO/5xqwWr5rV4qNA78RN1BLYOcOeP+Na9ul5yutAUQA629X9ehOYEfLUETzyefVOVd3GyioDIJXjV9UIXStYLyTrdS3LgqkeAHSZipH2RLMBBWSoXNUWBb7+waHVjV/QcoGEXpQryDBoQZv+i0lB+pir4BpMpKz+xfRrMmVp9a5ep6K3ibmobAzE3tUq/K1hgVZRtJrQBoA2fe6MyxoWaG8L/A9X/sgxCTz4fQAAAABJRU5ErkJggg=="));
		// Icono para alternar entre historial y generación
		ImageIcon iconoHistorial = new ImageIcon(
				iconoHistorialOg.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

		// Etiqueta para cambiar entre historial y generación
		JLabel lblHistorial = new JLabel("");
		lblHistorial.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (generationPanel.isVisible()) {
					generationPanel.setVisible(false);
					historialPanel.setVisible(true);
					controlador.setHistorial();
				} else {
					generationPanel.setVisible(true);
					historialPanel.setVisible(false);
				}
			}
		});
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorial.setIcon(iconoHistorial);
		lblHistorial.setBounds(20, 221, 30, 30);
		mainPanel.add(lblHistorial);

		// Título de la ventana
		lblTitle = new JLabel("Generador de Palabras", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setBounds(100, 0, 283, 66);
		mainPanel.add(lblTitle);

		// Crea el icono del historial a partir de un codigo en base 64
		ImageIcon iconoCopiarOg = new ImageIcon(Base64.getDecoder().decode(
				"iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAAXNSR0IArs4c6QAAAg5JREFUaEPtmssuREEQhr+xsXDZur6BSKyInUsiEV7Bwt3Ku1gJwULEC2BHbFlKPILrllhYoWRMykzPmZ7uMuccOWd50lVdX1d3z1+npkTOn1LO48cFIO/WgCVgCOgwgnwDboFjYAd4t/BbDdAPHAGTFs4TfNwAc8B97DwaoA24ACZinXraXwLTwIfneOcwDbABbMc4C7BdB3YD7ComGuAaGFXOzoBl4DlmAmXbA+wB8+rdFTAe418DvAKdylkf8BTj3GErPh/Ue5mzO2YODVC9F//qijWdpwAISP8dMFC2k2t0MMCH8xCbpjYhKDnE8kMmj9xCp3kDiIm3xjaNM1AA6BUoMqBWIxUVa5WB1FSsBUCqKtYCIFUVawGQqoq1AEhVxVoAtEqCOOcpABw1bUvriH+VgRegS/2y9nrWw5k5A1JgjymAk7Jef2wgHzMDsAlsJQQrxbirAMkMQDsgWRhJgHCVgJkBkLilVj0EpupAZB5A4pabaQVYBIbVt6LMb6HQUi9TWygEogDwXLWGWsjTT82wIgOeK1dkoN5CFVso7S0UqmI94/4eJp9tdEOw0hixKD5CVawvgHR19oFZZVBpTVkANFKxvoE2M2613G9zNrqbcSRjfVRssz6Txp8DMz+lrEUGfFSsFYD0sRe+6pJKkWUFkKRiY4OXAyt/UTgon4Vf17YlQGygQfa5B/gEV/SNMXruXPIAAAAASUVORK5CYII="));
		// Icono para alternar entre historial y generación
		ImageIcon iconoCopiar = new ImageIcon(iconoCopiarOg.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		// Panel para la generación de palabras
		generationPanel = new JPanel();
		generationPanel.setBounds(0, 0, 484, 266);
		mainPanel.add(generationPanel);
		generationPanel.setLayout(null);

		// Etiqueta para seleccionar el número de sílabas
		lblNumSilabas = new JLabel("Número de Sílabas:", SwingConstants.CENTER);
		lblNumSilabas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumSilabas.setBounds(100, 88, 117, 27);
		generationPanel.add(lblNumSilabas);

		// Selector para el número de sílabas
		spinnerNumSilabas = new JSpinner();
		spinnerNumSilabas
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinnerNumSilabas.setBounds(288, 88, 95, 27);
		generationPanel.add(spinnerNumSilabas);

		// Subtítulo para la palabra generada
		lblSubTitle2 = new JLabel("La palabra es:");
		lblSubTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTitle2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubTitle2.setBounds(171, 136, 139, 21);
		generationPanel.add(lblSubTitle2);

		// Botón para generar una nueva palabra
		btnGenerar = new JButton("Generar");
		btnGenerar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.generarPalabra();
			}
		});
		btnGenerar.setBounds(115, 223, 253, 27);
		generationPanel.add(btnGenerar);

		btnCopiar = new JButton("");
		btnCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener el Clipboard y el Toolkit del sistema
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				// Copiar el texto al portapapeles creando un objeto tipo StringSelection
				clipboard.setContents(new StringSelection(lblPalabra.getText()), null);
			}
		});
		btnCopiar.setBounds(433, 159, 26, 26);
		btnCopiar.setIcon(iconoCopiar);
		generationPanel.add(btnCopiar);

		palabraPanel = new JPanel();
		palabraPanel.setBackground(new Color(255, 255, 255));
		palabraPanel.setBounds(54, 159, 375, 42);
		generationPanel.add(palabraPanel);
		palabraPanel.setLayout(null);

		scrollPanePalabra = new JScrollPane((Component) null);
		scrollPanePalabra.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanePalabra.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPanePalabra.setBounds(0, 0, 375, 42);
		palabraPanel.add(scrollPanePalabra);

		lblPalabra = new JLabel("", SwingConstants.CENTER);
		scrollPanePalabra.setViewportView(lblPalabra);
		lblPalabra.setFont(new Font("Tahoma", Font.BOLD, 14));

		// Panel para el historial
		historialPanel = new JPanel();
		historialPanel.setBounds(0, 0, 484, 266);
		mainPanel.add(historialPanel);
		historialPanel.setLayout(null);
		historialPanel.setVisible(false);

		// Panel para mostrar el texto del historial
		JPanel textPanel = new JPanel();
		textPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textPanel.setBackground(new Color(255, 255, 255));
		textPanel.setBounds(167, 59, 150, 175);
		historialPanel.add(textPanel);
		textPanel.setLayout(null);

		// Área de texto para el historial
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		JScrollPane scrollPaneHistorial = new JScrollPane(textArea);
		scrollPaneHistorial.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneHistorial.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneHistorial.setBounds(5, 31, 140, 140);
		textPanel.add(scrollPaneHistorial, BorderLayout.CENTER);

		// Subtítulo para el historial
		lblSubTitle3 = new JLabel("Historial");
		lblSubTitle3.setBounds(27, 8, 95, 14);
		textPanel.add(lblSubTitle3);
		lblSubTitle3.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTitle3.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}

	/**
	 * Establece el controlador para la vista.
	 * 
	 * @param controlador El controlador que se establecerá para la vista.
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	/**
	 * Obtiene el número de sílabas seleccionado por el usuario.
	 * 
	 * @return El número de sílabas seleccionado por el usuario.
	 */
	public int getNumSilabas() {
		return (Integer) spinnerNumSilabas.getValue();
	}

	/**
	 * Establece la palabra generada en la etiqueta correspondiente.
	 * 
	 * @param palabra La palabra generada que se mostrará en la vista.
	 */
	public void setPalabra(String palabra) {
		lblPalabra.setText(palabra);
	}

	/**
	 * Establece el historial de palabras en el área de texto correspondiente.
	 * 
	 * @param historial El historial de palabras que se mostrará en la vista.
	 */
	public void setHistorial(String historial) {
		textArea.setText(historial);
	}
}