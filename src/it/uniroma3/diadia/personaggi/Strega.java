package it.uniroma3.diadia.personaggi;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparaNumAttrezzi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_INT = "Ahahahaha";
	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}
	@Override
	public String agisci(Partita partita) {
		Collection<Stanza> adia=  partita.getStanzaCorrente().getMapStanzeAdiacenti().values();
		if(super.haSalutato())
			partita.setStanzaCorrente(Collections.max(adia, new ComparaNumAttrezzi()));
		else
			partita.setStanzaCorrente(Collections.min(adia, new ComparaNumAttrezzi()));
		return MESSAGGIO_INT;
	}
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		return MESSAGGIO_INT;
	}
	

}
