package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	private Stanza Stanza1;
	private Stanza Stanza2;
	private Stanza Stanza3;
	private Attrezzo Penna;
	private Attrezzo Piuma;
	private List<Attrezzo> Attrezzi;



	@Before
	public void setUp() throws Exception {
		Stanza1  = new Stanza("S1");
		Penna = new Attrezzo("penna",0);
		Stanza2= new Stanza("S2");
		Stanza2.addAttrezzo(Penna);
		Piuma = new Attrezzo("piuma",0);
		Stanza3=new Stanza("S3");
		Stanza3.addAttrezzo(Penna);
		Stanza3.addAttrezzo(Piuma);
		Attrezzi= new LinkedList<Attrezzo>();
		Attrezzi.add(Piuma);
	}


	/*Verifica setStanzaAdiacente assegni la stanza2 come adiacente della stanza1*/
	@Test
	public void testimpostaStanzaAdiacenteUnaAssegnazione() {
		assertNotEquals(Stanza2,Stanza1.getStanzaAdiacente("est"));
		Stanza1.impostaStanzaAdiacente("est", Stanza2);
		assertEquals(Stanza2,Stanza1.getStanzaAdiacente("est"));
	}
	/*Verifica setStanzaAdiacente assegni la stanza3 come adiacente alla stanza1 sovrascrivendo stanza2*/
	@Test
	public void testimpostaStanzaAdiacenteDueAssegnazioni() {
		Stanza1.impostaStanzaAdiacente("est", Stanza2);
		assertEquals(Stanza2,Stanza1.getStanzaAdiacente("est"));
		Stanza1.impostaStanzaAdiacente("est", Stanza3);
		assertEquals(Stanza3,Stanza1.getStanzaAdiacente("est"));
	}
	/*Verifica che impostaStanzaAdiacente su due direzioni diverse*/
	@Test
	public void testimpostaStanzaAdiacenteDueAssegnazioniDiverseDirezioni() {
		Stanza1.impostaStanzaAdiacente("est", Stanza2);
		Stanza1.impostaStanzaAdiacente("nord", Stanza3);
		assertEquals(Stanza3,Stanza1.getStanzaAdiacente("nord"));
		assertEquals(Stanza2,Stanza1.getStanzaAdiacente("est"));
	}

	/*Verifica l' aggiunta di un attrezzo ad una stanza vuota*/
	@Test
	public void testAddPennaAdArrayVuoto() {
		Stanza1.addAttrezzo(Penna);
		assertEquals(Penna,Stanza1.getAttrezzo("penna"));
	}
	/*Verifica che addAttrezzo non sovrascrivi altri attrezzi in caso di array pieno*/

	/*Verifica AddAttrezzo inserisca un attrezzo corretamente se l' array attrezzi della stanza ha un solo attrezzo*/
	@Test
	public void testAddPennaAdArrayConUnAttrezzo(){
		Stanza1.addAttrezzo(Penna);
		assertEquals(Penna,Stanza1.getAttrezzo("penna"));
		Stanza1.addAttrezzo(Piuma);
		assertEquals(Piuma,Stanza1.getAttrezzo("piuma"));
	}
	/*Verifica la rimozione di un attrezzo dalla stanza se questa ha un solo attrezzo*/
	@Test
	public void testRemoveAttrezzoDaArrayCon1Elemento() {
		assertTrue(Stanza2.hasAttrezzo("penna"));
		assertTrue(Stanza2.removeAttrezzo(Penna));
		assertFalse(Stanza2.hasAttrezzo("penna"));
	}
	/*Verifica che removeAttrezzo ritorni false se l' attrezzo non è presente nella stanza*/
	@Test
	public void testRemoveAttrezzoDaArryaVuoto() {
		assertFalse(Stanza1.removeAttrezzo(Penna));
	}
	/*Verifica che RemoveAttrezzo rimuova un attrezzo nella prima posizione della'array spostando l' attrezzo rimanente in prima  posizione*/
	@Test 
	public void testRemovePrimoDi2Attrezzi() {
		Stanza3.removeAttrezzo(Penna);
		assertEquals( Attrezzi, Stanza3.getAttrezzi() );


	}
}
