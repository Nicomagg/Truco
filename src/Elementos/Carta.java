package Elementos;

public class Carta{
	private int numero;
	private String palo;
	private int valor;
	private boolean habilitada = true;
	
	public Carta(int num, String pal){
		setPalo(pal);
		setNumero(num);
		asignarValor();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPalo() {
		return palo;
	}

	@Override
	public String toString() {
		return  (numero + " " + palo);
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	//Funcion que asiga valor a la carta segun su numero y palo
	private void asignarValor(){
		switch(this.getNumero()){
			case 1:
				switch(this.getPalo()){
					case "Espada":
						this.setValor(14);
						break;
					case "Basto":
						this.setValor(13);
						break;
					default:
						this.setValor(8);
						break;
				}break;
			
			case 7:
				switch(this.getPalo()){
					case "Espada":
						this.setValor(12);
						break;
					case "Oro":
						this.setValor(11);
						break;
					default:
						this.setValor(4);
						break;
				}break;
			
			case 2:
				this.setValor(9);
				break;
			case 3:
				this.setValor(10);
				break;
			case 4:
				this.setValor(1);
				break;
			case 5:
				this.setValor(2);
				break;
			case 6:
				this.setValor(3);
				break;
			case 10:
				this.setValor(5);
				break;
			case 11:
				this.setValor(6);
				break;
			case 12:
				this.setValor(7);
				break;
		}
	}

	//funcion para comparar el valor de dos cartas
	public int campararCartas(Carta c) {
		if(this.getValor() < c.getValor()){
			return -1;
		}else if(this.getValor() > c.getValor()){
			return 1;
		}else{
			return 0;
		}
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}
	
}
