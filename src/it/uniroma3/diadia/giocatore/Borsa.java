package it.uniroma3.diadia.giocatore;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Properties;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA= 20;
	private List<Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	public Borsa()  {	
		Properties p= new Properties();
		try {
			p.load(new FileReader("resources/diadia.properties"));
			String pesoString = p.getProperty("peso_max_borsa");
			this.pesoMax= Integer.parseInt(pesoString);
			System.out.println("peso massimo di ogni borsa:"+pesoMax);
		}
		catch(Exception e) {
			System.out.println("errore");
			this.pesoMax = DEFAULT_PESO_MAX_BORSA;
			}

		this.attrezzi = new LinkedList<Attrezzo>(); 
		this.numeroAttrezzi = 0;


	}
	public Borsa(int pesoMax)  {

		this.pesoMax = pesoMax;
		this.attrezzi = new LinkedList<Attrezzo>(); 
		this.numeroAttrezzi = 0;
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return attrezzi.add(attrezzo);
	}

	public int getPesoMax() {
		return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for( Attrezzo attrezzo : attrezzi) {
			if(nomeAttrezzo.equals( attrezzo.getNome() ) )
				a=attrezzo;
		}

		return a;
	}
	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : attrezzi)
			peso += a.getPeso();

		return peso;
	}
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a= this.getAttrezzo(nomeAttrezzo);
		attrezzi.remove(this.getAttrezzo(nomeAttrezzo));

		return a;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo a : attrezzi)
				s.append(a.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	public List<Attrezzo>getContenutoOrdinatoPerPeso(){
		LinkedList<Attrezzo> ordinata= new LinkedList<Attrezzo>(this.attrezzi);
		final ComparatoreAttrezziPerPeso cmp = new ComparatoreAttrezziPerPeso();
		Collections.sort(ordinata,cmp);


		return ordinata;

	}

	/*restituisce l'insieme degli attrezzi nella borsa ordinati per nome*/

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		final SortedSet<Attrezzo> ordinata= new TreeSet<>(this.attrezzi);
		return ordinata;
	}

	/*restituisce una mappa che associa un intero (rappresentante un
    peso) con l’insieme (comunque non vuoto) degli attrezzi di tale
    peso: tutti gli attrezzi dell'insieme che figura come valore hanno
    lo stesso peso pari all'intero che figura come chiave*/

	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		final Map<Integer,Set<Attrezzo>> peso2Attrezzi = new HashMap<>();

		for(Attrezzo a:this.attrezzi) {
			if(peso2Attrezzi.containsKey(a.getPeso())) {
				//Attrezzo con peso gia visto in precedenza;
				//peso il vecchio set e aggiungo il vecchio set senza questo
				//attrezzzo aggiungendo il nuov peso;
				//
				final Set<Attrezzo> AttrezzoStessoPeso= peso2Attrezzi.get(a.getPeso());
				AttrezzoStessoPeso.add(a);

			}
			else {
				//Questo attrezzo ha un peso mai visto prima;
				//creo un nuov set per ospitare un nuovo set correnti e futuri dello stesso peso
				final Set<Attrezzo> set = new TreeSet<>();
				set.add(a);
				peso2Attrezzi.put(a.getPeso(), set);
			}

		}

		return peso2Attrezzi;
	}
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatoreAttrezziPerPeso cmp = new ComparatoreAttrezziPerPeso();
		final SortedSet<Attrezzo> set= new TreeSet<>(cmp);
		set.addAll(this.attrezzi);
		return set;
	}
	public List<Attrezzo> getAttrezzi(){
		return this.attrezzi;
	}
}
