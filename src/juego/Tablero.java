package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Tablero extends Canvas {

	private JFrame ventana = null;
	private List<CuadroDeJuego> cuadro = new ArrayList<CuadroDeJuego>();
	
	//Creación de un patrón Singleton
	private static Tablero instance = null;
	
	public static Tablero getInstance() {
		if (instance == null) { // Si no está inicializada, se inicializa
			instance = new Tablero();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		Tablero.getInstance().juego();

	}

	/**
	 * Constructor de tablero
	 * @return 
	 */
	public Tablero() {
		//Crear una nueva ventana con su nombre y dimensiones
		ventana = new JFrame("Tres en raya");
		ventana.setBounds(0, 0, 600, 600);
		
		// Para colocar objetos sobre la ventana se debe asignar un "layout" (plantilla) al panel principal de la ventana
		ventana.getContentPane().setLayout(new BorderLayout());
		//Crear los cuadros en los que se podrá poner una X o un O
		//cuadro = crearCuadros();
	
		
		//Detectar cuando se hace clic sobre el tablero 
	/*	canvas.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseClicked(e);
				cuadro.
			}			
		});*/
		
		ventana.getContentPane().add(this, BorderLayout.CENTER);
		//Evitar que la ventana se repinte con los eventos de Windows
		ventana.setIgnoreRepaint(true);
		//Hacer que la ventana sea visible
		ventana.setVisible(true);
	}
	
	/**
	 * Método principal del juego
	 */
	public void juego() {
		
			this.repaint();
	}
	
	private List<CuadroDeJuego> crearCuadros() {
		List<CuadroDeJuego> cuadro = new ArrayList<CuadroDeJuego>();
		
		/*Calcular cuanto vale un cuadro ya que el tablero es 3x3, al dividir entre 3 el ancho y el alto
		 * obtenemos cuanto vale 1
		 */
	//	int xCuadro = canvas.getWidth()/3;
		//int yCuadro = canvas.getHeight()/3;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
		//		CuadroDeJuego c = new CuadroDeJuego(xCuadro*(i+1), yCuadro*(j+1));
	//			cuadro.add(c);
			}
		}
		
		// Devuelvo la lista con todos los cuadros del tablero
		return cuadro;
	}
	
	/**
	 * Sobrescritura del méotodo paint() ya que el canvas es el tablero 
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(185, 0, 15, 600);
		g.fillRect(385, 0, 15, 600);
		g.fillRect(0, 185, 600, 15);
		g.fillRect(0, 385, 600, 15);
	}
	
	//Getter y setters
	public Canvas getCanvas() {
		return this;
	}

}

