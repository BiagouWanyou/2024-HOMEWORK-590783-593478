package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	private String nome;
	private String parametro;
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Comando non valido");
	}
	public void setParametro(String parametro) {}
	@Override
	public String getNome() {
		return this.nome;
	}
	@Override
	public String getParametro() {
		return this.parametro;
	}
	public ComandoNonValido() {
		this.nome= "non valido";
	}

}
