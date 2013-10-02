package jugador;

import java.util.ArrayList;
import java.util.Scanner;

import Elementos.Carta;
import Elementos.Contador;

public class Humano extends Jugador{

	private Scanner sc;

	public Humano(String nom) {
		super(nom);
		this.setMano(true);
	}
	
	@Override
	public boolean isHumano(){
		return true;
	}
	
	//FUncionpara obtener cartas
	public void obtenerCartas(Carta[] mezcla){
		int j = 0;
		Carta[] cartas = new Carta[3];
		for(int i = 0; i<6; i++){
			if((i%2)==0){
				cartas[j]=mezcla[i];
				j++;
			}
		}
		this.setCartas(cartas);
	}
	
	public int obtenerPutnos(){
		sc = new Scanner(System.in);
		System.out.println("Ingrese sus puntos: ");
		int num = sc.nextInt();
		return num;
	}
	
	//funcion para que el humano cante falta envido
	public void cantarFaltaEnvido(boolean envido, boolean envidoEnvido, boolean realEnvido, Maquina jugadorM, Contador contador, boolean mentir){
		System.out.println("\n"+this.getNombre()+": Falta Envido");
		//condicion que verifica si la maquina quiere o no
		if(jugadorM.cantoFaltaEnvido(envido, envidoEnvido, realEnvido, mentir, contador)){
			System.out.println("\n"+jugadorM.getNombre()+": Quiero");
			int puntosHumano = this.obtenerPutnos();
			int puntosMaquina = jugadorM.puntosMano();
			System.out.println("\n"+this.getNombre()+": "+puntosHumano+" puntos");
			System.out.println("\n"+jugadorM.getNombre()+": "+puntosMaquina+" puntos");
			if(puntosHumano < puntosMaquina){
				contador.sumarFaltaEnvido(jugadorM);
			}else if(puntosHumano > puntosMaquina){
				contador.sumarFaltaEnvido(this);
			}else{
				if(this.isMano()){
					contador.sumarFaltaEnvido(this);
				}else{
					contador.sumarFaltaEnvido(jugadorM);
				}
			}
		}else{
			contador.sumarPuntos(this, contador.faltaEnvidoNoQuerida(envido, envidoEnvido, realEnvido));
		}
	}
	
	//funcion para ver que hace el humano cuando la maquina le canta falta envido
	public boolean cantoFaltaEnvido(){
		boolean error = true;
		int respuesta = 0;
		while(error){
			System.out.print("\n1-Quiero  --  2-No Quiero");
			try{
				respuesta = sc.nextInt();
				if((respuesta<1)&&(respuesta>2)){
					System.out.println("\nError, el valor ingresado no se corresponde al rango dado");
				}else{
					error = false;
				}
			}catch(Exception e){
				System.out.println("\nError, el valor ingresado no es número");
			}
		}		
		if(respuesta == 1){
			return true;
		}else{
			return false;
		}
	}
	
	//funcion para que el humano cante real Envido
	public void realEnvido(boolean envido, boolean envidoEnvido, Maquina jugadorM, boolean mentira, Contador contador){
		System.out.println("\n"+this.getNombre()+": Real Envido");
		ArrayList<Boolean> respuestaMaquina = new ArrayList<Boolean>();
		respuestaMaquina = jugadorM.cantoRealEnvido(envido, envidoEnvido, contador, mentira, this);
		//Condicional para ver si la maquina canto falta envido
		if(!(respuestaMaquina.get(1))){
			//Condicional para ver si quiso la maquina o no
			if(respuestaMaquina.get(0)){
				System.out.println("\n"+jugadorM.getNombre()+": Quiero");
				int puntosHumano = this.obtenerPutnos();
				int puntosMaquina = jugadorM.puntosMano();
				System.out.println("\n"+this.getNombre()+": "+puntosHumano+" puntos");
				System.out.println("\n"+jugadorM.getNombre()+": "+puntosMaquina+" puntos");
				if(puntosHumano<puntosMaquina){
					contador.sumarPuntos(jugadorM, contador.realEnvioQuerido(envido, envidoEnvido));
				}else if(puntosHumano>puntosMaquina){
					contador.sumarPuntos(this, contador.realEnvioQuerido(envido, envidoEnvido));
				}else{
					if(this.isMano()){
						contador.sumarPuntos(this, contador.realEnvioQuerido(envido, envidoEnvido));
					}else{
						contador.sumarPuntos(jugadorM, contador.realEnvioQuerido(envido, envidoEnvido));
					}
				}
			}else{
				contador.sumarPuntos(this,contador.realEnvidoNoQuerido(envido, envidoEnvido));
			}
		}
	}

}
