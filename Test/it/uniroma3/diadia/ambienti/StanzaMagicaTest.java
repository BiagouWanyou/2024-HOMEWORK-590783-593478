package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	StanzaMagica magica;
	Attrezzo attrezzo;
	@Before
	public void setUp() throws Exception {
		magica = new StanzaMagica("S1",1);
		attrezzo = new Attrezzo("piuma",1);
	}

	@Test
	public void testSogliaMagicaSuperata() {
		magica.addAttrezzo(attrezzo);
		magica.removeAttrezzo(attrezzo);
		magica.addAttrezzo(attrezzo);
		assertNotNull(magica.getAttrezzo("amuip"));
		assertEquals(2,magica.getAttrezzo("amuip").getPeso());
	
	}
	@Test
	public void testSogliaMagicaNonSuperata() {
		magica.addAttrezzo(attrezzo);
		assertEquals(attrezzo,magica.getAttrezzo("piuma"));
		assertEquals(1,magica.getAttrezzo("piuma").getPeso());
	}

}
