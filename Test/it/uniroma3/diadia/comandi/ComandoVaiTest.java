package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class ComandoVaiTest {
	Partita partita;
	IOConsole IO;
	ComandoVai vai;
	LabirintoBuilder labirinto;
	
	@Before
	public void setUp() throws Exception {
		IO= new IOConsole();
		labirinto= new LabirintoBuilder();
	
		vai = new ComandoVai();
		labirinto.addStanzaIniziale("Iniziale")
		.addStanzaVincente("Vincente")
		.addAdiacenza("Iniziale", "Vincente", "nord");
		partita = new Partita(labirinto,IO);
		
		
	}

	@Test
	public void testEseguiCheckStanzaCorrente() {
		vai.setParametro("nord");
		vai.esegui(partita);
		assertEquals(partita.getStanzaCorrente().getNome(),"Vincente");
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
		assertEquals(partita.getStanzaCorrente().getNome(),"Iniziale");
	}
}
