package jugador;

import Elementos.Carta;

public abstract class Jugador {
	private String nombre;
	private Carta[] cartas;
	
	public Jugador(String nom){
		this.setNombre(nom);
	}
	
	public Carta[] getCartas(){
		return(cartas);
	}
	
	public void setCartas(Carta[] car){
		this.cartas = car;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
