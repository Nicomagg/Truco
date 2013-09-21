package jugador;

import Elementos.Carta;

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
	
	//FUncionpara obtener cartas
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
	}
}
