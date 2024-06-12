package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando{
	private String nome;
	protected String parametro;
	abstract public void esegui(Partita partita);
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome= nome;
	}
	public void setParametro(String paremtro) {}
	public String getParametro() {return this.parametro;}
	
}
