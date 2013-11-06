package jugador;

import java.util.ArrayList;
import java.util.Scanner;

import Elementos.Carta;
import Elementos.Contador;

public class Humano extends Jugador{

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
		boolean error = true;
		int num = 0;
		int realNum = 0;
		//Verifico los puntos del humano
		if((this.getCartas()[0].getPalo() == this.getCartas()[1].getPalo())&&(this.getCartas()[0].getPalo() == this.getCartas()[2].getPalo())){
			if((this.getCartas()[0].getNumero()<10)&&(this.getCartas()[1].getNumero()<10)&&(this.getCartas()[2].getNumero()<10)){
				if((this.getCartas()[0].getNumero()>this.getCartas()[1].getNumero())&&(this.getCartas()[0].getNumero()>this.getCartas()[2].getNumero())){
					if(this.getCartas()[1].getNumero()>this.getCartas()[2].getNumero()){
						realNum = 20 + this.getCartas()[0].getNumero() + this.getCartas()[1].getNumero();
					}else{
						realNum = 20 + this.getCartas()[0].getNumero() + this.getCartas()[2].getNumero();
					}
				}else if((this.getCartas()[1].getNumero()>this.getCartas()[0].getNumero())&&(this.getCartas()[1].getNumero()>this.getCartas()[2].getNumero())){
					if(this.getCartas()[0].getNumero()>this.getCartas()[2].getNumero()){
						realNum = 20 + this.getCartas()[1].getNumero() + this.getCartas()[0].getNumero();
					}else{
						realNum = 20 + this.getCartas()[1].getNumero() + this.getCartas()[2].getNumero();
					}
				}else if((this.getCartas()[2].getNumero()>this.getCartas()[0].getNumero())&&(this.getCartas()[2].getNumero()>this.getCartas()[1].getNumero())){
					if(this.getCartas()[0].getNumero()>this.getCartas()[1].getNumero()){
						realNum = 20 + this.getCartas()[2].getNumero() + this.getCartas()[0].getNumero();
					}else{
						realNum = 20 + this.getCartas()[2].getNumero() + this.getCartas()[1].getNumero();
					}
				}
				//Sigue aca. Tengo que ver si hay un 10 entre las cartas y eso
			}
		}else if((this.getCartas()[0].getPalo() == this.getCartas()[1].getPalo())){
			if((this.getCartas()[0].getNumero()<10)&&(this.getCartas()[1].getNumero()<10)){
				realNum = (20 + this.getCartas()[0].getNumero() + this.getCartas()[1].getNumero());
			}else if((this.getCartas()[0].getNumero()>=10)&&(this.getCartas()[1].getNumero()<10)){ 
				realNum = (20 + this.getCartas()[1].getNumero());
			}else if((this.getCartas()[0].getNumero()<10)&&(this.getCartas()[1].getNumero()>=10)){
				realNum = (20 + this.getCartas()[0].getNumero());
			}else{
				realNum = 20;
			}
		}else if((this.getCartas()[0].getPalo() == this.getCartas()[2].getPalo())){
			if((this.getCartas()[0].getNumero()<10)&&(this.getCartas()[2].getNumero()<10)){
				realNum = (20 + this.getCartas()[0].getNumero() + this.getCartas()[2].getNumero());
			}else if((this.getCartas()[0].getNumero()>=10)&&(this.getCartas()[2].getNumero()<10)){ 
				realNum = (20 + this.getCartas()[2].getNumero());
			}else if((this.getCartas()[0].getNumero()<10)&&(this.getCartas()[2].getNumero()>=10)){
				realNum = (20 + this.getCartas()[0].getNumero());
			}else{
				realNum = 20;
			}
		}else if((this.getCartas()[1].getPalo() == this.getCartas()[2].getPalo())){
			if((this.getCartas()[1].getNumero()<10)&&(this.getCartas()[2].getNumero()<10)){
				realNum = (20 + this.getCartas()[1].getNumero() + this.getCartas()[2].getNumero());
			}else if((this.getCartas()[1].getNumero()>=10)&&(this.getCartas()[2].getNumero()<10)){ 
				realNum = (20 + this.getCartas()[2].getNumero());
			}else if((this.getCartas()[1].getNumero()<10)&&(this.getCartas()[2].getNumero()>=10)){
				realNum = (20 + this.getCartas()[1].getNumero());
			}else{
				realNum = 20;
			}
		}else{
			int max = 0;
			for(int i = 0; i < 3; i++){
				if((this.getCartas()[i].getNumero()>max) && (this.getCartas()[i].getNumero()<10)){
					max = this.getCartas()[i].getNumero();
				}
			}
			realNum = max;
		}
		
