package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	StanzaBuia S1;
	Attrezzo L;
	@Before
	public void setUp() throws Exception {
		S1= new StanzaBuia("S1","L");
		L = new Attrezzo("L",1);
	}

	@Test
	public void testgetDescrizioneBuia() {
		assertEquals(S1.getDescrizione(),"Qui c'è un buio pesto");
	}

	@Test
	public void testgetDescrizioneIlluminata() {
		S1.addAttrezzo(L);
		assertNotEquals(S1.getDescrizione(),"Qui c'è un buio pesto");
	}

}
