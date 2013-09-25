package principal;

import java.util.Scanner;

import jugador.Humano;
import jugador.Jugador;
import jugador.Maquina;
import Elementos.Carta;
import Elementos.Contador;
import Elementos.Mazo;

public class Principal {

	private static boolean mentir;
	private static Scanner sc = new Scanner(System.in);
	private static Mazo mazo = new Mazo();//Instancio un nuevo mazo
	private static Contador contador = new Contador();//Instancio un nuevo contador del partido
	private static Maquina jugadorM = new Maquina("Maquina");//Instancio un nuevo jugador maquina
	private static Humano jugadorH;
	
	public static void main(String[] args) {
		System.out.println("TRUCO - by Nicolas Maggione \n");
		
		//Distintas variables
		boolean error;
		int difPuntos;
		int puntosGanar;
		int primera = 0;//Variables para la primera vuelta de una mano
		int segunda = 0;//Variables para la segunda vuelta de una mano
		int tercera = 0;//Variables para la tercera vuelta de una mano		
		boolean truco = false;
		boolean reTruco = false;
		boolean vale4 = false;
		
		System.out.print("Ingrese su nombre: ");
		String nombre = sc.next();
		jugadorH = new Humano(nombre);//Instancio un nuevo jugador humano
		
		//Bucle que se ejecutara mientras no gane nadie
		while(!(contador.hayGanador())){
			System.out.println("\nSus puntos: "+contador.getPuntosJug()+"\nPuntos maquina: "+ contador.getPuntosMaq());
			Carta[] mezcla = mazo.mezclarMazo();//Mezclo el mazo
			jugadorH.obtenerCartas(mezcla);//Reparto al jugador
			jugadorM.obtenerCartas(mezcla);//Reparto a la maquina
			error = true;
			difPuntos = contador.getPuntosMaq() - contador.getPuntosJug();
			puntosGanar = 30 - contador.getPuntosJug();//Puntos que le falta al jugador para ganar
			
			//condicional para ver el grado de mentira de la maquina
			if((difPuntos <= -10)&&(puntosGanar < 20)){
				mentir = jugadorM.mentir(50);
			}else if((difPuntos < -10)&&(puntosGanar < 25)){
				mentir = jugadorM.mentir(70);
			}else{
				mentir = jugadorM.mentir(20);
			}
			
			//controlo quien es mano
			if(jugadorH.isMano()){
				System.out.println("\n"+jugadorH.getNombre()+" es mano.\nSus cartas son: " + jugadorH);
				
				//Bucle que se repetira siempre que el valor ingresado no sea correcto.
				while(error){
					System.out.println("\n-Ingrese 1 para jugar la primera carta; \n-Ingrese 2 para jugar la segunda carta; \n-Ingrese 3 para jugar la tercer carta; \n4-Envido   5-RealEnvido   6-FaltaEnvido   7-Truco");
					//Try para verificar que lo que se ingresa sea un numero
					try{
						primera = sc.nextInt();
						//Verifico que el numero ingresado sea correcto
						if((primera < 1)&&(primera > 7)){
							System.out.println("El valor ingresa no corresponde con un numero válido");
						}else{
							error = false;
						}
					}catch(Exception e){
						System.out.println("El valor ingresado no es un número");
					}//fin try
				}//fin while
				
				//condicinal segun lo ingresado por el jugador
				if(primera == 4){
					
				}
				
			}
		}//fin while principal
	}//fin main
	
