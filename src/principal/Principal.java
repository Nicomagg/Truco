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
		Maquina jugadorM = new Maquina("Maquina");
		System.out.print("\nIngrese su nombre: ");
		String nom = sc.next();
		Humano jugadorH = new Humano(nom);
		boolean mentir;
		boolean error;
		int resp = 0;
		int puntosFaltaHumanoGanar;
		int diferenciaPuntos;
		
		while(!(contador.hayGanador())){
			//Reparto de cartas para cada jugador
			jugadorH.obtenerCartas(mazo.mezclarMazo());
			jugadorM.obtenerCartas(mazo.mezclarMazo());
			
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
					resp = humanoPedirCarta(true,true,true,true);
				}else if(resp == 5){
					jugadorH.realEnvido(false, false, jugadorM, mentir, contador);
					resp = humanoPedirCarta(true,true,true,true);
				}else if(resp == 6){
					jugadorH.faltaEnvido(false, false, false, jugadorM, contador, mentir);
					humanoPedirCarta(true,true,true,true);
				}
				
				if(resp == 7){
					if(mentir){
						System.out.println("\n"+jugadorH.getNombre()+": Truco");
						System.out.print("\n"+jugadorM.getNombre()+": Primero esta el ");
						jugadorM.envido(false, contador, mentir, jugadorH);
					}else{
						if(jugadorM.puntosMano()>26){
							System.out.println("\n"+jugadorH.getNombre()+": Truco");
							System.out.print("\n"+jugadorM.getNombre()+": Primero esta el ");
							jugadorM.envido(false, contador, mentir, jugadorH);
						}
					}
					ArrayList<Boolean> respuestaTruco = jugadorH.truco(contador, jugadorM);
					if(respuestaTruco.get(1)){
						jugadorM.reTruco(contador, jugadorH, mentir);//Ver como seguir aca
					}else{
						if(respuestaTruco.get(0)){
							resp = humanoPedirCarta(true,true,true,false);//Agregar reTruco y Vale 4
						}else{
							contador.sumarPuntos(jugadorH, 1);
							jugadorH.setManoGanada(2);
						}
					}
				}
				
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
						
				
			}else{//fin si el humano es mano
			
			
			}//fin si la maquina es mano
			
			//Puntos de cada jugador
			System.out.println("\n"+jugadorH.getNombre()+": "+contador.getPuntosJug()+" puntos");
			System.out.println("\n"+jugadorM.getNombre()+": "+contador.getPuntosMaq()+" puntos");
		}
	}//fin main
	
	//metodo para pedir que el jugador juegue una carta
	private static int humanoPedirCarta(final boolean prim, final boolean seg, final boolean terc, final boolean truco){
		boolean error = true;
		int resp = 0;
		Scanner sc = new Scanner(System.in);
		//Verifico que no se ingrese cualquier cosa
		while(error){
			try{
				if(!(truco)){
					if(prim && seg && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Jugar 3ra Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
						}
					}else if(prim && seg){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>2)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
						}
					}else if(seg && terc){
						System.out.print("\n1-Jugar 2da Carta \n2-Jugar 3ra Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>2)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==1){
								resp = 2;
							}else{
								resp = 3;
							}
						}
					}else if(prim && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 3ra Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>2)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==2){
								resp = 3;
							}
						}
					}else if(prim){
						System.out.print("\n1-Jugar 1ra Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if(!(resp==1)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
						}
					}else if(seg){
						System.out.print("\n1-Jugar 2da Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if(!(resp==1)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							resp = 2;
						}
					}else if(terc){
						System.out.print("\n3-Jugar 3ra Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if(!(resp==3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							resp = 3;
						}
					}
				}else{
					if(prim && seg && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Jugar 3ra Carta \n4-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>4)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==4){
								resp = 7;
							}
						}
					}else if(prim && seg){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==3){
								resp = 7;
							}
						}
					}else if(seg && terc){
						System.out.print("\n1-Jugar 2da Carta \n2-Jugar 3ra Carta \n3-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							switch(resp){
								case 1:
									resp = 2;
									break;
								case 2:
									resp = 3;
									break;
								case 3:
									resp = 7;
									break;
							}
						}
					}else if(prim && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 3ra Carta \n3-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==2){
								resp = 3;
							}else if(resp == 3){
								resp = 7;
							}
						}
					}else if(prim){
						System.out.print("\n1-Jugar 1ra Carta \n2-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))==(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp == 2){
								resp = 7;
							}
						}
					}else if(seg){
						System.out.print("\n1-Jugar 2da Carta \n2-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))||(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp == 1){
								resp = 2;
							}else{
								resp = 7;
							}
						}
					}else if(terc){
						System.out.print("\n1-Jugar 3ra Carta \n2-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))||(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==1){
								resp=3;
							}else{
								resp = 7;
							}
						}
					}
				}
			}catch(Exception e){
				System.out.println("\nError. El valor ingresado no corresponde a un número");
				System.exit(1);
			}
		}
		return resp;
	}
}//fin class
