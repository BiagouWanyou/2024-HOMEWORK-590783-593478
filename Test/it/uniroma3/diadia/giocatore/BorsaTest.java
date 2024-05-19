package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

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
	Borsa BorsaVuota;
	Borsa BorsaOrdine;
	Borsa BorsaPesiUguali;
	Borsa BorsaMappaPeso;
	Attrezzo a1;
	Attrezzo doppioPeso;
	Attrezzo nomeDiverso;
	Attrezzo lanterna;
	Attrezzo martello;
	Attrezzo osso;
	@Before
	public void setUp() throws Exception {
		a1= new Attrezzo("a",1);
		doppioPeso= new Attrezzo("a",2);
		nomeDiverso = new Attrezzo("d",1);
		Borsa= new Borsa();
		BorsaVuota = new Borsa(100);
		BorsaPesiUguali = new Borsa(100);
		BorsaOrdine = new Borsa(100);
		BorsaMappaPeso = new Borsa(100);
		Attrezzo1 = new Attrezzo("A1",1);
		Attrezzo2 = new Attrezzo("A2",10);
		Piena= new Borsa(100);
		for(int i=0;i<9;i++) {
			Piena.addAttrezzo(Attrezzo1);
		}
		Piena.addAttrezzo(Attrezzo2);
		BorsaOrdine.addAttrezzo(nomeDiverso);
		BorsaOrdine.addAttrezzo(doppioPeso);
		BorsaOrdine.addAttrezzo(a1);
		lanterna= new Attrezzo("lanterna",5);
		osso = new Attrezzo("osso",5);
		martello = new Attrezzo("martello",5);
		BorsaPesiUguali.addAttrezzo(martello);
		BorsaPesiUguali.addAttrezzo(lanterna);
		BorsaPesiUguali.addAttrezzo(osso);
;	}

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
	//Verifica che getAttrezzo ritorni null se non è presente, nella borsa, l' attrezzo specificato 
	@Test
	public void testgetAttrezzoAttrezzoMancante() {
		assertNull(Borsa.getAttrezzo("A1"));
	}
	@Test
	public void testhasAttrtezzoMancante() {
		assertFalse(Borsa.hasAttrezzo("A1"));
	}
	@Test 
	public void testgetContenutoOrdinatoPerPesoDuePesiUguali() {
		Iterator<Attrezzo> i= BorsaOrdine.getContenutoOrdinatoPerPeso().iterator();
		assertEquals(a1,i.next());
		assertEquals(nomeDiverso,i.next());
		assertEquals(doppioPeso,i.next());
		
		
	}
	@Test 
	public void testgetContenutoOrdinatoPerPesoVuota() {
		assertTrue(BorsaVuota.getContenutoOrdinatoPerPeso().isEmpty());
		
	}
	@Test
	public void testgetContenutoOrdinatoPerNomeDueNomiUguali() {
		Iterator<Attrezzo> i=BorsaOrdine.getContenutoOrdinatoPerNome().iterator();
		assertEquals(a1,i.next());
		assertEquals(doppioPeso,i.next());
		assertEquals(nomeDiverso,i.next());
	}
	@Test 
	public void testgetContenutoOrdinatoPerNomeVuota() {
		assertTrue(BorsaVuota.getContenutoOrdinatoPerNome().isEmpty());
		
	}
	@Test
	public void getContenutoRaggruppatoPerPesoDuePesiUguali() {
		Iterator<Attrezzo> i = BorsaOrdine.getContenutoRaggruppatoPerPeso().get(1).iterator();
		assertEquals(a1,i.next());
		assertEquals(nomeDiverso,i.next());
		i= BorsaOrdine.getContenutoRaggruppatoPerPeso().get(2).iterator();
		assertEquals(doppioPeso,i.next());
	}
	@Test 
	public void getContenutoRaggruppatoPesoVuota() {
		assertTrue(BorsaVuota.getContenutoRaggruppatoPerPeso().isEmpty());
	}
	@Test
	public void getSortedSetOrdinatoPerPesoDuePesiUguali() {
		Iterator<Attrezzo> i=BorsaOrdine.getSortedSetOrdinatoPerPeso().iterator();
		assertEquals(a1,i.next());
		assertEquals(nomeDiverso,i.next());
		assertEquals(doppioPeso,i.next());
	}
	@Test
	public void getSortedSetOrdinatoPerPesoVuota() {
		assertTrue(BorsaVuota.getSortedSetOrdinatoPerPeso().isEmpty());
	}
	@Test
	public void getContenutoRaggruppatoPerPesiUgualiTuttiStessoPeso() {
		Iterator<Attrezzo> i = BorsaPesiUguali.getContenutoRaggruppatoPerPeso().get(5).iterator();
		assertEquals(lanterna,i.next());
		assertEquals(martello,i.next());
		assertEquals(osso,i.next());
	}
}
