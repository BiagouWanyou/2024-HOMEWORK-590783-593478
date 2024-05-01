package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;

public class ComandoVaiTest {
	Partita partita;
	IOConsole IO;
	ComandoVai vai;
	
	@Before
	public void setUp() throws Exception {
		IO= new IOConsole();
		partita = new Partita(IO);
		vai = new ComandoVai();
		
	}

	@Test
	public void testEseguiCheckStanzaCorrente() {
		vai.setParametro("sud");
		vai.esegui(partita);
		assertEquals(partita.getStanzaCorrente().getNome(),"Aula N10");
	}
	@Test
	public void testEseguiCheckCfu() {
		vai.setParametro("nord");
		vai.esegui(partita);
		assertEquals(partita.getGiocatore().getCfu(),19);
	}
	@Test
	public void testEseguiDirezioneNull() {
		vai.setParametro(null);
		vai.esegui(partita);
		assertEquals(partita.getStanzaCorrente().getNome(),"Atrio");
	}
}
