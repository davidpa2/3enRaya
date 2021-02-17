package juego;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class CuadroDeJuego {
	//Para facilitar la instancia del Tablero, crear aquí una referencia
	private Tablero tablero = (Tablero) Tablero.getInstance();
	protected int x, y; // Coordenadas x e y del cuadro
	protected int ancho, alto; // ancho y alto que ocupa el cuadro en pantalla
	protected int xSuperior, ySuperior; //La x y la y de la parte superior de cada cuadro, servirá para poder pintar cada cuadro
	private int turnoJugador = 0;

	/**
	 * Constructor por defecto
	 */
	public CuadroDeJuego() {	
	}

	/**
	 * Constructor con todos los parámetros con el que se creará cada cuadro 
	 * @param x
	 * @param y
	 */
	public CuadroDeJuego(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		//Calcular el tamaño de un cuadro dividiendo tanto anchura como altura entre tres
		this.ancho = Tablero.getInstance().getWidth()/3;  
		this.alto = Tablero.getInstance().getHeight()/3;
		xSuperior = this.x * this.ancho;
		ySuperior = this.y * this.alto;
		
	}
	
	/**
	 * Método paint con el que se pintará cada cuadro
	 * @param g
	 */
	public void paint(Graphics g) {	
		g.setColor(Color.BLACK);
		//System.out.println("Se esta pintando");
		g.drawRect(this.xSuperior, this.ySuperior, this.ancho, this.alto);
		
		pintarCirculoCruz(g);
	}
	
	/**
	 * Comprobar el turno del jugador para pintar una cruz o un círculo
	 * @param g
	 */
	private void pintarCirculoCruz(Graphics g) {
		//Si el turno es del jugador 1, se pintará un círculo
		if(this.turnoJugador == Tablero.JUGADOR1){
			g.setColor(Color.BLUE);
			g.drawOval(this.xSuperior + 10, this.ySuperior + 10, this.ancho - 20, this.alto - 20);
		}
		//Si es del jugador 2, se pintará una cruz	
		if(this.turnoJugador == Tablero.JUGADOR2) {
			g.setColor(Color.RED);
			g.drawLine(this.xSuperior + 10, this.ySuperior + 10, 
					this.xSuperior + this.ancho - 10, this.ySuperior + this.alto - 10);
			g.drawLine(this.xSuperior + 10, this.ySuperior + this.alto - 10, 
					this.xSuperior + this.ancho - 10, this.ySuperior + 10);
		}
	}
		
	/**
	 * Detectar en qué posición del canvas se ha hecho click para saber en qué cuadro fue
	 * @param clickX
	 * @param clickY
	 * @return
	 */
	public boolean detectarClick(int clickX, int clickY) {
		
		if (clickX > this.xSuperior && clickX < (xSuperior + ancho)
				&& 
				clickY > this.ySuperior && clickY < (ySuperior + alto)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Con el método click se decidirá a que jugador le toca colocar círculo o cruz
	 * @param jugador
	 */
	public void click(int jugador) {
		//En el primer turno hay que comprobar qué turno está seleccionado como primero
		if (this.turnoJugador == 0) {
			this.turnoJugador = jugador;
		}
		//Si un jugador ha hecho click en una casilla no ocupada, se pasará el turno al siguiente
		if (Tablero.matrizJugada[x][y] == 0) {
			Tablero.matrizJugada[x][y] = jugador;
			tablero.repaint(); //Repintar el tablero para colocar la cruz o el círculo según el turno
			Tablero.getInstance().revalidate(); //Asegurar que se repinta
			
		/*
		 * Si se ha hecho click en una casilla ocupada se resta un turno, por tanto le tocaría al mismo
		 * jugador hasta que haga click en un cuadro disponible  
		 */
		}else { 
			turnoJugador --;
		}
		
//		System.out.println("Juego:");
//		for (int i = 0; i < Tablero.getInstance().getMatrizJugada().length; i++) {
//			for (int j = 0; j < Tablero.getInstance().getMatrizJugada()[i].length; j++) {
//				System.out.print(Tablero.getInstance().getMatrizJugada()[i][j] + "\t");
//			}
//			System.out.println("");
//		}
	}
	
	/**
	 * Determina si existe un ganador en el juego.
	 * @return Devuelve: 1 si ha ganado el jugador 1; 2 si ha ganado el jugador 2; 
	 * 0 si no hay ganador y quedan casillas libres; -1 si no hay ganador y no quedan casillas libres
	 */
	public int getGanadorDelTablero () {
		// Busco si existe un ganador mirando las filas
		for (int i = 0; i < Tablero.matrizJugada.length; i++) {
			if (Tablero.matrizJugada[i][0] == Tablero.matrizJugada[i][1] && Tablero.matrizJugada[i][1] == Tablero.matrizJugada[i][2]) { 
				return Tablero.matrizJugada[i][0]; // Si todos los valores de una fila son iguales, 
										//devuelvo cualquiera de los elementos de esa fila
			}
		}
		// Busco si existe un ganador en las columnas
		for (int i = 0; i < Tablero.matrizJugada[0].length; i++) {
			if (Tablero.matrizJugada[0][i] == Tablero.matrizJugada[1][i] && Tablero.matrizJugada[1][i] == Tablero.matrizJugada[2][i]) { 
				return Tablero.matrizJugada[0][i]; // Si todos los valores de una columna son iguales, 
										//devuelvo cualquiera de los elementos de esa columna
			}
		}
		// Busco un ganador en la diagonal principal
		if (Tablero.matrizJugada[0][0] == Tablero.matrizJugada[1][1] && Tablero.matrizJugada[1][1] == Tablero.matrizJugada[2][2]) { 
			return Tablero.matrizJugada[0][0]; // Devuelvo cualquier elemento de la diagonal principal
		}
		// Busco un ganador en la diagonal secundaria
		if (Tablero.matrizJugada[0][2] == Tablero.matrizJugada[1][1] && Tablero.matrizJugada[1][1] == Tablero.matrizJugada[2][0]) {
			return Tablero.matrizJugada[0][2]; // Devuelvo cualquier elemento de la diagonal secundaria
		}
		
		/*
		 *  Si llegó hasta aquí no hay ganador, pero aún quedan dos posibilidades: puede que queden 
		 *  casillas vacías o puede que no
		 *  Voy a suponer que no hay casillas vacías y voy a recorrer el tablero buscando alguna casilla vacía
		 */
		boolean hayCasillasVacias = false;
		for (int i = 0; i < Tablero.matrizJugada.length; i++) {
			for (int j = 0; j < Tablero.matrizJugada[0].length; j++) {
				if (Tablero.matrizJugada[i][j] == 0) {
					hayCasillasVacias = true;
				}
			}
		}

		// Devuelvo valores diferentes dependiendo de si existen casillas vacías o no
		if (hayCasillasVacias == true) {
			return 0; // Indica que el juego continúa.
		}
		else {
			return -1; // Indica que no quedan casillas vacías, hay empate
		}
	}

	/**
	 * To String utilizado para depurar el código
	 */
	@Override
	public String toString() {
		return "CuadroDeJuego [xSuperior=" + x + ", ySuperior=" + y + "]";
	}

	//Getters y setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
	
}
