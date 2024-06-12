package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando{
	@Override
	public void esegui (Partita partita) {
		System.out.println(partita.getGiocatore().getBorsa().DEFAULT_PESO_MAX_BORSA);
		Stanza stanzaCorrente = partita.getStanzaCorrente ();
		Stanza prossimaStanza = null;
		if (parametro==null) {
			partita.getIO().mostraMessaggio("Dove vuoi andare? "
					+ "Devi specificare una direzione");
			return;
		}
		try{prossimaStanza = stanzaCorrente.getStanzaAdiacente (this.parametro);
		if(prossimaStanza==stanzaCorrente)
			partita.getIO().mostraMessaggio("La direzzione è bloccata");
		if (prossimaStanza==null) {
			partita.getIO().mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente (prossimaStanza);
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente () .getNome () ) ;
		partita.getGiocatore () .setCfu (partita.getGiocatore () .getCfu () -1) ;
		}catch(IllegalArgumentException e){
			partita.getIO().mostraMessaggio("devi specificare una direzione valida");
		}
		return;
		}
	public void setParametro(String parametro) {
		this.parametro= parametro;
	}
	
	public ComandoVai() {
		super.setNome("vai");
	}
}
