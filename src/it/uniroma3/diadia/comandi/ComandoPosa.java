package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	private String parametro;
	private String nome;
	public void esegui(Partita partita) {
		if(!partita.getGiocatore().getBorsa().hasAttrezzo(this.parametro))
			return;
		Attrezzo a=partita.getGiocatore().getBorsa().removeAttrezzo(this.parametro);
		partita.getStanzaCorrente().addAttrezzo(a);
		partita.getIO().mostraMessaggio("Hai posato un/a :"+partita.getStanzaCorrente().getAttrezzo(this.parametro));
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
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
	public ComandoPosa() {
		this.nome= "posa";
	}

}
