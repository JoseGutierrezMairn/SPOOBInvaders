
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package aplicacion;

import java.applet.AudioClip;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase SPOOBInvaders
 */
public class SPOOBInvaders implements Serializable{
	private static final long serialVersionUID = 1L;
	private static ArrayList<Jugador> jugadores;
	private static Objeto[][] invasores;
	private static Platillo platillo;
	private int dir;
	private int nextDir;
	private int ancho;
	private boolean arriba;
	private boolean temp;
	private int cont;
	private int number;
	private int disparos;
	private Objeto[] barreras;
	private static  SPOOBInvaders spi;
	private boolean golpe;
	private int rojos;
	private int jug;
	private int mov;
	
	/**
	 * Constructor para Objetos de la Clase SPOOBInvaders
	 * @param numJugadores int
	 * @param ancho int
	 */
	public SPOOBInvaders(int numJugadores, int ancho) {
		disparos=0;
		this.ancho= ancho;
		cont=0;
		number = 0;
		arriba= false;
		temp= false;
		spi = this;
		golpe = false;
		rojos = 0;
		agregueJugadores(numJugadores);
		hagaMatriz();
		hagaBarreras();
		hagaPlatillo();
		corrijaPosBarreras();
		dir= 1;
		nextDir=-5;
		jug = numJugadores;
		mov = 0;
	}
	
	public void hagaBarreras() {
		barreras= new Objeto[4];
		String[] l= new String[] {"v", "r"};
		for(int i=0; i<4; i++) {
			int num = (int) (Math.random() * 2) ;
			if(l[num].equals("v")) {
				barreras[i]= new BarreraVerde(new int[] {0,0});
			}else {
				barreras[i]= new BarreraRoja(new int[] {0,0});
			}
			barreras[i].setY(450);
			barreras[i].setVisible(true);
		}
	}
	
	public int numJug() {
		return jug;
	}

	
	public void corrijaPosBarreras() {
		barreras[0].setX(0);
		barreras[1].setX(250);
		barreras[2].setX(500);
		barreras[3].setX(ancho-100);
	}
	
	public Objeto[] getBarreras() {
		return barreras;
	}
	
	/**
	 * Agrega jugadores al juego
	 * @param numJugadores int
	 */
	public void agregueJugadores(int numJugadores) {
		jugadores= new ArrayList<Jugador>();
		int[] pos= new int[] {0,600};
		if(numJugadores <= 2) {
			for(int i=0; i < numJugadores; i++) {
				if(i==1) {
					pos[0]= ancho-70; 
				}
				jugadores.add(new Jugador(i, ancho, pos));	
				jugadores.get(i).cambieStringImagen(i);
			}
		}else if(numJugadores >= 3) {
			for(int i=0; i < 2; i++) {
				if(i==1) {
					pos[0]= ancho-70; 
				}
				jugadores.add(new Jugador(i, ancho, pos));	
				jugadores.get(i).cambieStringImagen(i);
				
			}
		}
	}
	
	/**
	 * crea la matriz de los invasores en el juego
	 */
	public void hagaMatriz() {
		invasores= new Objeto[5][12];
		int y= 100;
		int x= 100;
		for(int i=0; i<5; i++) {
			for(int j=0; j<12; j++) {
				invasores[i][j]= new Calamar(new int[] {x,y});
				invasores[1][j]= new Cangrejo(new int[] {x,150});
				invasores[2][j]= new Cangrejo(new int[] {x,200});
				invasores[3][j]= new Pulpo(new int[] {x,240});
				invasores[4][j]= new Pulpo(new int[] {x,290});
				x+=50;
			}
			x=100;
			y+=50;
		}
		
	}
	
	/**
	 * 
	 */
	public void hagaPlatillo() {
		int y = 60;
		int x = 3000;
		platillo = new Platillo(new int[] {x,y});
		
	}
	
	/**
	 * realiza el disparo del jugador
	 * @param num int
	 */
	public void jugadorDispara(int num) {
		if(!jugadores.get(num).getDisparo().esVisible() && rojos > 0) {
			jugadores.get(num).dispare("rojo");
			rojos-=1;
		}else if(!jugadores.get(num).getDisparo().esVisible()){
			jugadores.get(num).dispare("normal");
		}
		 //AudioClip audio= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/shoot.wav"));
		 //audio.play();
	}
	
