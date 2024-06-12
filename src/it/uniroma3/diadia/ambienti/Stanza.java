package it.uniroma3.diadia.ambienti;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza implements Comparable<Stanza>{

	private String nome;
	private List<Attrezzo> attrezzi;
	private Map<Direction, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio pg;
	public enum Direction {
		NORD() {
			@Override public Direction opposta() {
				return SUD;
			}
		},

		OVEST() {
			@Override public Direction opposta() {
				return EST;
			}
		},
		SUD(){
			@Override	public Direction opposta() {
				return NORD;
			}
		},
		EST(){
			@Override	public Direction opposta() {
				return OVEST;
			}
		};

		public abstract Direction opposta();}

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<Direction,Stanza>();
		this.attrezzi = new LinkedList<Attrezzo>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		try{ 
			Direction dir = Direction.valueOf(direzione.toUpperCase());
			stanzeAdiacenti.put(dir, stanza);
		}
		catch(IllegalArgumentException e){
			System.out.println("Direzione inestistente");

		}

	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		try {Stanza s =stanzeAdiacenti.get(Direction.valueOf(direzione.toUpperCase()));
			return s;}
		catch(IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(!this.hasAttrezzo(attrezzo.getNome()))
			return attrezzi.add(attrezzo);
		else
			return false;

	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		List<Direction> direzioni =this.getDirezioni();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direction direzione : direzioni)
			if (direzione!=null)
				risultato.append(" " + direzione);
		if(!this.attrezzi.isEmpty())
			risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo!=null)
				risultato.append(attrezzo.toString()+" ");
		}
		if(pg!=null)
			risultato.append("\nPersonaggio:"+this.pg.getNome()+"("+this.pg.getClass().getSimpleName()+")");
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo!=null)
				if (attrezzo.getNome().equals(nomeAttrezzo))
					trovato = true;
		}
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for(Attrezzo a: attrezzi) {
			if(a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return attrezzi.remove(attrezzo);
	}

	public List<Direction> getDirezioni() {
		List<Direction> direzioni = new LinkedList<>(this.stanzeAdiacenti.keySet());
		return direzioni;
	}
	@Override
	public int compareTo(Stanza stanza) {
		return this.getNome().compareTo(stanza.getNome());
	}
	public Map<Direction, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}
	public AbstractPersonaggio getPersonaggio() {
		return this.pg;
	}
	public void setPersonaggio(AbstractPersonaggio pg) {
		this.pg = pg;
	}

}