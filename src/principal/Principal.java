package principal;

import java.util.ArrayList;
import java.util.Scanner;

import jugador.Humano;
import jugador.Maquina;
import Elementos.Carta;
import Elementos.Contador;
import Elementos.Mazo;

public class Principal {

	
	public static void main(String[] args){
		System.out.println("TRUCO - by Nicolas Maggione \n");
		
		ArrayList<Carta> cartasJugadasHumano = new ArrayList<Carta>();
		ArrayList<Carta> cartasJugadasMaquina = new ArrayList<Carta>();
		Scanner sc = new Scanner(System.in);
		Contador contador = new Contador();
		Mazo mazo = new Mazo();
		Maquina jugadorM = new Maquina("Máquina");
		System.out.print("\nIngrese su nombre: ");
		String nom = sc.next();
		Humano jugadorH = new Humano(nom);
		ArrayList<Boolean> respuestaTruco;
		ArrayList<Boolean> respuestaReTruco;
		boolean respuestaVale4;
		boolean mentir;
		boolean error;
		boolean truco;
		boolean reTruco;
		boolean vale4;
		boolean envido;
		int resp = 0;
		int puntosFaltaHumanoGanar;
		int diferenciaPuntos;
		
		while(!(contador.hayGanador())){
			//Reparto de cartas para cada jugador
			jugadorH.obtenerCartas(mazo.mezclarMazo());
			jugadorM.obtenerCartas(mazo.mezclarMazo());
			
			//Inicializo las variales para el truco y envido
			envido = false;
			truco = false;
			reTruco = false;
			vale4 = false;
			
			//muestro cartas del jugador
			System.out.println("Cartas de "+jugadorH.getNombre()+": "+jugadorH);
			
			//funcion para ver el grado de entira de la maquina;
			puntosFaltaHumanoGanar = 30 - contador.getPuntosJug(); 
			diferenciaPuntos = contador.getPuntosMaq() - contador.getPuntosJug();
			if((diferenciaPuntos < -8)&&(puntosFaltaHumanoGanar > 10)){
				mentir = jugadorM.mentir(50);
			}else if((diferenciaPuntos < -12)&&(puntosFaltaHumanoGanar > 5)){
				mentir = jugadorM.mentir(70);
			}else{
				mentir = jugadorM.mentir(20);
			}
			
			//condicional para ver quien es mano
			if(jugadorH.isMano()){
				//Turno Humano
				System.out.println("\n"+jugadorH.getNombre()+" es mano.");
				error = true;
				//Verifico que no se ingrese cualquier cosa
				while(error){
					try{
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Jugar 3ra Carta \n4-Envido  --  5-Real Envido  --  6-Falta Envido  --  7-Truco \nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>7)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
						}
					}catch(Exception e){
						System.out.println("\nError. El valor ingresado no corresponde a un número");
						System.exit(1);
					}
				}
				
				if(resp == 4){
					jugadorH.envido(false, contador, jugadorM, mentir);
					envido = true;
					resp = jugadorH.pedirCarta(true,true,true,true,false,false);
				}else if(resp == 5){
					jugadorH.realEnvido(false, false, jugadorM, mentir, contador);
					envido = true;
					resp = jugadorH.pedirCarta(true,true,true,true,false,false);
				}else if(resp == 6){
					jugadorH.faltaEnvido(false, false, false, jugadorM, contador, mentir);
					envido = true;
					jugadorH.pedirCarta(true,true,true,true,false,false);
				}
				
				if(resp == 7){
					truco = true;
					//Verifico que la maquina no tenga puntos
					if(mentir){
						System.out.println("\n"+jugadorH.getNombre()+": Truco");
						System.out.print("\n"+jugadorM.getNombre()+": Primero esta el ");
						jugadorM.envido(false, contador, mentir, jugadorH);
						envido = true;
					}else{
						if(jugadorM.puntosMano()>26){
							System.out.println("\n"+jugadorH.getNombre()+": Truco");
							System.out.print("\n"+jugadorM.getNombre()+": Primero esta el ");
							jugadorM.envido(false, contador, mentir, jugadorH);
							envido = true;
						}
					}
					//Canta truco el humano
					respuestaTruco = jugadorH.truco(contador, jugadorM);
					//Verifico si la maquina no canto re truco
					if(respuestaTruco.get(1)){
						reTruco = true;
						respuestaReTruco = jugadorM.reTruco(contador, jugadorH, mentir);
						//verifico si el humano no canto vale 4
						if(respuestaReTruco.get(1)){
							vale4 = true;
							respuestaVale4 = jugadorH.vale4(contador, jugadorM, mentir);
							if(respuestaVale4){
								resp = jugadorH.pedirCarta(true,true,true,false,false,false);
							}else{
								contador.sumarPuntos(jugadorH, 3);
								jugadorH.setManoGanada(2);
							}
						}else{
							if(respuestaReTruco.get(0)){
								resp = jugadorH.pedirCarta(true,true,true,false,false,true);
							}else{
								contador.sumarPuntos(jugadorM, 2);
								jugadorH.setManoGanada(2);
							}
						}
					}else{
						if(respuestaTruco.get(0)){
							resp = jugadorH.pedirCarta(true,true,true,false,false,false);
						}else{
							contador.sumarPuntos(jugadorH, 1);
							jugadorH.setManoGanada(2);
						}
					}
				}
				
				//Verifico si el humano no canto vale 4
				if(resp == 9){
					vale4 = true;
					respuestaVale4 = jugadorH.vale4(contador, jugadorM, mentir);
					if(respuestaVale4){
						resp = jugadorH.pedirCarta(true,true,true,false,false,false);
					}else{
						contador.sumarPuntos(jugadorH, 3);
						jugadorH.setManoGanada(2);
					}
				}
				
				//Verifico que carta juega el humano
				switch(resp){
					case 1:
						System.out.println("\n"+jugadorH.getNombre()+": "+jugadorH.getCartas()[0]);
						jugadorH.getCartas()[0].setHabilitada(false);
						cartasJugadasHumano.add(jugadorH.getCartas()[0]);
						break;
					case 2:
						System.out.println("\n"+jugadorH.getNombre()+": "+jugadorH.getCartas()[1]);
						jugadorH.getCartas()[1].setHabilitada(false);
						cartasJugadasHumano.add(jugadorH.getCartas()[1]);
						break;
					case 3:
						System.out.println("\n"+jugadorH.getNombre()+": "+jugadorH.getCartas()[2]);
						jugadorH.getCartas()[2].setHabilitada(false);
						cartasJugadasHumano.add(jugadorH.getCartas()[2]);
						break;
				}
				//fin turno humano
				
				//comienzo turno maquina
				if((!jugadorH.ganoMano())&&(!jugadorM.ganoMano())){
					if(!(envido)){
						if(mentir){
							jugadorM.envido(false, contador, mentir, jugadorH);
							envido = true;
						}else{
							if(jugadorM.puntosMano()>26){
								jugadorM.envido(false, contador, mentir, jugadorH);
								envido = true;
							}
						}
					}
					
					//Aca falta hacer el metodo para que la maquina juegue alguna carta
				}
				
				
			}else{//fin si el humano es mano
			
			
			}//fin si la maquina es mano
			
			//Puntos de cada jugador
			System.out.println("\n"+jugadorH.getNombre()+": "+contador.getPuntosJug()+" puntos "
							 + "\n"+jugadorM.getNombre()+": "+contador.getPuntosMaq()+" puntos");
		}
	}//fin main
}//fin class
