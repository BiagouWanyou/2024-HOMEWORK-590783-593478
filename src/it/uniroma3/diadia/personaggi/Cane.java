package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private static final String MESSAGGIO_FAIL= "GRRRRRRR!!!";
	private static final String MESSAGGIO_DONO = "Bau Bau!!";
	private Attrezzo attrezzo ;
	private String preferito;
	public Cane(String nome, String presentaz, Attrezzo attrezzo,String preferito) {
		super(nome, presentaz);
		this.attrezzo = attrezzo;
		this.preferito= preferito;
	}
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_FAIL;

	}
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		if(attrezzo.getNome().equals(attrezzo)) {
			return this.agisci(partita);}
		
		partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
		return this.MESSAGGIO_DONO;

	}
			
			
		
}
