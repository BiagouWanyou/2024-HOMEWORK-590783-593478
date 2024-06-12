package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{
	private String parametro;
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Comando non valido");
	}

	public String getParametro() {
		return this.parametro;
	}
	public ComandoNonValido() {
		super.setNome("non valido");
	}

}
