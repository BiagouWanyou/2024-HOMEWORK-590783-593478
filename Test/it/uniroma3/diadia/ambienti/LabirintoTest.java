package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LabirintoTest {
	Labirinto Roma3;

	@Before
	public void setUp() throws Exception {
		Roma3 = Labirinto.newBuilder();
		Roma3.creaStanze();
	}

	
//Verifica  getStanzaIniziale ritorni ritorni Atrio
	@Test
	public void testGetStanzaIniziale() {
		assertEquals("Atrio",Roma3.getStanzaIniziale().getNome());
		
	}
//Verifica che getStanzaVincente ritorni Biblioteca
	@Test
	public void testGetStanzaVIncente() {
		assertEquals("Biblioteca",Roma3.getStanzaVincente().getNome());
		
	}

}
