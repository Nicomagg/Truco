package jugador;

import java.util.ArrayList;

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
	public boolean cantarTruco(){
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
	public boolean cantarReTruco(){
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
	public boolean cantarVale4(){
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
			int puntosEnJuegos = contador.faltaEnvidoNoQuerida(envido, envidoEnvido, realEnvido);
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
	
	//funcion para que el maquina cante falta envido
	public void faltaEnvido(boolean envido, boolean envidoEnvido, boolean realEnvido, Humano jugadorH, Contador contador, boolean mentir){
		System.out.println("\n"+this.getNombre()+": Falta Envido");
		//condicional para ver si el humano quiere
		if(jugadorH.cantoFaltaEnvido()){
			System.out.println("\n"+jugadorH.getNombre()+": Quiero");
			int puntosMaquina = this.puntosMano();
			int puntosHumano = jugadorH.obtenerPutnos();
			System.out.println("\n"+this.getNombre()+": "+puntosMaquina+" puntos.");
			System.out.println("\n"+jugadorH.getNombre()+": "+puntosHumano+" puntos.");
			if(puntosMaquina<puntosHumano){
				contador.sumarFaltaEnvido(jugadorH);
			}else if(puntosMaquina>puntosHumano){
				contador.sumarFaltaEnvido(this);
			}else{
				if(this.isMano()){
					contador.sumarFaltaEnvido(this);
				}else{
					contador.sumarFaltaEnvido(jugadorH);
				}
			}
		}else{
			System.out.println("\n"+jugadorH.getNombre()+": No Quiero");
			contador.sumarPuntos(this, contador.faltaEnvidoNoQuerida(envido, envidoEnvido, realEnvido));
		}
	}
	
	//funcion para ver que hace la maquina cuando le cantan falta envido
	public ArrayList<Boolean> cantoRealEnvido(boolean envido, boolean envidoEnvido, Contador contador, boolean mentir, Humano jugadorH){
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		boolean realEnvido = false;
		boolean faltaEnvido = false;
		if(mentir){
			this.faltaEnvido(envido, envidoEnvido, true, jugadorH, contador, mentir);
			faltaEnvido = true;
		}else{
			int puntosEnJuegos = contador.realEnvidoNoQuerido(envido, envidoEnvido);
			int puntosGanarHumano = 30 - contador.getPuntosJug();
			int puntosGanarMaquina = 30 - contador.getPuntosMaq();
			if(puntosGanarHumano <= puntosEnJuegos){
				realEnvido = true;
			}else if(puntosGanarMaquina <= puntosEnJuegos){
				this.faltaEnvido(envido, envidoEnvido, true, jugadorH, contador, mentir);
				faltaEnvido = true;
			}else if(this.puntosMano()>31){
				this.faltaEnvido(faltaEnvido, envidoEnvido, true, jugadorH, contador, mentir);
				faltaEnvido = true;
			}else if(this.puntosMano()>29){
				realEnvido = true;
			}
		}
		respuesta.add(realEnvido);
		respuesta.add(faltaEnvido);
		return respuesta;
	}
	
	//funcion para cantar real Envido la maquina
	public void realEnvido(boolean envido, boolean envidoEnvido, Humano jugadorH, Contador contador, boolean mentir){
		System.out.println("\n"+this.getNombre()+": Real Envido");
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		respuesta = jugadorH.cantoRealEnvido(envido, envidoEnvido, contador, this, mentir);
		//COndicional para ver si se canto falta envido o no
		if(!(respuesta.get(1))){
			//Verifico respuesta humano
			if(respuesta.get(0)){
				System.out.println("\n"+jugadorH.getNombre()+": Quiero");
				int puntosMaquina = this.puntosMano();
				int puntosHumano = jugadorH.obtenerPutnos();
				System.out.println("\n"+this.getNombre()+": "+puntosMaquina+" puntos");
				System.out.println("\n"+jugadorH.getNombre()+": "+puntosHumano+" puntos");
				if(puntosMaquina<puntosHumano){
					contador.sumarPuntos(jugadorH, contador.realEnvioQuerido(envido, envidoEnvido));
				}else if(puntosMaquina>puntosHumano){
					contador.sumarPuntos(this, contador.realEnvioQuerido(envido, envidoEnvido));
				}else{
					if(this.isMano()){
						contador.sumarPuntos(this, contador.realEnvioQuerido(envido, envidoEnvido));
					}else{
						contador.sumarPuntos(jugadorH, contador.realEnvioQuerido(envido, envidoEnvido));
					}
				}
			}else{
				System.out.println("\n"+jugadorH.getNombre()+": No Quiero");
				contador.sumarPuntos(this, contador.realEnvidoNoQuerido(envido, envidoEnvido));
			}
		}
	}
	
	//funcion para que la maquina respona a un envido cantado por el humano
	public ArrayList<Boolean> cantoEnvido(boolean envido, Humano jugadorH, boolean mentir, Contador contador){
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		boolean resp = false;
		boolean realEnvido = false;
		boolean faltaEnvido = false;
		if(!(envido)){
			if(mentir){
				this.envido(true, contador, mentir, jugadorH);
				envido = true;
			}else{
				if((this.puntosMano()<29)&&(this.puntosMano()>24)){
					resp = true;
				}else if((this.puntosMano()<32)&&(this.puntosMano()>28)){
					this.realEnvido(true, false, jugadorH, contador, mentir);
					realEnvido = true;
				}else if((this.puntosMano()<34)&&(this.puntosMano()>31)){
					this.faltaEnvido(true, false, false, jugadorH, contador, mentir);
				}else{
					int puntosGanarHumano = 30 - contador.getPuntosJug();
					int puntosGanarMaquina = 30 - contador.getPuntosMaq();
					if(puntosGanarHumano <= contador.envidoNoQuerido(false)){
						resp = true;
					}else if(puntosGanarMaquina <= contador.envidoNoQuerido(false)){
						this.faltaEnvido(true, false, false, jugadorH, contador, mentir);
						faltaEnvido = true;
					}
				}
			}
		}else{
			if(mentir){
				this.faltaEnvido(false, true, false, jugadorH, contador, mentir);
			}else{
				if((this.puntosMano()<32)&&(this.puntosMano()>28)){
					resp = true;
				}else if((this.puntosMano()<34)&&(this.puntosMano()>31)){
					this.faltaEnvido(false, true, false, jugadorH, contador, mentir);
				}else{
					int puntosGanarHumano = 30 - contador.getPuntosJug();
					int puntosGanarMaquina = 30 - contador.getPuntosMaq();
					if(puntosGanarHumano <= contador.envidoNoQuerido(true)){
						resp = true;
					}else if(puntosGanarMaquina <= contador.envidoNoQuerido(true)){
						this.faltaEnvido(false, true, false, jugadorH, contador, mentir);
						faltaEnvido = true;
					}
				}
			}
		}
		respuesta.add(resp);
		respuesta.add(envido);
		respuesta.add(realEnvido);
		respuesta.add(faltaEnvido);
		return respuesta;
	}
	
	//Funcion para que la maquina cante falta envido
	public void envido(boolean envido, Contador contador, boolean mentir, Humano jugadorH){
		ArrayList<Boolean> respuesta;
		if(!(envido)){
			System.out.println("\n"+this.getNombre()+": Envido");
			respuesta =jugadorH.cantoEnvido(envido, mentir, contador, this);
			if((!(respuesta.get(1)))&&(!(respuesta.get(2)))&&(!(respuesta.get(3)))){
				if(respuesta.get(0)){
					System.out.println("\n"+jugadorH.getNombre()+": Quiero");
					int puntosMaquina = this.puntosMano();
					int puntosHumano = jugadorH.obtenerPutnos();
					System.out.println("\n"+this.getNombre()+": "+puntosMaquina+"puntos");
					System.out.println("\n"+jugadorH.getNombre()+": "+puntosHumano+"puntos");
					if(puntosMaquina<puntosHumano){
						contador.sumarPuntos(jugadorH, contador.envidoQuerido(envido));
					}else if(puntosMaquina>puntosHumano){
						contador.sumarPuntos(this, contador.envidoQuerido(envido));
					}else{
						if(this.isMano()){
							contador.sumarPuntos(this, contador.envidoQuerido(envido));
						}else{
							contador.sumarPuntos(jugadorH, contador.envidoQuerido(envido));
						}
					}
				}else{
					System.out.println("\n"+jugadorH.getNombre()+": No Quiero");
					contador.sumarPuntos(this, contador.envidoNoQuerido(envido));
				}
			}
		}else{
			System.out.println("\n"+this.getNombre()+": Envido Envido");
			respuesta =jugadorH.cantoEnvido(envido, mentir, contador, this);
			if((!(respuesta.get(2)))&&(!(respuesta.get(3)))){
				if(respuesta.get(0)){
					System.out.println("\n"+jugadorH.getNombre()+": Quiero");
					int puntosMaquina = this.puntosMano();
					int puntosHumano = jugadorH.obtenerPutnos();
					System.out.println("\n"+this.getNombre()+": "+puntosMaquina+"puntos");
					System.out.println("\n"+jugadorH.getNombre()+": "+puntosHumano+"puntos");
					if(puntosMaquina<puntosHumano){
						contador.sumarPuntos(jugadorH, contador.envidoQuerido(envido));
					}else if(puntosMaquina>puntosHumano){
						contador.sumarPuntos(this, contador.envidoQuerido(envido));
					}else{
						if(this.isMano()){
							contador.sumarPuntos(this, contador.envidoQuerido(envido));
						}else{
							contador.sumarPuntos(jugadorH, contador.envidoQuerido(envido));
						}
					}
				}else{
					System.out.println("\n"+jugadorH.getNombre()+": No Quiero");
					contador.sumarPuntos(this, contador.envidoNoQuerido(envido));
				}
			}
		}
	}
	
	public ArrayList<Boolean> cantoTruco(Contador contador){
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		boolean reTruco = false;
		boolean resp = false;;
		int puntosGanarHumano = 30 - contador.getPuntosJug();
		if(puntosGanarHumano==29){
			reTruco = true;
		}else{
			if(this.cantarTruco()){
				if(this.cantarReTruco()){
					reTruco = true;
				}else{
					resp = true;
				}
			}else{
				resp = false;
			}
		}
		
		respuesta.add(resp);
		respuesta.add(reTruco);
		return respuesta;
	}
	
	//funcion para que la maquina canta truco
	public ArrayList<Boolean> truco(Contador contador, Humano jugadorH){
		System.out.println("\n"+this.getNombre()+": TRUCO!!!");
		ArrayList<Boolean> respuestaTruco = new ArrayList<Boolean>();
		boolean reTruco = false;
		boolean resp = false;
		ArrayList<Boolean> respuesta = jugadorH.cantoTruco();
		if(!(respuesta.get(1))){
			if(respuesta.get(0)){
				System.out.println("\n"+jugadorH.getNombre()+": Quiero");
				resp = true;
			}else{
				System.out.println("\n"+jugadorH.getNombre()+": No Quiero");
				resp = false;
			}
		}else{
			reTruco = true;
		}
		respuestaTruco.add(resp);
		respuestaTruco.add(reTruco);
		return respuestaTruco;
	}
	
	//funcion para ver que hace la maquina cuando el humano canta re truco
	public ArrayList<Boolean> cantoReTruco(Contador contador, Humano jugadorH, boolean mentir){
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		boolean resp = false;
		boolean vale4 = false;
		int puntosGanarHumano = 30 - contador.getPuntosJug();
		if(puntosGanarHumano>=28){
			vale4 = true;
		}else{
			if(this.cantarVale4()){
				vale4 = true;
			}else{
				if(mentir){
					vale4 = true;
				}else{
					if(this.cantarReTruco()){
						System.out.println("\n"+this.getNombre()+": Quiero!");
						resp = true;
					}else{
						System.out.println("\n"+this.getNombre()+": No Quiero!");
					}
				}
			}
		}
		
		respuesta.add(resp);
		respuesta.add(vale4);
		return respuesta;
	}
	
	//funcion para que la maquina cante reTruco
	public ArrayList<Boolean> reTruco(Contador contador, Humano jugadorH, boolean mentir){
		System.out.println("\n"+this.getNombre()+": QUIERO RETRUCO!!!");
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		ArrayList<Boolean> respHumano = jugadorH.cantoReTruco();
		boolean resp = false;
		boolean vale4 = false;
		if(!(respHumano.get(1))){
			if(respHumano.get(0)){
				System.out.println("\n"+jugadorH.getNombre()+": Quiero");
				resp = true;
			}else{
				System.out.println("\n"+jugadorH.getNombre()+": No Quiero");
			}
		}else{
			vale4 = true;
		}
		
		respuesta.add(resp);
		respuesta.add(vale4);
		return respuesta;
	}
	
	//funcion para ver que hace la maquina cuando le cantan vale 4
	public boolean cantoVale4(Contador contador, Humano jugadorH, boolean mentir){
		if(this.cantarVale4()){
			return true;
		}else{
			int puntosGanarHumano = 30 - contador.getPuntosJug();
			int diferencia = Math.abs(contador.getPuntosJug()-contador.getPuntosMaq());
			if(puntosGanarHumano>=27){
				return true;
			}else{
				if((diferencia>9)&&(this.cantarReTruco())){
					return true;
				}
			}
		}
		return false;
	}

}