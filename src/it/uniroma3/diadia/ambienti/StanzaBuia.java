package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	private String nomeAttrezzo;
	
	public StanzaBuia(String nome,String nomeAttrezzoNuovo) {
		super(nome);
		this.nomeAttrezzo= nomeAttrezzoNuovo;
	}
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(nomeAttrezzo))
			return super.getDescrizione();
		else
			return "Qui c'è un buio pesto";
	}
	

}
