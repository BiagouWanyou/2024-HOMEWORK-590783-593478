package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;

public class ComandoAiuto extends AbstractComando {
	static final private String[] elencoComandi= {"vai", "aiuto", "fine","prendi", "posa"};
	String Elenco= new String();
	IOConsole IO= new IOConsole();
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			Elenco = Elenco +" " +elencoComandi[i];
		partita.getIO().mostraMessaggio(Elenco);}
	
	public ComandoAiuto(){
		this.setNome("aiuto");
	}
}
