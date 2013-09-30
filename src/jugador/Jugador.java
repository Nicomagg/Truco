package jugador;

import java.util.Arrays;

import Elementos.Carta;

public abstract class Jugador {
	private String nombre;
	private Carta[] cartas;
	private boolean mano = false;
	private int manoGanada = 0;
	
	public Jugador(String nom){
		this.setNombre(nom);
		this.setManoGanada(0);
	}
	
	public Carta[] getCartas(){
		return(cartas);
	}
	
	public void setCartas(Carta[] car){
		this.cartas = car;
	}
	
	public void setCarta(Carta car, int posi){
		this.cartas[posi] = car;
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
	
	@Override
	public String toString() {
		return (Arrays.toString(cartas));
	}

	//Funcion que verifica si es la maquina o el jugador humano
	public abstract boolean isHumano();

	public int getManoGanada() {
		return manoGanada;
	}

	public void setManoGanada(int manoGanada) {
		this.manoGanada = this.getManoGanada() + manoGanada;
	}
	
	//funcion para ver si gano la mano
	public boolean ganoMano(){
		if(this.getManoGanada()==2){
			return true;
		}else{
			return false;
		}
	}
	
}
