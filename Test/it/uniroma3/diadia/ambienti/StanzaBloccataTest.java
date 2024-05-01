package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	StanzaBloccata S1;
	Stanza S2;
	Stanza S3;
	Attrezzo P;
	@Before
	public void setUp() throws Exception {
		S1 = new StanzaBloccata("S1","nord","P");
		S2= new Stanza("S2");
		S3 = new Stanza("S3");
		P= new Attrezzo("P",1);
		S1.impostaStanzaAdiacente("nord", S2);
		S1.impostaStanzaAdiacente("sud", S3);
	}

	@Test
	public void testgetStanzaAdiacenteBloccata() {
		assertEquals(S1,S1.getStanzaAdiacente("nord"));
	}
	@Test
	public void testgetStanzaAdiacenteAperta() {
		S1.addAttrezzo(P);
		assertEquals(S2,S1.getStanzaAdiacente("nord"));
	}
	@Test
	public void testgetStanzaAdiacenteNonBloccata() {
		
		assertEquals(S3,S1.getStanzaAdiacente("sud"));
	}
}