	//funcion para cantar la falta envido
	public void faltaEnvido(boolean envido, boolean envidoEnvido, boolean realEnvido, Jugador jug){
		System.out.println("\nFalta Envido");
		int respuesta = 0;
		boolean error = true;
		
		if(!(jug.isHumano())){
			
			while(error){
				try{
					System.out.println("1-Quiero  --  2-No Quiero \n Respuesta(1 o 2): ");
					respuesta = sc.nextInt();
					
					if((respuesta<1)&&(respuesta>2)){
						System.out.println("El valor ingresado no corresponde con una respuesta válida.");
					}else{
						error = false;
					}
				}catch (Exception e){
					System.out.println("Error. El valor ingresado no es un número");
				}
			}
			
		}else{
			int puntosGanarHuman = 30 - contador.getPuntosJug();
			if(envidoEnvido){
				if(realEnvido){
					if(7 >= puntosGanarHuman){
						respuesta = 1;
					}else{
						if((jugadorM.puntosMano())>29){
							respuesta = 1;
						}else{
							respuesta = 0;
						}
					}
				}else{
					if(4 >= puntosGanarHuman){
						respuesta = 1;
					}else{
						if((jugadorM.puntosMano())>29){
							respuesta = 1;
						}else{
							respuesta = 0;
						}
					}
				}
			}else if(envido){
				if(realEnvido){
					if(5 >= puntosGanarHuman){
						respuesta = 1;
					}else{
						if((jugadorM.puntosMano())>29){
							respuesta = 1;
						}else{
							respuesta = 0;
						}
					}
				}else{
					if(2 >= puntosGanarHuman){
						respuesta = 1;
					}else{
						if((jugadorM.puntosMano())>29){
							respuesta = 1;
						}else{
							respuesta = 0;
						}
					}
				}
			}else if(realEnvido){
				if(3 >= puntosGanarHuman){
					respuesta = 1;
				}else{
					if((jugadorM.puntosMano())>29){
						respuesta = 1;
					}else{
						respuesta = 0;
					}
				}
			}else{	
				if((jugadorM.puntosMano())>29){
					respuesta = 1;
				}else{
					respuesta = 0;
				}
			}
		}	
		
		if(respuesta == 1){
			System.out.println("\nQuiero");
			int puntosHum = jugadorH.obtenerPutnos();
			System.out.println("Los puntos de la máquina son: "+jugadorM.puntosMano());
			if(puntosHum < jugadorM.puntosMano()){
				contador.sumarFaltaEnvido(jugadorM);
			}else if(puntosHum > jugadorM.puntosMano()){
				contador.sumarFaltaEnvido(jugadorH);
			}else{
				if(jugadorH.isMano()){
					contador.sumarFaltaEnvido(jugadorH);
				}else{
					contador.sumarFaltaEnvido(jugadorM);
				}
			}
		}else{
			System.out.println("No Quiero");
			if(jug.isHumano()){
				if(envido){
					if(realEnvido){
						contador.sumarPuntos(jugadorH, 5);
					}else{
						contador.sumarPuntos(jugadorH, 2);
					}
				}else if(envidoEnvido){
					if(realEnvido){
						contador.sumarPuntos(jugadorH, 7);
					}else{
						contador.sumarPuntos(jugadorH, 4);
					}
				}else if(realEnvido){
					contador.sumarPuntos(jugadorH, 3);
				}else{
					contador.sumarPuntos(jugadorH, 1);
				}
			}else{
				if(envido){
					if(realEnvido){
						contador.sumarPuntos(jugadorM, 5);
					}else{
						contador.sumarPuntos(jugadorM, 2);
					}
				}else if(envidoEnvido){
					if(realEnvido){
						contador.sumarPuntos(jugadorM, 7);
					}else{
						contador.sumarPuntos(jugadorM, 4);
					}
				}else if(realEnvido){
					contador.sumarPuntos(jugadorM, 3);
				}else{
					contador.sumarPuntos(jugadorM, 1);
				}
			}
		}
	
	}//fin falta envido
	
