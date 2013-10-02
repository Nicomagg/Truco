package jugador;

import Elementos.Carta;
import Elementos.Contador;

public class Maquina extends Jugador{

	public Maquina(String nom) {
		super(nom);
	}
	
	public boolean isHumano(){
		return false;
	}
	
	//funcion que especifica segun la probabilidad con que queramos que mienta, si miente la maquina o no
	public boolean mentir(int probabilidad){
		int probMentira = (int) (Math.random() * 100);
		if(probabilidad == 20){
			if(probMentira < 21){
				return true;
			}else{
				return false;
			}
		}else if (probMentira == 50){
			if(probMentira < 51){
				return true;
			}else{
				return false;
			}
		}else{
			if(probMentira < 71){
				return true;
			}else{
				return false;
			}
		}
	}
	
	//funcion para contar los puntos que tengo
	public int puntosMano(){
		if((this.getCartas()[0].getPalo() == this.getCartas()[1].getPalo())){
			if((this.getCartas()[0].getNumero()<10)&&(this.getCartas()[1].getNumero()<10)){
				return(20 + this.getCartas()[0].getNumero() + this.getCartas()[1].getNumero());
			}else if((this.getCartas()[0].getNumero()>=10)&&(this.getCartas()[1].getNumero()<10)){ 
				return(20 + this.getCartas()[1].getNumero());
			}else if((this.getCartas()[0].getNumero()<10)&&(this.getCartas()[1].getNumero()>=10)){
				return(20 + this.getCartas()[0].getNumero());
			}else{
				return 20;
			}
		}else if((this.getCartas()[0].getPalo() == this.getCartas()[2].getPalo())){
			if((this.getCartas()[0].getNumero()<10)&&(this.getCartas()[2].getNumero()<10)){
				return(20 + this.getCartas()[0].getNumero() + this.getCartas()[2].getNumero());
			}else if((this.getCartas()[0].getNumero()>=10)&&(this.getCartas()[2].getNumero()<10)){ 
				return(20 + this.getCartas()[2].getNumero());
			}else if((this.getCartas()[0].getNumero()<10)&&(this.getCartas()[2].getNumero()>=10)){
				return(20 + this.getCartas()[0].getNumero());
			}else{
				return 20;
			}
		}else if((this.getCartas()[1].getPalo() == this.getCartas()[2].getPalo())){
			if((this.getCartas()[1].getNumero()<10)&&(this.getCartas()[2].getNumero()<10)){
				return(20 + this.getCartas()[1].getNumero() + this.getCartas()[2].getNumero());
			}else if((this.getCartas()[1].getNumero()>=10)&&(this.getCartas()[2].getNumero()<10)){ 
				return(20 + this.getCartas()[2].getNumero());
			}else if((this.getCartas()[1].getNumero()<10)&&(this.getCartas()[2].getNumero()>=10)){
				return(20 + this.getCartas()[1].getNumero());
			}else{
				return 20;
			}
		}else{
			int max = 0;
			for(int i = 0; i < 3; i++){
				if((this.getCartas()[i].getNumero()>max) && (this.getCartas()[i].getNumero()<10)){
					max = this.getCartas()[i].getNumero();
				}
			}
			return max;
		}
	}
	
	//FUncion para obtener cartas
	public void obtenerCartas(Carta[] mezcla){
		int j = 0;
		Carta[] cartas = new Carta[3];
		for(int i = 0; i<6; i++){
			if((i%2)!=0){
				cartas[j]=mezcla[i];
				j++;
			}
		}
		this.setCartas(cartas);
		ordenarMenorMayor();
	}
	
	//Funcion que ordena las cartas de menor a mayor segun su valor
	private void ordenarMenorMayor(){
		Carta aux;
		for(int i = 0; i < 2; i++){
			for(int j = i; j < 3; j++){
				if((this.getCartas()[i].getValor())>(this.getCartas()[j].getValor())){
					aux = this.getCartas()[j];
					this.setCarta(this.getCartas()[0], j);
					this.setCarta(aux, 0);
				}
			}
		}
	}
	
	//Funcion para ver si tengo cartas para el truco
	public boolean cartaTruco(){
		if((this.getCartas()[0].getValor() > 7)&&(this.getCartas()[1].getValor() > 7)){
			return true;
		}else if((this.getCartas()[1].getValor() > 7)&&(this.getCartas()[2].getValor() > 7)){
			return true;
		}else if((this.getCartas()[0].getValor() > 7)&&(this.getCartas()[2].getValor() > 7)){
			return true;
		}
		return false;
	}
	
	//Funcion para ver si tengo cartas para el reTruco
	public boolean cartaReTruco(){
		if((this.getCartas()[0].getValor() > 9)&&(this.getCartas()[1].getValor() > 9)){
			return true;
		}else if((this.getCartas()[1].getValor() > 9)&&(this.getCartas()[2].getValor() > 9)){
			return true;
		}else if((this.getCartas()[0].getValor() > 9)&&(this.getCartas()[2].getValor() > 9)){
			return true;
		}else if(this.getCartas()[1].getValor() > 10){
			return true;
		}else if(this.getCartas()[0].getValor() > 10){
			return true;
		}else if(this.getCartas()[2].getValor() > 10){
			return true;
		}
		return false;
	}
	
	//Funcion para ver si tengo cartas para el vale 4
	public boolean cartaVale4(){
		if((this.getCartas()[0].getValor() > 10)&&(this.getCartas()[1].getValor() > 10)){
			return true;
		}else if((this.getCartas()[1].getValor() > 10)&&(this.getCartas()[2].getValor() > 10)){
			return true;
		}else if((this.getCartas()[0].getValor() > 10)&&(this.getCartas()[2].getValor() > 10)){
			return true;
		}else if(this.getCartas()[1].getValor() > 10){
			return true;
		}else if(this.getCartas()[0].getValor() > 10){
			return true;
		}else if(this.getCartas()[2].getValor() > 10){
			return true;
		}
		return false;
	}
	
	//Funcion para cuando le cantan falta envido a la maquina
	public boolean cantoFaltaEnvido(boolean envido, boolean envidoEnvido, boolean realEnvido, boolean mentir, Contador contador){
		if(this.puntosMano()>29){
			return true;
		}else{
			int puntosEnJuegos;
			if(envido){
				if(realEnvido){
					puntosEnJuegos = 5;
				}else{
					puntosEnJuegos = 2;
				}
			}else if(envidoEnvido){
				if(realEnvido){
					puntosEnJuegos = 7;
				}else{
					puntosEnJuegos = 4;
				}
			}else if(realEnvido){
				puntosEnJuegos = 3;
			}else{
				puntosEnJuegos = 1;
			}
			int puntosGanarHumano = 30 - contador.getPuntosJug();
			int puntosGanarMaquina = 30 - contador.getPuntosMaq();
			if(puntosGanarHumano <= puntosEnJuegos){
				return true;
			}else if(puntosGanarMaquina <= puntosEnJuegos){
				return true;
			}
		}
		return false;
	}
	
	//funcion para ver que hace la maquina cuando le cantan falta envido
	public boolean cantoRealEnvido(boolean envido, boolean envidoEnvido, Contador contador, boolean mentir, Humano jugadorH){
		if(mentir){
			//Seguir aca mañana. Falta hacer el etodo para que la maquina cante falta envido,
		}
	}
	
}
