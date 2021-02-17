package aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SPOOBInvadersTest {
	public SPOOBInvadersTest() {
		
	}
	
	@Test
	public void pruebaJugadorEnModoUnJugador() {
		SPOOBInvaders spi= new SPOOBInvaders(1,700);
		Jugador j= spi.getJugadores().get(0);
		assertTrue(j.esVisible());
		
		assertTrue(j.getLives()==3);
		assertTrue(j.getScore()==0);
		
		spi.muevaJugador(60, 0, 700);
		
		assertEquals(60, j.getX());
		assertTrue(j.getLives()==3);
		assertTrue(j.getScore()==0);
		
		spi.muevaJugador(-60, 0, 700);
		
		assertEquals(0, j.getX());
		assertTrue(j.getLives()==3);
		assertTrue(j.getScore()==0);
		
		spi.muevaJugador(100, 0, 700);
		
		
		spi.jugadorDispara(0);
		
		assertTrue(j.getDisparo().esVisible());
		
		spi.hagaMatriz();
		while (j.getDisparo().esVisible()) {
			spi.mireInvasores(j);
			spi.muevaDisparo();
		}
		int impactados=0;
		for(Objeto[] o: spi.getInvasores()) {
			for(Objeto inv: o) {
				if(!inv.esVisible()) {
					impactados++;
				}
			}
		}
		assertTrue(impactados>0);
	}
	
	@Test
	public void pruebaJugadoresEnModoDosJugadores() {
		SPOOBInvaders spi= new SPOOBInvaders(2,700);
		Jugador jUno= spi.getJugadores().get(0);
		Jugador jDos= spi.getJugadores().get(1);
		
		assertTrue(jUno.esVisible());
		
		assertTrue(jUno.getLives()==3);
		assertTrue(jUno.getScore()==0);
		
		spi.muevaJugador(60, 0, 700);
		
		assertEquals(60, jUno.getX());
		assertTrue(jUno.getLives()==3);
		assertTrue(jUno.getScore()==0);
		
		spi.muevaJugador(-60, 0, 700);
		
		assertEquals(0, jUno.getX());
		assertTrue(jUno.getLives()==3);
		assertTrue(jUno.getScore()==0);
		
		spi.jugadorDispara(0);
		
		assertTrue(jUno.getDisparo().esVisible());
		
		
		
		assertTrue(jDos.esVisible());
		
		assertTrue(jDos.getLives()==3);
		assertTrue(jDos.getScore()==0);

		spi.muevaJugador(-30, 1, 700);
		
		assertEquals(600, jDos.getX());
		assertTrue(jDos.getLives()==3);
		assertTrue(jDos.getScore()==0);
		
		spi.muevaJugador(30, 1, 700);
		
		assertEquals(630, jDos.getX());
		assertTrue(jDos.getLives()==3);
		assertTrue(jDos.getScore()==0);
		
		spi.jugadorDispara(1);
		
		assertTrue(jDos.getDisparo().esVisible());
		
		spi.hagaMatriz();
		while (jDos.getDisparo().esVisible()) {
			spi.mireInvasores(jDos);
			spi.muevaDisparo();
		}
		int impactados=0;
		for(Objeto[] o: spi.getInvasores()) {
			for(Objeto inv: o) {
				if(!inv.esVisible()) {
					impactados++;
				}
			}
		}
		assertTrue(impactados>0);
		
	}
	
	@Test
	public void pruebaLosInvasores() {
		SPOOBInvaders spi= new SPOOBInvaders(1,700);
		Jugador j= spi.getJugadores().get(0);
		spi.hagaMatriz();
		int vivos=0;
		for(Objeto[] o: spi.getInvasores()) {
			for(Objeto inv: o) {
				if(inv.esVisible()) {
					vivos++;
				}
			}
		}
		assertEquals(60, vivos);
		
		int devolver=0;
		int unDisparoInvasor=0;
		spi.disparaInvasor();
		for(Objeto[] o: spi.getInvasores()) {
			for(Objeto inv: o) {
				if(inv.getDisparo().esVisible()) {
					unDisparoInvasor++;
					j.setX(inv.getX());
					devolver= -1 *inv.getX();
					while(inv.getDisparo().esVisible()) {
						spi.reviseJugadores(inv.getDisparo());
						spi.muevaDisparo();
					}
				}
			}
		}
		assertEquals(1, unDisparoInvasor);
		assertEquals(2, j.getLives());
		spi.muevaJugador(devolver, 0, 700);
		spi.setDisparos();
		
		unDisparoInvasor=0;
		spi.disparaInvasor();
		for(Objeto[] o: spi.getInvasores()) {
			for(Objeto inv: o) {
				if(inv.getDisparo().esVisible()) {
					unDisparoInvasor++;
					j.setX(inv.getX());
					devolver= -1 *inv.getX();
					while(inv.getDisparo().esVisible()) {
						spi.reviseJugadores(inv.getDisparo());
						spi.muevaDisparo();
					}
				}
			}
		}

		assertEquals(1, unDisparoInvasor);
		assertEquals(1, j.getLives());
		spi.muevaJugador(devolver, 0, 700);
		spi.setDisparos();
		
		unDisparoInvasor=0;
		spi.disparaInvasor();
		for(Objeto[] o: spi.getInvasores()) {
			for(Objeto inv: o) {
				if(inv.getDisparo().esVisible()) {
					unDisparoInvasor++;
					j.setX(inv.getX());
					devolver= -1 *inv.getX();
					while(inv.getDisparo().esVisible()) {
						spi.reviseJugadores(inv.getDisparo());
						spi.muevaDisparo();
					}
				}
			}
		}
		assertEquals(1, unDisparoInvasor);
		assertEquals(0, j.getLives());
		assertFalse(j.esVisible());
	}
	
	@Test
	public void pruebaBarreras() {
		SPOOBInvaders spi = new SPOOBInvaders(1, 700);
		DisparoNormal dInvasor= new DisparoNormal(new int[] {0,0}, false);
		DisparoNormal dJugador= new DisparoNormal(new int[] {0,0}, true);
		BarreraVerde bv= new BarreraVerde(new int[] {0,200});
		BarreraRoja br= new BarreraRoja(new int[] {0,200});
		
		//Se prueba la verde (Solo se destruye despues de 3 tiros de invasor)
		assertFalse(bv.muero(dJugador));
		assertFalse(bv.muero(dInvasor));
		assertFalse(bv.muero(dJugador));
		assertFalse(bv.muero(dInvasor));
		assertFalse(bv.muero(dJugador));
		assertTrue(bv.muero(dInvasor));
		
		//Se prueba la roja (Se destruye al primer impacto de la bala de un jugador )
		assertFalse(br.muero(dInvasor));
		assertTrue(br.muero(dJugador));
		
	
		
	}
	

}