	//funcion para cantar el real envido
	public void realEnvido(boolean envido, boolean envidoEnvido, Jugador jug){
		System.out.println("\nReal Envido");
		int respuesta = 0;
		boolean error = true;
		
		if(jug.isHumano()){
			int puntosGanarMaq = 30 - contador.getPuntosMaq();
			int puntosGanarHum = 30 - contador.getPuntosJug();
			if(mentir){
				this.faltaEnvido(envido, envidoEnvido, true, jugadorM);
			}else{
				if(envido){
					if(puntosGanarHum<=2){
						respuesta = 1;
					}else{
						if(puntosGanarMaq<=2){
							this.faltaEnvido(envido, envidoEnvido, true, jugadorM);
						}else{
							if((jugadorM.puntosMano()>27)&&(jugadorM.puntosMano()<31)){
								respuesta = 1;
							}else if(jugadorM.puntosMano()>=31){
								this.faltaEnvido(envido, envidoEnvido, true, jugadorM);
							}else{
								respuesta = 0;
							}
						}
					}
				}else if(envidoEnvido){
					if(puntosGanarHum<=4){
						respuesta = 1;
					}else{
						if(puntosGanarMaq<=4){
							this.faltaEnvido(envido, envidoEnvido, true, jugadorM);
						}else{
							if((jugadorM.puntosMano()>27)&&(jugadorM.puntosMano()<31)){
								respuesta = 1;
							}else if(jugadorM.puntosMano()>=31){
								this.faltaEnvido(envido, envidoEnvido, true, jugadorM);
							}else{
								respuesta = 0;
							}
						}
					}
				}else{
					if(puntosGanarMaq==1){
						this.faltaEnvido(envido, envidoEnvido, true, jugadorM);
					}else{
						if((jugadorM.puntosMano()>27)&&(jugadorM.puntosMano()<31)){
							respuesta = 1;
						}else if(jugadorM.puntosMano()>=31){
							this.faltaEnvido(envido, envidoEnvido, true, jugadorM);
						}else{
							respuesta = 0;
						}
					}
				}
			}//fin pregunta sobre mentira
			//fin juego de la maquina si es ella la que tiene que decidir si quiere o no
		}else{
			
			while(error){
				try{
					System.out.println("1-Quiero  --  2-No Quiero  --  3-Falta Envido \nRespuesta(1, 2 o 3");
					respuesta = sc.nextInt();
					
					if((respuesta<1)&&(respuesta>3)){
						System.out.println("\nError, el valor ingresado no corresponde a una respuesta");
					}else{
						error = false;
					}
				}catch(Exception e){
					System.out.println("Error. El valor ingresado no es un número");
				}
			}
			
			if(respuesta == 3){
				this.faltaEnvido(envido, envidoEnvido, true, jugadorH);
			}
		}
		
		if(respuesta == 1){
			int puntos = jugadorH.obtenerPutnos();
			System.out.println("Los puntos de la máquina son: "+jugadorM.puntosMano());
			if(puntos < jugadorM.puntosMano()){
				if(envido){
					contador.sumarPuntos(jugadorM, 5);
				}else if(envidoEnvido){
					contador.sumarPuntos(jugadorM, 7);
				}else{
					contador.sumarPuntos(jugadorM, 3);
				}
			}else if(puntos > jugadorM.puntosMano()){
				if(envido){
					contador.sumarPuntos(jugadorH, 5);
				}else if(envidoEnvido){
					contador.sumarPuntos(jugadorH, 7);
				}else{
					contador.sumarPuntos(jugadorH, 3);
				}
			}else{
				if(jugadorH.isMano()){
					if(envido){
						contador.sumarPuntos(jugadorH, 5);
					}else if(envidoEnvido){
						contador.sumarPuntos(jugadorH, 7);
					}else{
						contador.sumarPuntos(jugadorH, 3);
					}
				}else{
					if(envido){
						contador.sumarPuntos(jugadorM, 5);
					}else if(envidoEnvido){
						contador.sumarPuntos(jugadorM, 7);
					}else{
						contador.sumarPuntos(jugadorM, 3);
					}
				}
			}
		}else{
			if(jug.isHumano()){
				if(envido){
					contador.sumarPuntos(jugadorH, 3);
				}else if(envidoEnvido){
					contador.sumarPuntos(jugadorH, 5);
				}else{
					contador.sumarPuntos(jugadorH, 1);
				}
			}else{
				if(envido){
					contador.sumarPuntos(jugadorM, 3);
				}else if(envidoEnvido){
					contador.sumarPuntos(jugadorM, 5);
				}else{
					contador.sumarPuntos(jugadorM, 1);
				}
			}
		}
	}//fin real envido

}//fin class
