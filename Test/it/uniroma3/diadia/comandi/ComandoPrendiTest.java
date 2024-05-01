package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

	Partita partita;
	IOConsole IO;
	ComandoPrendi prendi;
	@Before
	public void setUp() throws Exception {
		IO= new IOConsole();
		partita = new Partita(IO);
		prendi= new ComandoPrendi();
	}

	@Test
	public void test() {
		prendi.setParametro("osso");
		prendi.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}

}
