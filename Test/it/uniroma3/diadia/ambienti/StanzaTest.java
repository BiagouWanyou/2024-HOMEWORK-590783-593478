package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
 private Stanza Stanza1;
 private Stanza Stanza2;
 private Stanza Stanza3;
 private Stanza StanzaArrayPieno;
 private Attrezzo Penna;
 private Attrezzo duplicato;
 private Attrezzo Piuma;
 private Attrezzo[] Attrezzi;
 


 @Before
	public void setUp() throws Exception {
		Stanza1  = new Stanza("S1");

		
		Penna = new Attrezzo("penna",0);
		
		StanzaArrayPieno = new Stanza("StanzaPiena");
		duplicato = new Attrezzo("duplicato",0);
		for(int i=0;i < 10;i++) {
			StanzaArrayPieno.addAttrezzo(duplicato);
		}
		
		Stanza2= new Stanza("S2");
		Stanza2.addAttrezzo(Penna);
		
		
		Piuma = new Attrezzo("piuma",0);
		
		Stanza3=new Stanza("S3");
		Stanza3.addAttrezzo(Penna);
		Stanza3.addAttrezzo(Piuma);
		
		Attrezzi= new Attrezzo[10];
		Attrezzi[0]=Piuma;
}
 
 
/*Verifica setStanzaAdiacente assegni la stanza A1 come adiacente di S1*/
	@Test
	public void testAdiacenteUnaAssegnazione() {
		assertNotEquals(Stanza2,Stanza1.getStanzaAdiacente("est"));
		Stanza1.impostaStanzaAdiacente("est", Stanza2);
		assertEquals(Stanza2,Stanza1.getStanzaAdiacente("est"));
	}
	/*Verifica setStanzaAdiacente assegni la stanza A2.2 come adiacente di S2 sovrascrivendo A2.1*/
	@Test
	public void testAdiacenteDueAssegnazioni() {
		Stanza1.impostaStanzaAdiacente("est", Stanza2);
		assertEquals(Stanza2,Stanza1.getStanzaAdiacente("est"));
		Stanza1.impostaStanzaAdiacente("est", Stanza3);
		assertEquals(Stanza3,Stanza1.getStanzaAdiacente("est"));
	}
	/*Verifica che aggiungendo un riferimento a null come stanza adiacente*/
	@Test
	public void testAdiacenteConSAdiacenteNull() {
		assertEquals(null,Stanza1.getStanzaAdiacente("nord"));
	}
	/*Verifica l' aggiunta di un attrezzo ad una stanza vuota*/
	@Test
	public void testAddPennaAdArrayVuoto() {
		Stanza1.addAttrezzo(Penna);
		assertEquals(Penna,Stanza1.getAttrezzo("penna"));
	}
	/*Verifica che addAttrezzo non sovrascrivi altri atrezzi in caso di array pieno*/
	@Test 
	public void testAddPennaAdArrayPieno() {
		StanzaArrayPieno.addAttrezzo(Penna);
		assertFalse(StanzaArrayPieno.hasAttrezzo("penna"));
	}
	/*Verifica AddPenna inserisca un attrezzo corretamente se l' array è ha un solo attrezzo*/
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
		assertArrayEquals( Attrezzi, Stanza3.getAttrezzi() );
		
		
	}
}
