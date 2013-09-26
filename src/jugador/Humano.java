package jugador;

import java.util.Scanner;

import Elementos.Carta;

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

	@Override
	public boolean jugarCarta(int posicion) {
		if(this.getCartas()[posicion].isHabilitada()){
			System.out.println("\nCarta de usted: "+this.getCartas()[posicion].getNumero()+"-"+this.getCartas()[posicion].getPalo());
			this.getCartas()[posicion].setHabilitada(false);
			return true;
		}else{
			System.out.println("La carta ya ha sido jugada");
			return false;
		}
	}

}