	/**
	 * 
	 * @param ancho
	 */
	public void simuleJugador(int ancho) {
		int num = (int) (Math.random()*2)+1;
		for(int i = 0 ; i<=6; i++) {
			if((jugadores.get(1).getX() <= ancho-65) && (num == 1)){
				jugadores.get(1).setX(1);
				jugadorDispara(1);
			}if((jugadores.get(1).getX() >= +5) && (num == 2)) {
				jugadores.get(1).setX(-1);
				jugadorDispara(1);
			}
		}
	}
	
	/**
	 * realiza el movimiento del jugador
	 * @param x int 
	 * @param i int
	 * @param ancho int
	 */
	public void muevaJugador(int x, int i, int ancho) {
		if(numJug() == 3) {
			if(i == 0) {
				if(jugadores.get(0).getX()+x >= 0 && jugadores.get(0).getX()+x <= ancho - 61) {
					jugadores.get(0).setX(x);
				}
			}
		}
		if(numJug() != 3) {
			if(jugadores.get(i).getX()+x >= 0 && jugadores.get(i).getX()+x <= ancho - 61) {
				jugadores.get(i).setX(x);
			}
		}
		
	}
	
	/**
	 * realiza el movimiento del invasor a la izquierda
	 */
	public void muevaIzquierda() {
		for(int i=0; i<5; i++) {
			for(int j=0; j<12; j++) {
				Objeto inv= invasores[i][j];
				if(inv.getX()>=10) {
					inv.setX(-1);
					String nombre= inv.getName();
					if(arriba) {
						inv.setStringImagen("src/imagenes/"+nombre+"Arriba.png");
						if(nombre == "cangrejo" && golpe && inv.getResiste() == 1) {
							inv.setStringImagen("src/imagenes/"+nombre+"ArribaAmarillo.png");
							temp=false;
							cont++;
						}if(nombre == "calamar" && golpe && inv.getResiste() == 1) {
							inv.setStringImagen("src/imagenes/"+nombre+"ArribaRojo.png");
							temp=false;
							cont++;
						}if(nombre == "calamar" && golpe && inv.getResiste() == 2) {
							inv.setStringImagen("src/imagenes/"+nombre+"ArribaAmarillo.png");
							temp=false;
							cont++;
						}
						temp=false;
						cont++;
					}else {
						inv.setStringImagen("src/imagenes/"+nombre+".png");
						if(nombre == "cangrejo" && golpe && inv.getResiste() == 1) {
							inv.setStringImagen("src/imagenes/"+nombre+"Amarillo.png");
							temp=true;
							cont++;
						}if(nombre == "calamar" && golpe && inv.getResiste() == 1) {
							inv.setStringImagen("src/imagenes/"+nombre+"Rojo.png");
							temp=true;
							cont++;
						}if(nombre == "calamar" && golpe && inv.getResiste() == 2) {
							inv.setStringImagen("src/imagenes/"+nombre+"Amarillo.png");
							temp=true;
							cont++;
						}
						temp= true;
						cont++;
					}
				}else {
					dir= -5;
					nextDir=1;
				}
			}
		}
	}
	
	
	/**
	 * realiza el movimiento de los invasores
	 * @param ancho int
	 */
	public void muevaInvasores(int ancho) {
		
		if(dir==1) {
			muevaALaDerecha(ancho);
		}else if(dir==-5) {
			muevaAbajo();
		}else if(dir==-1) {
			muevaIzquierda();
		}
		if(temp&&cont>=1800) {
			arriba=true;
			cont=0;
		}else if((!temp) && cont>=1800){
			arriba=false;
			cont=0;
		}
	}
	
	public void mover() {
		Platillo p = platillo;
		if(p.getX()>=-1000 && p.esVisible()) {
			p.setX(-2);
			number++;
		}if(p.getX() <= -1000 && p.esVisible()) {
			this.hagaPlatillo();
			number++;
		}
	}
	
	/**
	 * Realiza el movimiento del platillo de Izquierda a Derecha
	 * @param ancho int
	 */
	public void muevaPlatillo(int ancho) {
		mover();
		if(platillo.vivo && number>=3000) {
			number = 0;
		}
	}
	
