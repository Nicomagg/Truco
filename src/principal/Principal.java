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
		int resultadoVuelta = 0; //Variable para saber si las cartas de la maquina son mayore, menore o iguales
		
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
			System.out.println("Cartas de "+jugadorM.getNombre()+": "+jugadorM);//Esto se borra al final. Solo esta para poder ver las cartas de la maquina
			
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
					Scanner sc1 = new Scanner(System.in);
					try{
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Jugar 3ra Carta \n4-Envido  --  5-Real Envido  --  6-Falta Envido  --  7-Truco \nRespuesta: ");
						resp = sc1.nextInt();
						if((resp<1)||(resp>7)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
						}
					}catch(Exception e){
						System.out.println("\nError. El valor ingresado no corresponde a un número");
					}
				}
				
				//Verifico si no se canto envido
				if(resp == 4){
					jugadorH.envido(false, contador, jugadorM, mentir);
					envido = true;
				}else if(resp == 5){
					jugadorH.realEnvido(false, false, jugadorM, mentir, contador);
					envido = true;
				}else if(resp == 6){
					jugadorH.faltaEnvido(false, false, false, jugadorM, contador, mentir);
					envido = true;
				}
				
				//Verifico si ya no se termino antes la partida
				if((!(jugadorH.ganoMano()))&&(!(jugadorM.ganoMano()))){
					if((resp == 4)||(resp == 5)||(resp == 6)){
						resp = jugadorH.pedirCarta(true,true,true,true,false,false);
					}
					if(resp == 7){
						truco = true;
						if(!(envido)){	
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
					
					//Verifico si se debe jugar o no
					if((!(jugadorH.ganoMano()))&&(!(jugadorM.ganoMano()))){
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
					}
				}
				//fin turno humano
				
				//comienzo turno maquina
				if((!(jugadorH.ganoMano()))&&(!(jugadorM.ganoMano()))){
					//Verifico si no se canto envido
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
					
					if((!(jugadorH.ganoMano()))&&(!(jugadorM.ganoMano()))){
						//Verifico si la maquina puede cantar truco o si ya se ha cantado
						if(!(truco)){
							if(mentir){
								truco = true;
								respuestaTruco = jugadorM.truco(contador, jugadorH);
								if(respuestaTruco.get(1)){
									reTruco = true;
									respuestaReTruco = jugadorH.reTruco(contador, jugadorM, mentir);
									//Verifico respuesta del retruco
									if(respuestaReTruco.get(1)){
										vale4 = true;
										respuestaVale4 = jugadorM.vale4(jugadorH);
										if(!(respuestaVale4)){
											jugadorH.setManoGanada(2);
										}
									}else if(!(respuestaReTruco.get(0))){
										jugadorM.setManoGanada(2);
									}
								}else{
									if(!(respuestaTruco.get(0))){
										jugadorH.setManoGanada(2);
									}
								}
							}else{
								if(jugadorM.cantarTruco()){
									truco = true;
									respuestaTruco = jugadorM.truco(contador, jugadorH);
									if(respuestaTruco.get(1)){
										reTruco = true;
										respuestaReTruco = jugadorH.reTruco(contador, jugadorM, mentir);
										//Verifico respuesta del retruco
										if(respuestaReTruco.get(1)){
											vale4 = true;
											respuestaVale4 = jugadorM.vale4(jugadorH);
											if(!(respuestaVale4)){
												jugadorH.setManoGanada(2);
											}
										}else if(!(respuestaReTruco.get(0))){
											jugadorM.setManoGanada(2);
										}
									}else{
										if(!(respuestaTruco.get(0))){
											jugadorH.setManoGanada(2);
										}
									}
								}
							}
						}//Fin si no se canto truco
						
						int posiCarta = jugadorM.jugarCarta(cartasJugadasHumano.get(0), false);
						cartasJugadasMaquina.add(jugadorM.getCartas()[posiCarta]);
						System.out.println(jugadorM.getNombre()+": "+jugadorM.getCartas()[posiCarta]);
						
						if(cartasJugadasHumano.get(0).getValor() < cartasJugadasMaquina.get(0).getValor()){
							resultadoVuelta = -1;
							jugadorM.setManoGanada(1);
						}else if(cartasJugadasHumano.get(0).getValor() < cartasJugadasMaquina.get(0).getValor()){
							resultadoVuelta = 1;
							jugadorH.setManoGanada(1);
						}else{
							resultadoVuelta = 0;
						}
						
					}//Fin verificacion si no gano antes alguien la mano
					
				}//Fin turno de la maquina
				
				//Se cambia quien es mano
				jugadorH.setMano(false);
				jugadorM.setMano(true);
			
			}else{//fin si el humano es mano
			
				//Turno Maquina
				System.out.println("\n"+jugadorM.getNombre()+" es mano.");
				
				//Verifico si la maquina debe cantar envido
				if(mentir){
					jugadorM.envido(false, contador, mentir, jugadorH);
					envido = true;
				}else{
					if(jugadorM.puntosMano()>30){
						jugadorM.realEnvido(false, false, jugadorH, contador, mentir);
						envido = true;
					}else if(jugadorM.puntosMano()>26){
						jugadorM.envido(false, contador, mentir, jugadorH);
						envido = true;
					}
				}
				
				//Verifico si canto truco
				if(mentir){
					truco = true;
					respuestaTruco = jugadorM.truco(contador, jugadorH);
					if(respuestaTruco.get(1)){
						reTruco = true;
						respuestaReTruco = jugadorH.reTruco(contador, jugadorM, mentir);
						//Verifico respuesta del retruco
						if(respuestaReTruco.get(1)){
							vale4 = true;
							respuestaVale4 = jugadorM.vale4(jugadorH);
							if(!(respuestaVale4)){
								jugadorH.setManoGanada(2);
							}
						}else if(!(respuestaReTruco.get(0))){
							jugadorM.setManoGanada(2);
						}
					}else{
						if(!(respuestaTruco.get(0))){
							jugadorH.setManoGanada(2);
						}
					}
				}else{
					if(jugadorM.cantarTruco()){
						truco = true;
						respuestaTruco = jugadorM.truco(contador, jugadorH);
						if(respuestaTruco.get(1)){
							reTruco = true;
							respuestaReTruco = jugadorH.reTruco(contador, jugadorM, mentir);
							//Verifico respuesta del retruco
							if(respuestaReTruco.get(1)){
								vale4 = true;
								respuestaVale4 = jugadorM.vale4(jugadorH);
								if(!(respuestaVale4)){
									jugadorH.setManoGanada(2);
								}
							}else if(!(respuestaReTruco.get(0))){
								jugadorM.setManoGanada(2);
							}
						}else{
							if(!(respuestaTruco.get(0))){
								jugadorH.setManoGanada(2);
							}
						}
					}
				}
				
				//Verifico si no termino la partida ya
				if((!(jugadorH.ganoMano()))&&(!(jugadorM.ganoMano()))){
					//Juega carta la máquina
					System.out.println(jugadorM.getNombre()+": "+jugadorM.getCartas()[0]);
					jugadorM.getCartas()[0].setHabilitada(false);
					cartasJugadasMaquina.add(jugadorM.getCartas()[0]);
				}
					
				//Turno de juego del humano
				if((!(jugadorH.ganoMano()))&&(!(jugadorM.ganoMano()))){
					if((!(envido))&&(!(truco))){
						error = true;
						//Verifico que no se ingrese cualquier cosa
						while(error){
							Scanner sc1 = new Scanner(System.in);
							try{
								System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Jugar 3ra Carta \n4-Envido  --  5-Real Envido  --  6-Falta Envido  --  7-Truco \nRespuesta: ");
								resp = sc1.nextInt();
								if((resp<1)||(resp>7)){
									System.out.println("\nError. El valor ingresado no corresponde a un número válido");
								}else{
									error = false;
								}
							}catch(Exception e){
								System.out.println("\nError. El valor ingresado no corresponde a un número");
							}
						}
						
						//Verifico si no se canto envido
						if(resp == 4){
							jugadorH.envido(false, contador, jugadorM, mentir);
							envido = true;
						}else if(resp == 5){
							jugadorH.realEnvido(false, false, jugadorM, mentir, contador);
							envido = true;
						}else if(resp == 6){
							jugadorH.faltaEnvido(false, false, false, jugadorM, contador, mentir);
							envido = true;
						}
						
						if((!(jugadorH.ganoMano()))&&(!(jugadorM.ganoMano()))){
							if((resp == 4)||(resp == 5)||(resp == 6)){
								resp = jugadorH.pedirCarta(true,true,true,true,false,false);
							}
						}else{
							//Fijarme como seguir aca despues. No hay ganas de codear ahora!!!
						}
					}
				}
				
				//Se cambia quien es mano
				jugadorH.setMano(false);
				jugadorM.setMano(true);
			}//fin si la maquina es mano
			
			//Aca falta hacer la funcion para que siga la segunda mano
			
			//Puntos de cada jugador
			System.out.println("\n"+jugadorH.getNombre()+": "+contador.getPuntosJug()+" puntos "
							 + "\n"+jugadorM.getNombre()+": "+contador.getPuntosMaq()+" puntos"
							 + "\n \n \n"
							 + "------------------------------------------------------------------------------"
							 + "\n \n \n");
			//Inicializo las manos
			jugadorH.setManoGanada(0);
			jugadorM.setManoGanada(0);
		}
	}//fin main
}//fin class