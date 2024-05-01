package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	Partita partita;
	IOConsole IO;
	ComandoPosa posa;
	Attrezzo banana;
	@Before
	public void setUp() throws Exception {
		IO= new IOConsole();
		partita = new Partita(IO);
		posa = new ComandoPosa();
		banana= new Attrezzo("banana",1);
		partita.getGiocatore().getBorsa().addAttrezzo(banana);
	}

	@Test
	public void TestEseguiPosaAttrezzo() {
		posa.setParametro("banana");
		posa.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("banana"));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("banana"));
	}
	@Test
	public void TestsetParametro() {
		posa.setParametro(null);
		assertNull(posa.getParametro());
	}

}
