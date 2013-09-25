package Elementos;

import java.util.HashSet;

public class Mazo {
	Carta [] mazo = new Carta[40];
	
	public Mazo(){
		crearMazo();
	}
	
	//funcion para crear el mazo
	private void crearMazo(){
		for(int x=0; x<40; x++){
			
			//condicionales que asigna distintos palos a las cartas
			if(x<10){
				if(x<7){
					mazo[x] = new Carta(x+1,"Espada"); 
				}else{
					mazo[x] = new Carta(x+3,"Espada");
				}
			}else if(x < 20){
				if(x<17){
					mazo[x] = new Carta((x-10)+1,"Oro"); 
				}else{
					mazo[x] = new Carta((x-10)+3,"Oro");
				}
			}else if(x < 30){
				if(x<27){
					mazo[x] = new Carta((x-20)+1,"Basto"); 
				}else{
					mazo[x] = new Carta((x-20)+3,"Basto");
				}
			}else{
				if(x<37){
					mazo[x] = new Carta((x-30)+1,"Copa"); 
				}else{
					mazo[x] = new Carta((x-30)+3,"Copa");
				}
			}
		}
	}
	
	//funcion para mostrar mazo
	public void getMazo(){
		for(int i = 0; i<40; i++){
			System.out.print(mazo[i].getNumero()+"-");
			System.out.println(mazo[i].getPalo());
		}
	}
	
	//funcion para generar pseudo mezcla de cartas
	public Carta[] mezclarMazo(){
		Carta[] mezcla = new Carta[6];
		int j;
		HashSet<Carta> carta = new HashSet<Carta>();
		
		//Bucle para ver si no se reparten cartas iguales;
		while((carta.size())<6){
			j = (int)((Math.random()) * 39);
			carta.add(mazo[j]);
		}

		carta.toArray(mezcla);
		
		return mezcla;
	}
	
}
