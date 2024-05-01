package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	private String nome;
	private String parametro;
	@Override
	public void esegui(Partita partita){
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	@Override
	public void setParametro(String partametro){}
	
	@Override
	public String getNome() {
		return this.nome;
	}
	@Override
	public String getParametro() {
		return this.parametro;
	}
	public ComandoGuarda() {
		this.nome= "guarda";
	}
}