	/**
	 * retorna si se ha disparado 
	 * @return answ boolean
	 */
	public boolean hanDisparado() {
		boolean answ= false;
		for (Jugador j: jugadores) {
			if(j.getDisparo().esVisible()) {
				answ= true;
			}
		}
		return answ;
	}
	
	/**
	 * indica cuando han golpeado a un invasor y determina si este muere al acavar su valor de su resistes
	 * @param j
	 */
	public void mireInvasores(Jugador j){
		for(int i=4; i>=0; i--) {
			for(int col=11; col>=0; col--) {
				if(j.getDisparo().getName().equals("normal") && j.getDisparo().getX()+5 >= invasores[i][col].getX() && j.getDisparo().getX()+5 <= invasores[i][col].getX()+60 && (j.getDisparo().getY() <=  invasores[i][col].getY()+30 && j.getDisparo().getY() >=  invasores[i][col].getY())){
					if(invasores[i][col].esVisible() &&j.getDisparo().esVisible()) {
						invasores[i][col].setResiste();
						golpe =true;
						if(invasores[i][col].getResiste()==0) {
							j.setScore(invasores[i][col].getScore());
							invasores[i][col].setVisible(false);
							
						}
						j.getDisparo().setVisible(false);
						
						// audio= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/invaderexplode.wav"));
					    //audio.play();
					}	
				}if(j.getDisparo().getName().equals("rojo") && j.getDisparo().getX()+5 >= invasores[i][col].getX() && j.getDisparo().getX()+5 <= invasores[i][col].getX()+60 && (j.getDisparo().getY() <=  invasores[i][col].getY()+30 && j.getDisparo().getY() >=  invasores[i][col].getY())) {
					if(invasores[i][col].esVisible() && j.getDisparo().esVisible()) {
						invasores[i][col].setResiste();
						if(col+1 <= 11) {
							invasores[i][col+1].setResiste();
						}
						if(col-1 >= 0) {
							invasores[i][col-1].setResiste();
						}
						
						golpe =true;
						if(invasores[i][col].getResiste()==0) {
							invasores[i][col].setVisible(false);
							j.setScore(invasores[i][col].getScore());
						}if(col-1 >= 0 && invasores[i][col-1].getResiste()==0) {
							invasores[i][col-1].setVisible(false);
							j.setScore(invasores[i][col-1].getScore());
						}if(col+1 <= 11 && invasores[i][col+1].getResiste()==0) {
							invasores[i][col+1].setVisible(false);
							j.setScore(invasores[i][col+1].getScore());
						}
						j.getDisparo().setVisible(false);
						
						//AudioClip audio= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/invaderexplode.wav"));
					    //audio.play();
					}
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public void mirePlatillo(Jugador j) {
		if(j.getDisparo().getX()+1 >= platillo.getX() && j.getDisparo().getX()+5 <= platillo.getX()+60 && j.getDisparo().getY() <= platillo.getY()+30 && j.getDisparo().getY() >= platillo.getY()) {
			if(platillo.esVisible() && j.getDisparo().esVisible()) {
				platillo.setResiste();
				if(platillo.getResiste()==0) {
					platillo.setVisible(false);
					j.setScore(platillo.getScore());
					j.setLives(1);
					this.rojos=3;
				}
				j.getDisparo().setVisible(false);
			}
		}
	}
	
	/**
	 * 
	 */
	public void mireBarreras() {
			
			for (Jugador j: jugadores) {
				if(j.getDisparo().esVisible()) {
					j.getDisparo().getX();
					for(Objeto b: barreras) {
						
						if(j.getDisparo().getX()>= b.getX()+5 && j.getDisparo().getX()<= b.getX()+60 && j.getDisparo().getY()<=530 && b.esVisible()){
							j.getDisparo().setVisible(false);
							
							if(b.muero(j.getDisparo())) {
								b.setVisible(false);
								
							}
						}
					}
				}
			}
			for(Objeto[] o: invasores) {
				for(Objeto inv: o) {
					Objeto d= inv.getDisparo();
					for(Objeto b: barreras) {
						if(d.getX()>= b.getX()+5 && d.getX()<= b.getX()+60 && d.getY()>= b.getY() && b.esVisible() && d.esVisible()) {
							d.setVisible(false);
							if(b.muero(d)) {
								b.setVisible(false);
							}
						}
					}
				}
			}
	}
	
	/**
	 * realiza el movimiento del disparo a lo largo del tablero
	 */
	public void muevaDisparo() {
		for(Jugador j: jugadores) {
			if(j.getDisparo().esVisible()) {
				j.getDisparo().setY(-2);
				if(j.getDisparo().getY()<=-30) {
					j.getDisparo().setVisible(false);
				}
			}
		}
		for(Objeto[] l: invasores) {
			for(Objeto inv: l) {
				if(inv.getDisparo().esVisible()) {
					inv.getDisparo().setY(+2);
					if(inv.getDisparo().getY()>= 720) {
						inv.getDisparo().setVisible(false);
					}
				}
			}
		}
	}
	
	public void disparaInvasor() {
		
		if(disparos<1) {
			int fil = (int) (Math.random() * 5) ;
			int col = (int) (Math.random() * 12);
			invasores[fil][col].dispare("normal");
		}else if(disparos>=150) {
			disparos=-1;
		}
		disparos++;
		muevaDisparo();
		for(Objeto[] l: invasores) {
			for(Objeto inv: l) {
				if(inv.getDisparo().esVisible()) {
					reviseJugadores(inv.getDisparo());
				}
			}
		}
	}
	
	public boolean gana() {
		boolean answ= false;
		int i=0;
		for(Objeto[] o: invasores) {
			for(Objeto inv: o) {
				if(!inv.esVisible()) {
					i++;
				}
			}
		}
		if(i==60) {
			answ=true;
		}
		return answ;
	}
	
	public boolean sigueJugando() {
		boolean answ= true;
		int jug=0;
		for(Jugador j: jugadores) {
			if(j.getLives()==0) {
				jug++;
				j.setVisible(false);
				j.getDisparo().setVisible(false);
			}for(Objeto[] l: invasores) {
				for(Objeto inv: l) {
					if (inv.getY() >= 600) {
						
						answ = false;
					}
				}
			}
		}
		if(jug==jugadores.size()) {
			answ=false;
		}
		return answ;
	}
	
	public void reviseJugadores(Objeto d) {
		for(Jugador j: jugadores) {
			if(j.esVisible() && d.getX()+5 >= j.getX() && d.getX()+5<= j.getX()+60 && (d.getY()+25<= j.getY()+60 && d.getY()+25 >= j.getY())) {
				j.setLives(-1);
				d.setVisible(false);
			}
			
		}
	}
	
	/**
	 * realiza el movimiento del invasor hacia abajo
	 */
	public void muevaAbajo() {
		for(int i=4; i>=0; i--) {
			for(int j=11; j>=0; j--) {
				Objeto inv= invasores[i][j];
				inv.setY(7);
				String nombre= inv.getName();
				if(arriba) {
					inv.setStringImagen("src/imagenes/"+nombre+"Arriba.png");
					if(nombre == "cangrejo" && golpe && inv.getResiste() == 1) {
						inv.setStringImagen("src/imagenes/"+nombre+"ArribaAmarillo.png");
						temp=false;
						cont++;
					}if(nombre == "calamar" && golpe && inv.getResiste() == 1) {
						inv.setStringImagen("src/imagenes/"+nombre+"ArribaRojo.png");
						temp=false;
						cont++;
					}if(nombre == "calamar" && golpe && inv.getResiste() == 2) {
						inv.setStringImagen("src/imagenes/"+nombre+"ArribaAmarillo.png");
						temp=false;
						cont++;
					}
					temp=false;
					cont++;
				}else {
					inv.setStringImagen("src/imagenes/"+nombre+".png");
					if(nombre == "cangrejo" && golpe && inv.getResiste() == 1) {
						inv.setStringImagen("src/imagenes/"+nombre+"Amarillo.png");
						temp=true;
						cont++;
					}if(nombre == "calamar" && golpe && inv.getResiste() == 1) {
						inv.setStringImagen("src/imagenes/"+nombre+"Rojo.png");
						temp=true;
						cont++;
					}if(nombre == "calamar" && golpe && inv.getResiste() == 2) {
						inv.setStringImagen("src/imagenes/"+nombre+"ArribaAmarillo.png");
						temp=true;
						cont++;
					}
					temp= true;
					cont++;
				}
					
				if(i==0 && j==0) {
					dir= nextDir;
				}
			}
		}
	}
	
	/**
	 * realiza el movimiento del invasor a la derecha
	 * @param ancho
	 */
	public void muevaALaDerecha(int ancho) {
		for(int i=4; i>=0; i--) {
			for(int j=11; j>=0; j--) {
				Objeto inv= invasores[i][j];
				if(inv.getX()<=ancho-45) {
					inv.setX(1);
					String nombre= inv.getName();
					if(arriba) {
						inv.setStringImagen("src/imagenes/"+nombre+"Arriba.png");
						if(nombre == "cangrejo" && golpe && inv.getResiste() == 1) {
							inv.setStringImagen("src/imagenes/"+nombre+"ArribaAmarillo.png");
							temp=false;
							cont++;
						}if(nombre == "calamar" && golpe && inv.getResiste() == 1) {
							inv.setStringImagen("src/imagenes/"+nombre+"ArribaRojo.png");
							temp=false;
							cont++;
						}if(nombre == "calamar" && golpe && inv.getResiste() == 2) {
							inv.setStringImagen("src/imagenes/"+nombre+"ArribaAmarillo.png");
							temp=false;
							cont++;
						}
						temp=false;
						cont++;
					}else {
						inv.setStringImagen("src/imagenes/"+nombre+".png");
						if(nombre == "cangrejo" && golpe && inv.getResiste() == 1) {
							inv.setStringImagen("src/imagenes/"+nombre+"Amarillo.png");
							temp=true;
							cont++;
						}if(nombre == "calamar" && golpe && inv.getResiste() == 1) {
							inv.setStringImagen("src/imagenes/"+nombre+"Rojo.png");
							temp=true;
							cont++;
						}if(nombre == "calamar" && golpe && inv.getResiste() == 2) {
							inv.setStringImagen("src/imagenes/"+nombre+"Amarillo.png");
							temp=true;
							cont++;
						}
						temp= true;
						cont++;
					}
				}else {
					dir= -5;
					nextDir=-1;
				}
			}
		}
	}
	
	/**
	 * retorna la matriz de los invasores
	 * @return invasores Objeto[][]
	 */
	public Objeto[][] getInvasores(){
		return invasores;
	}
	
	/**
	 * retorna el arrayList de los jugadores
	 * @return jugadores ArrayList
	 */
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	
	public Platillo getPlatillo() {
		return platillo;
	}
	
	/**
	 * 
	 */
	public void setDisparos() {
		disparos = 0;
	}
	
	/**
	 * 
	 * @param archivo
	 * @throws SPOOBInvadersException
	 */
	@SuppressWarnings("resource")
	public static void abrir(File archivo) throws SPOOBInvadersException {
		
		FileInputStream entrada = null;
		
		try {
			entrada = new FileInputStream(archivo);
			ObjectInputStream reader = new ObjectInputStream(entrada);
			spi = (SPOOBInvaders)reader.readObject();
			reader.close();
			
		}catch(FileNotFoundException e) {
			throw new SPOOBInvadersException(SPOOBInvadersException.ARCHIVO_NO_ENCONTRADO);
		}catch(EOFException e){
			throw new SPOOBInvadersException(SPOOBInvadersException.ARCHIVO_VACIO);
		}catch(IOException e){
			throw new SPOOBInvadersException(SPOOBInvadersException.ERROR);
		}catch(ClassNotFoundException e){
			throw new SPOOBInvadersException(SPOOBInvadersException.CLASE_NO_ENCONTRADA);
		}
		
	}
	
	/**
	 * 
	 */
	 public static void guardar(File file)throws SPOOBInvadersException{
		 FileOutputStream salvar = null;
	        try{
	        	if(jugadores == null && invasores == null) {
	        		throw new SPOOBInvadersException(SPOOBInvadersException.ARCHIVO_VACIO);
	        	}
	        	salvar = new FileOutputStream(file+".dat");
	            ObjectOutputStream writer = new ObjectOutputStream(salvar);    
	            //error
	            writer.writeObject(spi);
	            writer.close();
	        }catch(FileNotFoundException e){
				throw new SPOOBInvadersException(SPOOBInvadersException.ARCHIVO_NO_ENCONTRADO);
			}catch(IOException e){
				e.printStackTrace();throw new SPOOBInvadersException(SPOOBInvadersException.ERROR);
			}
	    }

}
