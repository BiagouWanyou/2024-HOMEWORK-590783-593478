package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class FabbricaDiComandiFisarmonicaTest {
	FabbricaDiComandiFisarmonica fab;
	
	
	@Before
	public void setUp() throws Exception {
		fab = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testCostruisciComandoVai() {
		assertEquals(fab.costruisciComando("vai nord").getNome(),"vai");
		assertEquals(fab.costruisciComando("vai nord").getParametro(),"nord");
	}
	@Test
	public void testCostruisciComandoPrendi() {
		assertEquals("prendi",fab.costruisciComando("prendi osso").getNome());
		assertEquals("osso",fab.costruisciComando("prendi osso").getParametro());
	}
	@Test
	public void testCostruisciComandoPosa() {
		assertEquals("posa",fab.costruisciComando("posa osso").getNome());
		assertEquals("osso",fab.costruisciComando("posa osso").getParametro());
	}
	@Test
	public void testCostruisciComandoGuarda() {
		assertEquals("guarda",fab.costruisciComando("guarda").getNome());
	}
	@Test
	public void testCostruisciComandoAiuto() {
		assertEquals("aiuto",fab.costruisciComando("aiuto").getNome());
	}
	@Test
	public void testCostruisciComandoNonValido() {
		assertEquals("non valido",fab.costruisciComando("gira su te stesso").getNome());
	}
	@Test
	
	public void testCostruisciComandoFine() {
		assertEquals("fine",fab.costruisciComando("fine").getNome());
	}
	

}
