package principal;

import java.util.Scanner;

import jugador.Humano;
import jugador.Maquina;
import Elementos.Carta;
import Elementos.Contador;
import Elementos.Mazo;

public class Principal {

	public static void main(String[] args) {
		System.out.println("TRUCO - by Nicolas Maggione \n");
		
		Scanner sc = new Scanner(System.in);
		Mazo mazo = new Mazo();//Instancio un nuevo mazo
		Contador contador = new Contador();//Instancio un nuevo contador del partido
		Maquina jugadorM = new Maquina("Maquina");//Instancio un nuevo jugador maquina
		Humano jugadorH;
		System.out.print("Ingrese su nombre: ");
		String nombre = sc.next();
		jugadorH = new Humano(nombre);//Instancio un nuevo jugador humano
		
		//Bucle que se ejecutara mientras no gane nadie
		while(!(contador.hayGanador())){
			System.out.println("\nSus puntos son: "+contador.getPuntosJug()+"\nY los puntos de la máquina es"+ contador.getPuntosMaq());
			Carta[] mezcla = mazo.mezclarMazo();//Mezclo el mazo
			jugadorH.obtenerCartas(mezcla);//Reparto al jugador
			jugadorM.obtenerCartas(mezcla);//Reparto a la maquina
			
			//controlo quien es mano
			if(jugadorH.isMano()){
				System.out.println("\n"+jugadorH.getNombre()+" es mano.\nSus cartas son: " + jugadorH.getCartas());
				System.out.println("/n -Ingrese 1 para jugar la primera carta; /n-Ingrese 2 para jugar la segunda carta; /n Ingrese 3 para jugar la tercer carta; /n4-Envido   5-RealEnvido   6-FaltaEnvido   7-Truco");
			}
		}
	}

}
