package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	private String parametro;
	private String nome;
	public void esegui(Partita partita) {
		if(!partita.getStanzaCorrente().hasAttrezzo(this.parametro))
			return;
		Attrezzo a=partita.getStanzaCorrente().getAttrezzo(parametro);
		partita.getGiocatore().getBorsa().addAttrezzo(a);
		partita.getStanzaCorrente().removeAttrezzo(a);
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getIO().mostraMessaggio("Hai preso un/a :"+partita.getGiocatore().getBorsa().getAttrezzo(this.parametro));
		partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	@Override
	public String getNome() {
		return this.nome;
	}
	@Override
	public String getParametro() {
		return this.parametro;
	}
	public ComandoPrendi() {
		this.nome= "prendi";
	}
}
