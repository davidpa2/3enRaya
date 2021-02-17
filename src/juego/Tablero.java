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
import javax.swing.JOptionPane;


public class Tablero extends Canvas {

	//Declaración de las distintas variables
	private List<CuadroDeJuego> cuadro = new ArrayList<CuadroDeJuego>();//Lista de cuadros de tipo CuadroDeJuego
	public static JFrame ventana = null;//JFrame declarado como null por el momento
	//Número que referencia a cada jugador
	public static int JUGADOR1 = 1;
	public static int JUGADOR2 = 2;
	private int turnoActual = JUGADOR1; //Turno por el que comienza el juego
	//Matriz con la que se va comprobando el estado del juego
	public static int matrizJugada[][] = new int[][] {{0,0,0},
													  {0,0,0},
													  {0,0,0}};
	
	//Creación de un patrón Singleton
	private static Tablero instance = null;
	
	public static Tablero getInstance() {
		if (instance == null) { // Si no está inicializada, se inicializa
			instance = new Tablero();
		}
		return instance;
	}
	
	/**
	 * El juego comienza generando los cuadros de juego
	 * @param args
	 */
	public static void main(String[] args) {
		Tablero.getInstance().crearCuadros();

	}

	/**
	 * Constructor de tablero
	 * @return 
	 */
	public Tablero() {
		ventana = new JFrame("TresEnRaya");
		
		// Para colocar objetos sobre la ventana se debe asignar un "layout" (plantilla) al panel principal de la ventana
		ventana.getContentPane().setLayout(new BorderLayout());
		//Crear los cuadros en los que se podrá poner una X o un O
		ventana.getContentPane().add(this, BorderLayout.CENTER);
		ventana.setBounds(0, 0, 600, 600);
		
		//Detectar cuando se hace clic sobre el tablero 
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
//				repaint();
				/*
				 * Si se hace click con el botón1 del ratón recorrer la lista de cuadros para saber en
				 * cual de ellos se ha hecho click 
				 */
				if(e.getButton() == MouseEvent.BUTTON1) {
					for(CuadroDeJuego c: cuadro) {
						if(c.detectarClick(e.getX(), e.getY())) {
							c.click(turnoActual); //Pasar al método click el jugador que lo ha hecho
							/*
							 * Tras cada click hay que comprobar si ha ganado un jugador u otro
							 * Si del método getGanadorDelTablero devuelve '-1', significa que hay empate
							 */
							if (c.getGanadorDelTablero() == -1) {
								empate();
							}else { //Si devuelve 1, gana el jugador 1
								if (c.getGanadorDelTablero() == 1) {
									ganadorJ1();
								}else { //Si devuelve 2, gana el jugador 2
									if (c.getGanadorDelTablero() == 2) {
										ganadorJ2();
									}
								}
							}
							//Intercambiar el turno de los jugadores una vez uno haya hecho click
							if (turnoActual == JUGADOR1) {
								turnoActual = JUGADOR2;							
							}else {
								turnoActual = JUGADOR1;
							}
							//System.out.println("Ganador: " + c.getGanadorDelTablero());
							mostrarMatriz();
						}
					}
				}
			}			
		});
		
		//Evitar que la ventana se repinte con los eventos de Windows
		ventana.setIgnoreRepaint(true);
		//Hacer que la ventana sea visible
		ventana.setVisible(true);
		//Hacer que la ventana tenga prioridad en el ratón
		this.requestFocus();
	}
	
	/**
	 * Método utilizado para crear los cuadros de juego
	 */
	private void crearCuadros() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				CuadroDeJuego c = new CuadroDeJuego(j, i); //Se generan en las coordenadas deseadas
				cuadro.add(c); //Añadir cada cuadro a la lista
				//System.out.println("Se ha creado cuadro");
			}
		}
	}
	
	/**
	 * Sobrescritura del método paint() ya que el canvas es el tablero 
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GRAY);//Color del cuadro gris
		g.fillRect(0, 0, this.getWidth(), this.getHeight()); //Establecer sus dimensiones según el ancho y el alto del canvas
		//Pintar todos los cuadros guardados en la lista de cuadros
		for(CuadroDeJuego c: this.cuadro) {
			c.paint(g);
		}

	}
	
	/**
	 * Método que muestra en la consola el estado del juego
	 */
	public void mostrarMatriz() {
		System.out.println("\nEstado del juego:");
		for (int i = 0; i < matrizJugada.length; i++) {
			for (int j = 0; j < matrizJugada[0].length; j++) {
				System.out.print(matrizJugada[j][i] + "\t");
			}
			System.out.println("");
		}
	}
	
	/**
	 * En el caso de que no queden cuadros libres ocurre el empate
	 */
	private static void empate() {
		String [] opciones ={"Aceptar"};
		int eleccion = JOptionPane.showOptionDialog(ventana,"No quedan casillas libres, el juego ha terminado.","Salir del juego",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0); //Salir de la aplicación
		}
	}
	
	/**
	 * En el caso de que gane el jugador 1:
	 */
	private static void ganadorJ1() {
		String [] opciones ={"Aceptar"};
		int eleccion = JOptionPane.showOptionDialog(ventana,"Ha ganado el jugador 1 (Círculo).","Salir del juego",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0); //Salir de la aplicación
		}
	}
	
	/**
	 * En el caso de que gane el jugador 2:
	 */
	private static void ganadorJ2() {
		String [] opciones ={"Aceptar"};
		int eleccion = JOptionPane.showOptionDialog(ventana,"Ha ganado el jugador 2 (Cruz).","Salir del juego",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0); //Salir de la aplicación
		}
	}
	
	//Getter y setters
	public Canvas getCanvas() {
		return this;
	}

	public int[][] getMatrizJugada() {
		return matrizJugada;
	}
	
}

