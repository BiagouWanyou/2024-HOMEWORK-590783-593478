package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	public void esegui(Partita partita) {
		if(!partita.getStanzaCorrente().hasAttrezzo(this.getParametro()))
			return;
		Attrezzo a=partita.getStanzaCorrente().getAttrezzo(parametro);
		partita.getGiocatore().getBorsa().addAttrezzo(a);
		partita.getStanzaCorrente().removeAttrezzo(a);
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getIO().mostraMessaggio("Hai preso un/a :"+partita.getGiocatore().getBorsa().getAttrezzo(this.parametro));
		partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	@Override
	public void setParametro(String parametro) {
		this.parametro= parametro;
	}
	
	public ComandoPrendi() {
		this.setNome("prendi");
	}
}
