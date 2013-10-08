package jugador;

import java.util.ArrayList;
import java.util.Scanner;

import Elementos.Carta;
import Elementos.Contador;

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
		boolean error = true;
		int num = 0;
		while(error){
			try{
				System.out.println("Ingrese sus puntos: ");
				num = sc.nextInt();
				if(((num>=0)&&(num<=7))||((num>=20)&&(num<=33))){
					error = false;
				}else{
					System.out.println("Error. Los puntos ingresados no son válidos. Si no posee puntos ingrese 0");
				}
			}catch (Exception e){
				System.out.println("Error. El valor ingresado no es un número");
			}
		}
		return num;
	}
	
	//funcion para que el humano cante falta envido
	public void faltaEnvido(boolean envido, boolean envidoEnvido, boolean realEnvido, Maquina jugadorM, Contador contador, boolean mentir){
		System.out.println("\n"+this.getNombre()+": Falta Envido");
		//condicion que verifica si la maquina quiere o no
		if(jugadorM.cantoFaltaEnvido(envido, envidoEnvido, realEnvido, mentir, contador)){
			System.out.println("\n"+jugadorM.getNombre()+": Quiero");
			int puntosHumano = this.obtenerPutnos();
			int puntosMaquina = jugadorM.puntosMano();
			System.out.println("\n"+this.getNombre()+": "+puntosHumano+" puntos");
			System.out.println("\n"+jugadorM.getNombre()+": "+puntosMaquina+" puntos");
			if(puntosHumano < puntosMaquina){
				contador.sumarFaltaEnvido(jugadorM);
			}else if(puntosHumano > puntosMaquina){
				contador.sumarFaltaEnvido(this);
			}else{
				if(this.isMano()){
					contador.sumarFaltaEnvido(this);
				}else{
					contador.sumarFaltaEnvido(jugadorM);
				}
			}
		}else{
			System.out.println("\n"+jugadorM.getNombre()+": No Quiero");
			contador.sumarPuntos(this, contador.faltaEnvidoNoQuerida(envido, envidoEnvido, realEnvido));
		}
	}
	
	//funcion para ver que hace el humano cuando la maquina le canta falta envido
	public boolean cantoFaltaEnvido(){
		boolean error = true;
		int respuesta = 0;
		while(error){
			System.out.print("\n1-Quiero  --  2-No Quiero");
			try{
				respuesta = sc.nextInt();
				if((respuesta<1)&&(respuesta>2)){
					System.out.println("\nError, el valor ingresado no se corresponde al rango dado");
				}else{
					error = false;
				}
			}catch(Exception e){
				System.out.println("\nError, el valor ingresado no es número");
			}
		}		
		if(respuesta == 1){
			return true;
		}else{
			return false;
		}
	}
	
	//funcion para que el humano cante real Envido
	public void realEnvido(boolean envido, boolean envidoEnvido, Maquina jugadorM, boolean mentira, Contador contador){
		System.out.println("\n"+this.getNombre()+": Real Envido");
		ArrayList<Boolean> respuestaMaquina = new ArrayList<Boolean>();
		respuestaMaquina = jugadorM.cantoRealEnvido(envido, envidoEnvido, contador, mentira, this);
		//Condicional para ver si la maquina canto falta envido
		if(!(respuestaMaquina.get(1))){
			//Condicional para ver si quiso la maquina o no
			if(respuestaMaquina.get(0)){
				System.out.println("\n"+jugadorM.getNombre()+": Quiero");
				int puntosHumano = this.obtenerPutnos();
				int puntosMaquina = jugadorM.puntosMano();
				System.out.println("\n"+this.getNombre()+": "+puntosHumano+" puntos");
				System.out.println("\n"+jugadorM.getNombre()+": "+puntosMaquina+" puntos");
				if(puntosHumano<puntosMaquina){
					contador.sumarPuntos(jugadorM, contador.realEnvioQuerido(envido, envidoEnvido));
				}else if(puntosHumano>puntosMaquina){
					contador.sumarPuntos(this, contador.realEnvioQuerido(envido, envidoEnvido));
				}else{
					if(this.isMano()){
						contador.sumarPuntos(this, contador.realEnvioQuerido(envido, envidoEnvido));
					}else{
						contador.sumarPuntos(jugadorM, contador.realEnvioQuerido(envido, envidoEnvido));
					}
				}
			}else{
				contador.sumarPuntos(this,contador.realEnvidoNoQuerido(envido, envidoEnvido));
			}
		}
	}
	
	//funcion para ver uqe hace el humano si la maquina le canta real envido
	public ArrayList<Boolean> cantoRealEnvido(boolean envido, boolean envidoEnvido,Contador contador, Maquina jugadorM, boolean mentir){
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		int resp = 0;
		boolean realEnvido = false;
		boolean faltaEnvido = false;
		boolean error = true;
		while(error){
			try{
				System.out.println("\n1-Quiero  --  2-No Quiero  --  3-Falta Envido");
				resp = sc.nextInt();
				if((resp<1)||(resp>3)){
					System.out.println("\nError, el valor ingresado no corresponde a un número válido");
				}else{
					error = false;
				}
			}catch(Exception e){
				System.out.println("\nError. El valor ingresado no es un número");
			}
		}
		switch(resp){
			case 1:
				realEnvido = true;
				break;
			case 2:
				realEnvido = false;
				break;
			case 3:
				this.faltaEnvido(envido, envidoEnvido, true, jugadorM, contador, mentir);
				faltaEnvido = true;
				break;
		}
		respuesta.add(realEnvido);
		respuesta.add(faltaEnvido);
		return respuesta;
	}
	
	//funcion para que el humano cante envido o envido envido
	public void envido(boolean envido, Contador contador, Maquina jugadorM, boolean mentir){
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		//Verifico si se canto envido o envido envido
		if(!(envido)){
			System.out.println("\n"+this.getNombre()+": Envido");
			respuesta = jugadorM.cantoEnvido(envido, this, mentir, contador);
			//verifico si no se canto envido de nuevo, real envido o falta envido
			if((!(respuesta.get(1)))&&(!(respuesta.get(2)))&&(!(respuesta.get(3)))){
				//verifico respuesta de la maquina
				if(respuesta.get(0)){
					System.out.println("\n"+jugadorM.getNombre()+": Quiero");
					int puntosHumano = this.obtenerPutnos();
					int puntosMaquina = jugadorM.puntosMano();
					System.out.println("\n"+this.getNombre()+": "+puntosHumano+" puntos");
					System.out.println("\n"+jugadorM.getNombre()+": "+puntosMaquina+" puntos");
					if(puntosHumano < puntosMaquina){
						contador.sumarPuntos(jugadorM, contador.envidoQuerido(false));
					}else if(puntosHumano > puntosMaquina){
						contador.sumarPuntos(this, contador.envidoQuerido(false));
					}else{
						if(this.isMano()){
							contador.sumarPuntos(this, contador.envidoQuerido(false));
						}else{
							contador.sumarPuntos(jugadorM, contador.envidoQuerido(false));
						}
					}
				}else{
					System.out.println("\n"+jugadorM.getNombre()+": No Quiero");
					contador.sumarPuntos(this, contador.envidoNoQuerido(false));
				}
			}
		}else{
			System.out.println("\n"+this.getNombre()+": Envido Envido");
			respuesta = jugadorM.cantoEnvido(envido, this, mentir, contador);
			//verifico si no se canto envido de nuevo, real envido o falta envido
			if((!(respuesta.get(2)))&&(!(respuesta.get(3)))){
				//verifico respuesta de la maquina
				if(respuesta.get(0)){
					System.out.println("\n"+jugadorM.getNombre()+": Quiero");
					int puntosHumano = this.obtenerPutnos();
					int puntosMaquina = jugadorM.puntosMano();
					System.out.println("\n"+this.getNombre()+": "+puntosHumano+" puntos");
					System.out.println("\n"+jugadorM.getNombre()+": "+puntosMaquina+" puntos");
					if(puntosHumano < puntosMaquina){
						contador.sumarPuntos(jugadorM, contador.envidoQuerido(true));
					}else if(puntosHumano > puntosMaquina){
						contador.sumarPuntos(this, contador.envidoQuerido(true));
					}else{
						if(this.isMano()){
							contador.sumarPuntos(this, contador.envidoQuerido(true));
						}else{
							contador.sumarPuntos(jugadorM, contador.envidoQuerido(true));
						}
					}
				}else{
					System.out.println("\n"+jugadorM.getNombre()+": No Quiero");
					contador.sumarPuntos(this, contador.envidoNoQuerido(true));
				}
			}
		}
	}
	
	//funcion para ver que hace el humano cuando la maquina canta envido o envido envido
	public ArrayList<Boolean> cantoEnvido(boolean envido, boolean mentir, Contador contador, Maquina jugadorM){
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		boolean resp = false;
		int respuestaHumano = 0;
		boolean realEnvido = false;
		boolean faltaEnvido = false;
		boolean error = true;
		if(!(envido)){
			while(error){
				try{
					System.out.println("1-Quiero  --  2-No Quiero  --  3-Envido  --  4-Real Envido  --  5-Falta Envido");
					respuestaHumano = sc.nextInt();
					if((respuestaHumano<1)&&(respuestaHumano>5)){
						System.out.println("\nError, el valor ingresado no corresponde a un número válido.");
					}else{
						error = false;
					}
				}catch(Exception e){
					System.out.println("\nError, el valor ingresado no es un número");
				}
			}
			switch(respuestaHumano){
				case 1:
					resp = true;
					break;
				case 2:
					resp = false;
					break;
				case 3:
					this.envido(false, contador, jugadorM, mentir);
					envido = true;
					break;
				case 4:
					this.realEnvido(true, false, jugadorM, mentir, contador);
					realEnvido = true;
					break;
				case 5:
					this.faltaEnvido(true, false, false, jugadorM, contador, mentir);
					faltaEnvido = true;
					break;
			}
		}else{
			while(error){
				try{
					System.out.println("1-Quiero  --  2-No Quiero  --  3-Real Envido  --  4-Falta Envido");
					respuestaHumano = sc.nextInt();
					if((respuestaHumano<1)&&(respuestaHumano>4)){
						System.out.println("\nError, el valor ingresado no corresponde a un número válido.");
					}else{
						error = false;
					}
				}catch(Exception e){
					System.out.println("\nError, el valor ingresado no es un número");
				}
			}
			switch(respuestaHumano){
				case 1:
					resp = true;
					break;
				case 2:
					resp = false;
					break;
				case 3:
					this.realEnvido(true, false, jugadorM, mentir, contador);
					realEnvido = true;
					break;
				case 4:
					this.faltaEnvido(true, false, false, jugadorM, contador, mentir);
					faltaEnvido = true;
					break;
			}
		}
		
		respuesta.add(resp);
		respuesta.add(envido);
		respuesta.add(realEnvido);
		respuesta.add(faltaEnvido);
		
		return respuesta;
	}
	
	//funcion para que el humano cante truco
	public ArrayList<Boolean> truco(Contador contador, Maquina jugadorM){
		System.out.println("\n"+this.getNombre()+": TRUCO!!!");
		ArrayList<Boolean> respuestaTruco = new ArrayList<Boolean>();
		ArrayList<Boolean> respuesta = jugadorM.cantoTruco(contador);
		boolean resp = false;
		boolean reTruco = false;
		if(!(respuesta.get(1))){
			if(respuesta.get(0)){
				System.out.println("\n"+jugadorM.getNombre()+": Quiero");
				resp = true;
			}else{
				System.out.println("\n"+jugadorM.getNombre()+": No Quiero");
				resp = false;
			}
		}else{
			reTruco = true;
		}
		respuestaTruco.add(resp);
		respuestaTruco.add(reTruco);
		return respuestaTruco;
	}
	
	//FUncion para ver que hace la maquina cuando la aquina canta truco
	public ArrayList<Boolean> cantoTruco(){
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		boolean reTruco = false;
		int resp = 0;
		boolean respBoolean = false;
		boolean error = true;
		
		while(error){
			try{
				System.out.println("\n1-Quiero  --  2-No Quiero  --  3-QUIERO RETRUCO!!!");
				resp = sc.nextInt();
				if((resp<1)||(resp>3)){
					System.out.println("\nError. El valor ingresado no corresponde a un número válido.");
				}else{
					error = false;
				}
			}catch(Exception e){
				System.out.println("\nError. El valor ingresado no es un número");
			}
		}
		
		switch(resp){
			case 1:
				respBoolean = true;
				break;
			case 2:
				respBoolean = false;
				break;
			case 3:
				reTruco = true;
		}
		
		respuesta.add(respBoolean);
		respuesta.add(reTruco);
		return respuesta;
	}
	
	//Funcion para que el humano cante reTruco
	public ArrayList<Boolean> reTruco(Contador contador, Maquina jugadorM, boolean mentir){
		System.out.println("\n"+this.getNombre()+": QUIERO RETRUCO!!!");
		ArrayList<Boolean> respReTruco = new ArrayList<Boolean>();
		ArrayList<Boolean> respMaquina = jugadorM.cantoReTruco(contador, this, mentir);
		boolean respMaquinaReTruco = false;
		boolean vale4 = false;
		if(!(respMaquina.get(1))){
			if(respMaquina.get(0)){
				respMaquinaReTruco = true;
			}
		}else{
			vale4 = true;
		}
		
		respReTruco.add(respMaquinaReTruco);
		respReTruco.add(vale4);
		return respReTruco;
	}
	
	//Funcion para ver que hace la maquina cuando el jugador canta reTruco
	public ArrayList<Boolean> cantoReTruco(){
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		boolean resp = false;
		int respNum = 0;
		boolean vale4 = false;
		boolean error = true;
		
		while(error){
			try{
				System.out.println("\n1-Quiero  --  2-No Quiero  --  3-QUIERO VALE 4!!!");
				respNum = sc.nextInt();
				if((respNum<1)&&(respNum>3)){
					System.out.println("\nError, el valor ingresado no corresponde a un número válido");
				}else{
					error = false;
				}
			}catch(Exception e){
				System.out.println("\nError. El valor ingresado no es un número");
			}
		}
		
		switch(respNum){
			case 1:
				resp = true;
				break;
			case 2:
				resp = false;
				break;
			case 3:
				vale4 = true;
				break;
		}
		
		respuesta.add(resp);
		respuesta.add(vale4);
		return respuesta;
	}
	
	//Funcion para que el humano cante vale 4
	public ArrayList<Boolean> vale4(Contador contador, Maquina jugadorM, boolean mentir){
		ArrayList<Boolean> respuesta = new ArrayList<Boolean>();
		ArrayList<Boolean> respMaquina;
	}
}