		while(error){
			Scanner sc = new Scanner(System.in);
			try{
				System.out.print("Ingrese sus puntos: ");
				num = sc.nextInt();
				if(((num>=0)&&(num<=7))||((num>=20)&&(num<=33))){
					error = false;
				}else{
					System.out.println("Error. Los puntos ingresados no son válidos. Si no posee puntos ingrese 0");
				}
			}catch (Exception e){
				System.out.println("Error. El valor ingresado no es un número");
				System.exit(1);
			}
		}
		//Verifico si lo ingresado por el usuario es realmente el numero
		if(!(realNum==num)){
			return -1;
		}
		return num;
	}
	
	//funcion para que el humano cante falta envido
	public void faltaEnvido(boolean envido, boolean envidoEnvido, boolean realEnvido, Maquina jugadorM, Contador contador, boolean mentir){
		System.out.println("\n"+this.getNombre()+": Falta Envido");
		//condicion que verifica si la maquina quiere o no
		if(jugadorM.cantoFaltaEnvido(envido, envidoEnvido, realEnvido, mentir, contador)){
			System.out.println(jugadorM.getNombre()+": Quiero");
			int puntosHumano = this.obtenerPutnos();
			int puntosMaquina = jugadorM.puntosMano();
			System.out.println("\n"+this.getNombre()+": "+puntosHumano+" puntos");
			System.out.println(jugadorM.getNombre()+": "+puntosMaquina+" puntos");
			//verifico si el humano no canto mal los puntos
			if(!(puntosHumano==-1)){
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
				System.out.println("Error "+this.getNombre()+". Puntos mal cantados, rabón perdido.");
				contador.sumarPuntos(jugadorM, 2);
				contador.sumarFaltaEnvido(jugadorM);
				jugadorM.setManoGanada(2);
			}
		}else{
			System.out.println(jugadorM.getNombre()+": No Quiero");
			contador.sumarPuntos(this, contador.faltaEnvidoNoQuerida(envido, envidoEnvido, realEnvido));
		}
	}
	
	//funcion para ver que hace el humano cuando la maquina le canta falta envido
	public boolean cantoFaltaEnvido(){
		boolean error = true;
		int respuesta = 0;
		while(error){
			Scanner sc = new Scanner(System.in);
			System.out.print("\n1-Quiero  --  2-No Quiero \nRespuesta:");
			try{
				respuesta = sc.nextInt();
				if((respuesta<1)&&(respuesta>2)){
					System.out.println("\nError, el valor ingresado no se corresponde al rango dado");
				}else{
					error = false;
				}
			}catch(Exception e){
				System.out.println("\nError, el valor ingresado no es número");
				System.exit(1);
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
				System.out.println(jugadorM.getNombre()+": Quiero");
				int puntosHumano = this.obtenerPutnos();
				int puntosMaquina = jugadorM.puntosMano();
				System.out.println("\n"+this.getNombre()+": "+puntosHumano+" puntos");
				System.out.println(jugadorM.getNombre()+": "+puntosMaquina+" puntos");
				//Verifico si no canto mal los puntos
				if(!(puntosHumano==-1)){
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
					System.out.println("Error "+this.getNombre()+". Puntos mal cantados, rabón perdido.");
					contador.sumarPuntos(jugadorM, 2);
					contador.sumarPuntos(jugadorM, contador.realEnvioQuerido(envido, envidoEnvido));
					jugadorM.setManoGanada(2);
				}
			}else{
				System.out.println(jugadorM.getNombre()+": No Quiero");
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
			Scanner sc = new Scanner(System.in);
			try{
				System.out.print("\n1-Quiero  --  2-No Quiero  --  3-Falta Envido \nRespuesta:");
				resp = sc.nextInt();
				if((resp<1)||(resp>3)){
					System.out.println("\nError, el valor ingresado no corresponde a un número válido");
				}else{
					error = false;
				}
			}catch(Exception e){
				System.out.println("\nError. El valor ingresado no es un número");
				System.exit(1);
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
					System.out.println(jugadorM.getNombre()+": Quiero");
					int puntosHumano = this.obtenerPutnos();
					int puntosMaquina = jugadorM.puntosMano();
					System.out.println("\n"+this.getNombre()+": "+puntosHumano+" puntos");
					System.out.println(jugadorM.getNombre()+": "+puntosMaquina+" puntos");
					//Verifico si el humano no canto mas los puntos
					if(!(puntosHumano==-1)){
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
						System.out.println("Error "+this.getNombre()+". Puntos mal cantados, rabón perdido.");
						contador.sumarPuntos(jugadorM, 2);
						contador.sumarPuntos(jugadorM, contador.envidoQuerido(false));
						jugadorM.setManoGanada(2);
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
					System.out.println(jugadorM.getNombre()+": Quiero");
					int puntosHumano = this.obtenerPutnos();
					int puntosMaquina = jugadorM.puntosMano();
					System.out.println("\n"+this.getNombre()+": "+puntosHumano+" puntos");
					System.out.println(jugadorM.getNombre()+": "+puntosMaquina+" puntos");
					//Verifico si el humano no canto mal los puntos
					if(!(puntosHumano==-1)){
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
						System.out.println("Error "+this.getNombre()+". Puntos mal cantados, rabón perdido.");
						contador.sumarPuntos(jugadorM, 2);
						contador.sumarPuntos(jugadorM, contador.envidoQuerido(true));
						jugadorM.setManoGanada(2);
					}
				}else{
					System.out.println(jugadorM.getNombre()+": No Quiero");
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
				Scanner sc = new Scanner(System.in);
				try{
					System.out.print("1-Quiero  --  2-No Quiero  --  3-Envido  --  4-Real Envido  --  5-Falta Envido \nRespuesta:");
					respuestaHumano = sc.nextInt();
					if((respuestaHumano<1)&&(respuestaHumano>5)){
						System.out.println("\nError, el valor ingresado no corresponde a un número válido.");
					}else{
						error = false;
					}
				}catch(Exception e){
					System.out.println("\nError, el valor ingresado no es un número");
					System.exit(1);
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
				Scanner sc = new Scanner(System.in);
				try{
					System.out.print("1-Quiero  --  2-No Quiero  --  3-Real Envido  --  4-Falta Envido \nRespuesta:");
					respuestaHumano = sc.nextInt();
					if((respuestaHumano<1)&&(respuestaHumano>4)){
						System.out.println("\nError, el valor ingresado no corresponde a un número válido.");
					}else{
						error = false;
					}
				}catch(Exception e){
					System.out.println("\nError, el valor ingresado no es un número");
					System.exit(1);
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
			Scanner sc = new Scanner(System.in);
			try{
				System.out.print("\n1-Quiero  --  2-No Quiero  --  3-QUIERO RETRUCO!!! \nRespuesta:");
				resp = sc.nextInt();
				if((resp<1)||(resp>3)){
					System.out.println("\nError. El valor ingresado no corresponde a un número válido.");
				}else{
					error = false;
				}
			}catch(Exception e){
				System.out.println("\nError. El valor ingresado no es un número");
				System.exit(1);
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
			Scanner sc = new Scanner(System.in);
			try{
				System.out.print("\n1-Quiero  --  2-No Quiero  --  3-QUIERO VALE 4!!! \nRespuesta:");
				respNum = sc.nextInt();
				if((respNum<1)&&(respNum>3)){
					System.out.println("\nError, el valor ingresado no corresponde a un número válido");
				}else{
					error = false;
				}
			}catch(Exception e){
				System.out.println("\nError. El valor ingresado no es un número");
				System.exit(1);
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
	public boolean vale4(Contador contador, Maquina jugadorM, boolean mentir){
		boolean resp = jugadorM.cantoVale4(contador, this, mentir);
		if(resp){
			System.out.println("\n"+jugadorM.getNombre()+": Quiero");
			return true;
		}else{
			System.out.println("\n"+jugadorM.getNombre()+": No Quiero");
			return false;
		}
	}
	
	//Funcion para ver que hace el humano cuando le cantan vale 4
	public boolean cantoVale4(){
		boolean error = true;
		int resp = 0;
		while(error){
			Scanner sc = new Scanner(System.in);
			try{
				System.out.print("\n1-Quiero  --  2-No Quiero \nRespuesta:");
				resp = sc.nextInt();
				if((resp<1)||(resp>2)){
					System.out.println("\nError. El valor ingresado no corresponde a una opción válida");
				}else{
					error = false;
				}
			}catch(Exception e){
				System.out.println("\nError. El valor ingresado no es un número");
				System.exit(1);
			}
		}
		
		if(resp == 1){
			return true;
		}
		
		return false;
	}
	
	//metodo para pedir que el jugador juegue una carta
	public int pedirCarta(boolean prim,boolean seg,boolean terc,boolean truco,boolean reTruco,boolean vale4){
		boolean error = true;
		int resp = 0;
		//Verifico que no se ingrese cualquier cosa
		while(error){
			Scanner sc = new Scanner(System.in);
			try{
				if((!(truco))&&(!(reTruco))&&(!(vale4))){
					if(prim && seg && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Jugar 3ra Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
						}
					}else if(prim && seg){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>2)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
						}
					}else if(seg && terc){
						System.out.print("\n1-Jugar 2da Carta \n2-Jugar 3ra Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>2)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==1){
								resp = 2;
							}else{
								resp = 3;
							}
						}
					}else if(prim && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 3ra Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>2)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==2){
								resp = 3;
							}
						}
					}else if(prim){
						System.out.print("\n1-Jugar 1ra Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if(!(resp==1)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
						}
					}else if(seg){
						System.out.print("\n1-Jugar 2da Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if(!(resp==1)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							resp = 2;
						}
					}else if(terc){
						System.out.print("\n3-Jugar 3ra Carta");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if(!(resp==3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							resp = 3;
						}
					}
				}else if(truco){
					if(prim && seg && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Jugar 3ra Carta \n4-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>4)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==4){
								resp = 7;
							}
						}
					}else if(prim && seg){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==3){
								resp = 7;
							}
						}
					}else if(seg && terc){
						System.out.print("\n1-Jugar 2da Carta \n2-Jugar 3ra Carta \n3-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							switch(resp){
								case 1:
									resp = 2;
									break;
								case 2:
									resp = 3;
									break;
								case 3:
									resp = 7;
									break;
							}
						}
					}else if(prim && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 3ra Carta \n3-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==2){
								resp = 3;
							}else if(resp == 3){
								resp = 7;
							}
						}
					}else if(prim){
						System.out.print("\n1-Jugar 1ra Carta \n2-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))==(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp == 2){
								resp = 7;
							}
						}
					}else if(seg){
						System.out.print("\n1-Jugar 2da Carta \n2-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))||(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp == 1){
								resp = 2;
							}else{
								resp = 7;
							}
						}
					}else if(terc){
						System.out.print("\n1-Jugar 3ra Carta \n2-Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))||(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==1){
								resp=3;
							}else{
								resp = 7;
							}
						}
					}
				}else if(reTruco){
					if(prim && seg && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Jugar 3ra Carta \n4-Re Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>4)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==4){
								resp = 8;
							}
						}
					}else if(prim && seg){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Re Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==3){
								resp = 8;
							}
						}
					}else if(seg && terc){
						System.out.print("\n1-Jugar 2da Carta \n2-Jugar 3ra Carta \n3-Re Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							switch(resp){
								case 1:
									resp = 2;
									break;
								case 2:
									resp = 3;
									break;
								case 3:
									resp = 8;
									break;
							}
						}
					}else if(prim && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 3ra Carta \n3-Re Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==2){
								resp = 3;
							}else if(resp == 3){
								resp = 8;
							}
						}
					}else if(prim){
						System.out.print("\n1-Jugar 1ra Carta \n2-Re Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))==(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp == 2){
								resp = 8;
							}
						}
					}else if(seg){
						System.out.print("\n1-Jugar 2da Carta \n2-Re Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))||(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp == 1){
								resp = 2;
							}else{
								resp = 8;
							}
						}
					}else if(terc){
						System.out.print("\n1-Jugar 3ra Carta \n2-Re Truco");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))||(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==1){
								resp=3;
							}else{
								resp = 8;
							}
						}
					}
				}else if(vale4){
					if(prim && seg && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Jugar 3ra Carta \n4-Vale 4");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>4)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==4){
								resp = 9;
							}
						}
					}else if(prim && seg){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 2da Carta \n3-Vale 4");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==3){
								resp = 9;
							}
						}
					}else if(seg && terc){
						System.out.print("\n1-Jugar 2da Carta \n2-Jugar 3ra Carta \n3-Vale 4");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							switch(resp){
								case 1:
									resp = 2;
									break;
								case 2:
									resp = 3;
									break;
								case 3:
									resp = 9;
									break;
							}
						}
					}else if(prim && terc){
						System.out.print("\n1-Jugar 1ra Carta \n2-Jugar 3ra Carta \n3-Vale 4");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((resp<1)||(resp>3)){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==2){
								resp = 3;
							}else if(resp == 3){
								resp = 9;
							}
						}
					}else if(prim){
						System.out.print("\n1-Jugar 1ra Carta \n2-Vale 4");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))==(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp == 2){
								resp = 9;
							}
						}
					}else if(seg){
						System.out.print("\n1-Jugar 2da Carta \n2-Vale 4");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))||(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp == 1){
								resp = 2;
							}else{
								resp = 9;
							}
						}
					}else if(terc){
						System.out.print("\n1-Jugar 3ra Carta \n2-Vale 4");
						System.out.print("\nRespuesta: ");
						resp = sc.nextInt();
						if((!(resp==1))||(!(resp==2))){
							System.out.println("\nError. El valor ingresado no corresponde a un número válido");
						}else{
							error = false;
							if(resp==1){
								resp=3;
							}else{
								resp = 9;
							}
						}
					}
				}
			}catch(Exception e){
				System.out.println("\nError. El valor ingresado no corresponde a un número");
			}
		}
		return resp;
	}
	
}