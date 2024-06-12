package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoRegala extends AbstractComando {
	public void setParametro(String parametro) {
		this.parametro= parametro;
	}
	public void esegui(Partita partita) {
		String messaggio = partita.getStanzaCorrente().getPersonaggio().riceviRegalo(partita.getGiocatore().getBorsa().getAttrezzo(parametro), partita);
		partita.getIO().mostraMessaggio(messaggio);
	}
}
