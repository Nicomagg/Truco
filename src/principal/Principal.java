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
	private static boolean cantoEnvido = false;
	private static boolean truco = false;
	private static boolean reTruco = false;
	private static boolean vale4 = false;
	
	public static void main(String[] args) {
		System.out.println("TRUCO - by Nicolas Maggione \n");
		
		//Distintas variables
		boolean error;
		int difPuntos;
		int puntosGanar;
		int primera = 0;//Variables para la primera vuelta de una mano
		int segunda = 0;//Variables para la segunda vuelta de una mano
		int tercera = 0;//Variables para la tercera vuelta de una mano		
		
		System.out.print("Ingrese su nombre: ");
		String nombre = sc.next();
		jugadorH = new Humano(nombre);//Instancio un nuevo jugador humano
		
		//Bucle que se ejecutara mientras no gane nadie
		while(!(contador.hayGanador())){
			System.out.println("\nSus puntos: "+contador.getPuntosJug()+"\nPuntos máquina: "+ contador.getPuntosMaq());
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
			
			//while para ver si la mano no ha sido ganada ya
			while((jugadorH.ganoMano())||(jugadorM.ganoMano())){
				
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
					
					//Si se canto algo antes de jugar alguna carta
					if(primera == 4){
						envido(false, jugadorH);
					}else if(primera == 5){
						realEnvido(false,false,jugadorH);
					}else if(primera == 6){
						faltaEnvido(false,false,false,jugadorH);
					}else if(primera == 7){
						
					}
				
				//fin si el jugador humano es mano	
				}
			
			}//fin while que controla si la mano se ha ganado
			
		}//fin while principal
	}//fin main
	
	//funcion para cantar la falta envido
	public static void faltaEnvido(boolean envido, boolean envidoEnvido, boolean realEnvido, Jugador jug){
		System.out.println("\nFalta Envido");
		cantoEnvido = true;
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
	public static void realEnvido(boolean envido, boolean envidoEnvido, Jugador jug){
		System.out.println("\nReal Envido");
		cantoEnvido = true;
		int respuesta = 0;
		boolean error = true;
		
		if(jug.isHumano()){
			int puntosGanarMaq = 30 - contador.getPuntosMaq();
			int puntosGanarHum = 30 - contador.getPuntosJug();
			if(mentir){
				faltaEnvido(envido, envidoEnvido, true, jugadorM);
			}else{
				if(envido){
					if(puntosGanarHum<=2){
						respuesta = 1;
					}else{
						if(puntosGanarMaq<=2){
							faltaEnvido(envido, envidoEnvido, true, jugadorM);
						}else{
							if((jugadorM.puntosMano()>27)&&(jugadorM.puntosMano()<31)){
								respuesta = 1;
							}else if(jugadorM.puntosMano()>=31){
								faltaEnvido(envido, envidoEnvido, true, jugadorM);
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
							faltaEnvido(envido, envidoEnvido, true, jugadorM);
						}else{
							if((jugadorM.puntosMano()>27)&&(jugadorM.puntosMano()<31)){
								respuesta = 1;
							}else if(jugadorM.puntosMano()>=31){
								faltaEnvido(envido, envidoEnvido, true, jugadorM);
							}else{
								respuesta = 0;
							}
						}
					}
				}else{
					if(puntosGanarMaq==1){
						faltaEnvido(envido, envidoEnvido, true, jugadorM);
					}else{
						if((jugadorM.puntosMano()>27)&&(jugadorM.puntosMano()<31)){
							respuesta = 1;
						}else if(jugadorM.puntosMano()>=31){
							faltaEnvido(envido, envidoEnvido, true, jugadorM);
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
				faltaEnvido(envido, envidoEnvido, true, jugadorH);
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
		}else if( respuesta == 2){
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
	
	//Funcion para realizar el envido
	public static void envido(boolean envido, Jugador jug){
		System.out.println("\nEnvido");
		cantoEnvido = true;
		int respuesta = 0;
		boolean error = true;
		
		if(envido){
			if(jug.isHumano()){
				if(mentir){
					faltaEnvido(false, true, false, jugadorM);
				}else{
					if((jugadorM.puntosMano()>27)&&(jugadorM.puntosMano()<30)){
						respuesta = 1;
					}else if(jugadorM.puntosMano()>=30){
						faltaEnvido(false, true, false, jugadorM);
					}else{
						respuesta = 2;
					}
				}
			//fin si es humano	
			}else{
				
				while(error){
					try{
						System.out.println("1-Quiero  --  2-No Quiero  --  3-Real Envido  --  4-Falta Envido");
						respuesta = sc.nextInt();
						if((respuesta<1)&&(respuesta>4)){
							System.out.println("\nError, el valor ingresado no corresponde a una de la opciones");
						}else{
							error = false;
						}
					}catch(Exception e){
						System.out.println("\nError. El valor ingresado no es un número");
					}
				}
				
				if(respuesta == 3){
					realEnvido(false, true, jugadorH);
				}else if(respuesta == 4){
					faltaEnvido(false, true, false, jugadorH);
				}
			//fin si es maquina el que canto	
			}
		//Fin Si se canto envido	
		}else{
			if(jug.isHumano()){
				if(mentir){
					envido(true, jugadorM);
				}else{
					if((jugadorM.puntosMano()>24)&&(jugadorM.puntosMano()<29)){
						respuesta = 1;
					}else if(jugadorM.puntosMano()>=29){
						envido(true, jugadorM);
					}else{
						respuesta = 2;
					}
				}
			//fin si es humano	
			}else{
				
				while(error){
					try{
						System.out.println("1-Quiero  --  2-No Quiero  --  3-Envido  --  4-Real Envido  --  5-Falta Envido");
						respuesta = sc.nextInt();
						if((respuesta<1)&&(respuesta>5)){
							System.out.println("\nError, el valor ingresado no corresponde a una de la opciones");
						}else{
							error = false;
						}
					}catch(Exception e){
						System.out.println("\nError. El valor ingresado no es un número");
					}
				}
				
				if(respuesta == 3){
					envido(true, jugadorH);
				}else if(respuesta == 4){
					realEnvido(true, false, jugadorH);
				}else if(respuesta == 5){
					faltaEnvido(true, false, false, jugadorH);
				}
			//fin si es maquina el que canto	
			}
		//Fin si no se canto envido envido	
		}
		
		if(respuesta == 1){
			int puntos = jugadorH.obtenerPutnos();
			System.out.println("\nLos puntos de la maquina es: "+jugadorM.puntosMano());
			
			if(puntos < jugadorM.puntosMano()){
				if(envido){
					contador.sumarPuntos(jugadorM, 4);
				}else{
					contador.sumarPuntos(jugadorM, 2);
				}
			}else if(puntos > jugadorM.puntosMano()){
				if(envido){
					contador.sumarPuntos(jugadorH, 4);
				}else{
					contador.sumarPuntos(jugadorH, 2);
				}
			}else{
				if(jugadorH.isMano()){
					if(envido){
						contador.sumarPuntos(jugadorH, 4);
					}else{
						contador.sumarPuntos(jugadorH, 2);
					}
				}else{
					if(envido){
						contador.sumarPuntos(jugadorM, 4);
					}else{
						contador.sumarPuntos(jugadorM, 2);
					}
				}
			}
				
		}else if(respuesta == 2){
			if(jug.isHumano()){
				if(envido){
					contador.sumarPuntos(jugadorH, 2);
				}else{
					contador.sumarPuntos(jugadorH, 1);
				}
			}else{
				if(envido){
					contador.sumarPuntos(jugadorM, 2);
				}else{
					contador.sumarPuntos(jugadorM, 1);
				}
			}
		}//fin si quiere o no
	}//fin envido

}//fin class
