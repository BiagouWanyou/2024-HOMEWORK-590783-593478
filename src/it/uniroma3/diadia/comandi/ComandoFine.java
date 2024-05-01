package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	private String nome;
	private String parametro;
	@Override
	public void esegui(Partita partita){
		partita.getIO().mostraMessaggio("Grazie di aver giocato");
		partita.setFinita();
	}
	@Override
	public void setParametro(String parametro) {};

	
	@Override
	public String getNome() {
		return this.nome;
	}
	@Override
	public String getParametro() {
		return this.parametro;
	}
	public ComandoFine() {
		this.nome= "fine";
	}
}
