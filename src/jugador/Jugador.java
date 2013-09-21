package jugador;

import Elementos.Carta;

public abstract class Jugador {
	private String nombre;
	private Carta[] cartas;
	private boolean mano = false;
	
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

	public boolean isMano() {
		return mano;
	}

	public void setMano(boolean mano) {
		this.mano = mano;
	}
	
	//Funcion que verifica si es la maquina o el jugador humano
	public abstract boolean isHumano();
	
}
