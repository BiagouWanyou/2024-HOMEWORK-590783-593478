package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.*;
public class PartitaTest {	
	private Partita Partita;
	private Stanza Stanza1;
	private Stanza Stanza2;
	@Before
	public void setUp() throws Exception {
		IOConsole IO= new IOConsole();
		Partita= new Partita(IO);
		Stanza1= new Stanza("S1");
		Stanza2= new Stanza("S2");
		Partita.setStanzaCorrente(Stanza1);
	}

	
	@Test
	/*verifica che getStanzaCorrente sia uguale alla stanza associata con setstanzacorrente*/
	public void testgetStanzaCorrenteNotNull() {
		Partita.setStanzaCorrente(Stanza1);
		assertEquals(Stanza1,this.Partita.getStanzaCorrente());
	}
	@Test
	/*verifica che getStanzaCorrente ritorni null se la stanza corrente punta a null*/
	public void testgetStanzaCorrenteNull() {
		Partita.setStanzaCorrente(null);
		assertNull(this.Partita.getStanzaCorrente());
	}
	@Test
	/* verifica che dopo due assegnazioni diverse getStanzaCorrenet ritorni l' ultima stanza assegnata*/
	public void testgetStanzaCorrente2diseguito() {
		Partita.setStanzaCorrente(Stanza1);
		Partita.setStanzaCorrente(Stanza2);
		assertEquals(Stanza2,this.Partita.getStanzaCorrente());
	}
	/*verifica Vinta ritorni true quando ti trovi nella stanza vincente */
	@Test
	public void testVintaVincente() {
		Partita.setStanzaCorrente(Partita.getLabirinto().getStanzaVincente());
		assertTrue(this.Partita.vinta());
	}
	/*verifica Vinta ritorni false quando non ti trovi nella stanza vincente */
	@Test
	public void testVintaPerdente() {
		assertFalse(this.Partita.vinta());
	}
	/*Verifica la partita sia finita se Finita è uguale a true*/
	@Test
	public void testFinita_FINITA_IS_TRUE() {
		Partita.setFinita();
		assertTrue(this.Partita.isFinita());
	}
	/*Verifica la partita sia finita se i CFU sono uguali a 0*/
	@Test
	public void testFinita_CFU() {
		Partita.getGiocatore().setCfu(0);
		assertTrue(this.Partita.isFinita());
	}
	/*Verifica che isFinita ritorni false se tutte le condizioni sono false*/
	@Test
	public void testFinitaNOT() {
		assertFalse(this.Partita.isFinita());
	}
	
}
