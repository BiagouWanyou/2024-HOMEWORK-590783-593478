package it.uniroma3.diadia;


import java.io.FileNotFoundException;

import it.uniroma3.diadia.IOConsole.IO;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	private IO IO;
	private giocatore Giocatore;
	private Labirinto Roma3;
	private Stanza stanzaCorrente;
	private boolean finita;
	
	public Partita(IO io,String testo) {
		this.IO = io;
		Giocatore = new giocatore();
		try {
			Roma3 = new Labirinto(testo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Roma3.creaStanze();
		stanzaCorrente = Roma3.getStanzaIniziale();
		this.finita= false;
		
	}
	public Partita(IO io) {
		this.IO = io;
		Giocatore = new giocatore();
		Roma3= Labirinto.newBuilder();
		Roma3.creaStanze();
		stanzaCorrente = Roma3.getStanzaIniziale();
		this.finita= false;
		
	}
	public Partita(Labirinto labirinto, IO IO){
		this.IO = IO;
		Giocatore = new giocatore();
		Roma3 = labirinto;
		stanzaCorrente = Roma3.getStanzaIniziale();
		this.finita = false;
	}
	public Partita(Labirinto lab) {
		Giocatore = new giocatore();
		this.finita = false;
		this.Roma3= lab;
	}



	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == Roma3.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.Giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	public giocatore getGiocatore() {
		return Giocatore;
	}
	public Labirinto getLabirinto() {
		return this.Roma3;
	}
	public boolean giocatoreIsVivo(){
		return this.getGiocatore().getCfu()!=0;
	}
	public IO getIO() {
		return this.IO;
	}	
	
}
