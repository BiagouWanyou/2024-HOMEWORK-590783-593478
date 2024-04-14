package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	Borsa Borsa;
	Attrezzo Attrezzo1;
	Attrezzo Attrezzo2;
	Borsa Piena;

	@Before
	public void setUp() throws Exception {
		Borsa= new Borsa();
		Attrezzo1 = new Attrezzo("A1",1);
		Attrezzo2 = new Attrezzo("A2",10);
		Piena= new Borsa(100);
		for(int i=0;i<9;i++) {
			Piena.addAttrezzo(Attrezzo1);
		}
		Piena.addAttrezzo(Attrezzo2);
		
	
	}

//Verifica che addAttrezzo abbia aggiunto un attrezzo all' array vuoto di una borsa
	@Test
	public void testAddAttrezzoadArrayVuoto() {
		Borsa.addAttrezzo(Attrezzo1);
		assertEquals(Attrezzo1,Borsa.getAttrezzo("A1"));
	}
//Verifica che addAttrezzo non aggiunga un attrezzo nuovo ad una borsa che ha raggiunto il peso massimo 
	@Test
	public void testAddAttrezzoSePesoMassimoRaggiunto() {
		Borsa.addAttrezzo(Attrezzo2);
		assertFalse(Borsa.addAttrezzo(Attrezzo1));
	}
/*Verifica che addAttrezzo non aggiunga un attrezzo nuovo ad una borsa 
* che ha raggiunto il numero massimo di attrezzi nella borsa */
	@Test
	public void testAddAttrezzoNumeroMassimoRaggiunto() {
		 assertNotEquals(Piena.getPeso(),Piena.getPesoMax());
		 assertFalse(Piena.addAttrezzo(Attrezzo2));
	
	}
/*Verifica che RemoveAttrezzo abbia rimosso un attrezzo dalla prima posizione dell' array della borsa*/
	@Test
	public void testRemoveAttrezzoPrimaPosizione() {
		 Borsa.addAttrezzo(Attrezzo1);
		 assertEquals(Attrezzo1,Borsa.getAttrezzo("A1"));
		 Borsa.removeAttrezzo("A1");
		 assertNull(Borsa.getAttrezzo("A1"));
	}
/*Verifica che RemoveAttrezzo abbia rimosso correttamente l' attrezzo nell' ultima posizione delll' array della borsa*/
	@Test
	public void testRemoveAttrezzoUltimaPosizione() {
		assertTrue(Piena.hasAttrezzo("A2"));
		assertEquals(Attrezzo2,Piena.removeAttrezzo("A2"));
		assertFalse(Piena.getAttrezzo("A2")==Attrezzo2);
		assertNull(Piena.getAttrezzo("A2"));
	}
/*Verifica che RemoveAttrezzo ritorni null se l'attrezzo specificato non è nella borsa*/
	@Test
	public void testRemoveAttrezzoAttrezzoMancante(){
		assertNull(Borsa.getAttrezzo("A2"));
	}
/*Verifica che getAttrezzo ritorni il corretto attrezzo quando questo è presente*/
	@Test
	public void testgetAttrezzoAttrezzoPresente() {
		assertEquals(Attrezzo1,Piena.getAttrezzo("A1"));
	}
	//Verifica che RemoveAttrezzo ritorni null se non è presente, nella borsa, l' attrezzo specificato 
	@Test
	public void testgetAttrezzoAttrezzoMancante() {
		assertNull(Borsa.getAttrezzo("A1"));
	}

}
