package com.me.invaders;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class alien {
	public final static int IZQUIERDA = 0;
	public final static int DERECHA = 1;
	private float SPEED = 1.2f; // Velocidad que se va a ir moviendo el alien.
	private final float DES_SPEED = 20; // Velocidad de descenso del alien.
	
	private Vector2 posicion;
	private float anchura, altura;
	private Rectangle bordes;
	private int movimiento;
	private float margenIzquierdo; // Es el margen izquierdo que no puede pasar el alien.
	private float margenDerecho; // Es el margen derecho que no puede pasar el alien.
	
	public alien(Vector2 posicion, float anchura, float altura, float margenIzquierdo, float margenDerecho) {
		this.posicion = posicion;
		this.anchura = anchura;
		this.altura = altura;
		bordes = new Rectangle(posicion.x, posicion.y, anchura, altura);
		movimiento = DERECHA;
		this.margenIzquierdo = margenIzquierdo; // Es el margen izquierdo que no puede pasar el alien.
		this.margenDerecho = margenDerecho; // Es el margen derecho que no puede pasar el alien.
	}
	
	public void update() {
		if(movimiento == DERECHA) { 
			posicion.x = posicion.x + SPEED; // Avanza a la izquierda.
			if(posicion.x + anchura > margenDerecho) { // Si el alien esta en el margen derecho.
				movimiento = IZQUIERDA;
				posicion.y = posicion.y - DES_SPEED; // Descender
				SPEED = SPEED * 1.1f; // Aumentamos la velocidad.
				
			}
		}
		if(movimiento == IZQUIERDA) {
			posicion.x = posicion.x - SPEED; // Avanza a la derecha.
			if(posicion.x < margenIzquierdo) { // Si el alien esta en el margen izquierdo.
				movimiento = DERECHA;
				posicion.y = posicion.y - DES_SPEED; // Descender
				SPEED = SPEED * 1.1f; // Aumentamos la velocidad.
			}
		}
		
		// Actualizamos los bordes (la anchura y altura ya se definen en el constructor de Entidad)
		bordes.x = posicion.x;
		bordes.y = posicion.y;
	}
	
	public boolean Muerto(shot disparo) {
		return colisiona(bordes, disparo.getBordes());
	}
	
	private boolean colisiona(Rectangle a, Rectangle b) {
		return a.overlaps(b); // la funcion overlaps nos devuelve verdadero si dos rectangulos se solapan.
	}
	
	// Getters -----------------------------------

	public Vector2 getPosicion() {
		return posicion;
	}

	public float getAnchura() {
		return anchura;
	}

	public float getAltura() {
		return altura;
	}

	public Rectangle getBordes() {
		return bordes;
	}
}
