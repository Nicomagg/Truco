package principal;

import java.util.Scanner;

import jugador.Humano;
import jugador.Maquina;
import Elementos.Contador;
import Elementos.Mazo;

public class Principal {

	
	public static void main(String[] args) {
		System.out.println("TRUCO - by Nicolas Maggione \n");
		
		Scanner sc = new Scanner(System.in);
		Contador contador = new Contador();
		Mazo mazo = new Mazo();
		Maquina jugadorM = new Maquina("Maquina");
		System.out.print("\nIngrese su nombre: ");
		String nom = sc.next();
		Humano jugadorH = new Humano(nom);
		boolean mentir;
		int puntosFaltaHumanoGanar;
		int diferenciaPuntos;
		
		while(contador.hayGanador()){
			//Reparto de cartas para cada jugador
			jugadorH.obtenerCartas(mazo.mezclarMazo());
			jugadorM.obtenerCartas(mazo.mezclarMazo());
			
			//Puntos de cada jugador
			System.out.println("\n"+jugadorH.getNombre()+": "+contador.getPuntosJug()+" puntos");
			System.out.println("\n"+jugadorM.getNombre()+": "+contador.getPuntosMaq()+" puntos");
			
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
				
			
			}else{//fin si el humano es mano
			
			
			}//fin si la maquina es mano
		}
	}//fin main
	
}//fin class
