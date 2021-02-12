package juego;

public class CuadroDeJuego {
	
	protected int x, y; // Coordenadas x e y del cuadro
	protected int ancho = 600 / 3, alto = 600 / 3; // ancho y alto que ocupa el cuadro en pantalla
	protected int xSuperior, ySuperior;

	public CuadroDeJuego() {
		
	}

	public CuadroDeJuego(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		xSuperior = x * Tablero.getInstance().getCanvas().getWidth();
		ySuperior = y * Tablero.getInstance().getCanvas().getHeight();
	}

	public void click(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
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

