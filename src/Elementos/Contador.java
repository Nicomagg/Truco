package Elementos;

import jugador.Jugador;

public class Contador {
	private int puntosJug;
	private int puntosMaq;
	
	public Contador(){
		setPuntosJug(0);
		setPuntosMaq(0);
	}
	
	public int getPuntosJug() {
		return puntosJug;
	}
	
	public void setPuntosJug(int puntosJ1) {
		this.puntosJug = puntosJ1;
	}
	
	public int getPuntosMaq() {
		return puntosMaq;
	}
	
	public void setPuntosMaq(int puntosJ2) {
		this.puntosMaq = puntosJ2;
	}
	
	//Funcion que determina cuantos puntos se debe asignar por la falta. Si esta en buenas o malas.
	private int puntosParaAsignarFalta(){
		if((this.getPuntosJug() > 15) || (this.getPuntosMaq() > 15)){
			if(this.getPuntosJug() < this.getPuntosMaq()){
				return (this.getPuntosMaq() - 30);
			}else{
				return (this.getPuntosJug() - 30);
			}
		}else{
			if(this.getPuntosJug() < this.getPuntosMaq()){
				return (this.getPuntosMaq() - 15);
			}else{
				return (this.getPuntosJug() - 15);
			}
		}
	}
	
	//Funcion que asigna puntos para la falta.
	public void sumarFaltaEnvido(Jugador jug){
		if(jug.isHumano()){
			this.setPuntosJug(this.getPuntosJug() + this.puntosParaAsignarFalta());
		}else{
			this.setPuntosMaq(this.getPuntosMaq() + this.puntosParaAsignarFalta());
		}
	}
	
	//Funcion que suma puntos
	public void sumarPuntos(Jugador jug, int puntos){
		if(jug.isHumano()){
			this.setPuntosJug(puntos);
		}else{
			this.setPuntosMaq(puntos);
		}
	}
	
	//funcion para saber si alguien gano
	public boolean hayGanador(){
		if((this.getPuntosJug()>=30)||(this.getPuntosMaq()>=30)){
			return true;
		}else{
			return false;
		}
	}
	
	//funcion que suma puntos si no se quiere la falta envido
	public int faltaEnvidoNoQuerida(boolean envido, boolean envidoEnvido, boolean realEnvido){
		int puntosGanados;
		if(envido){
			if(realEnvido){
				puntosGanados = 5;
			}else{
				puntosGanados = 2;
			}
		}else if(envidoEnvido){
			if(realEnvido){
				puntosGanados = 7;
			}else{
				puntosGanados = 4;
			}
		}else if(realEnvido){
			puntosGanados = 3;
		}else{
			puntosGanados = 1;
		}
		return puntosGanados;
	}
	
	//funcion que suma puntos si no se quiere el real envido
	public int realEnvidoNoQuerido(boolean envido, boolean envidoEnvido){
		int puntosGanados;
		if(envido){
			puntosGanados = 2;
		}else if(envidoEnvido){
			puntosGanados = 4;
		}else{
			puntosGanados = 1;
		}
		return puntosGanados;
	}
	
	//Funcion que retorna los puntos a sumar para el que gana el real envido
	public int realEnvioQuerido(boolean envido, boolean envidoEnvido){
		int puntosGanados;
		if(envido){
			puntosGanados = 5;
		}else if(envidoEnvido){
			puntosGanados = 7;
		}else{
			puntosGanados = 3;
		}
		return puntosGanados;
	}
	
	//funcion que retorna los puntos a sumar para si se quiere el envido o envidoEnvido
	public int envidoNoQuerido(boolean envido){
		int puntosGanados;
		if(envido){
			puntosGanados = 2; 
		}else{
			puntosGanados = 1;
		}
		return puntosGanados;
	}
	
	//funcion que retirna los puntos para un envido querido
	public int envidoQuerido(boolean envido){
		int puntosGanados;
		if(envido){
			puntosGanados=4;
		}else{
			puntosGanados = 2;
		}
		return puntosGanados;
	}
}
