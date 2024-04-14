package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class giocatoreTest {
 giocatore Biagou;

	@Before
	public void setUp() throws Exception {
		Biagou= new giocatore();
	}

// Verifica testCfu ritorni il corretto numero di cfu
	@Test
	public void testGetCfu() {
		assertEquals(20,Biagou.getCfu());
	}
	//Verifica che setCfu abbia modificato il numero di cfu
	@Test
	public void testSetCfu() {
		Biagou.setCfu(0);
		assertEquals(0,Biagou.getCfu());
	}
}
