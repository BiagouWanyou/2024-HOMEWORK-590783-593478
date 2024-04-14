package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {
	public IOConsole IO;
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi", "posa"};

	private Partita partita;

	public DiaDia(IOConsole IO) {
		this.partita = new Partita();
		this.IO= IO;
	}

	public void gioca() {
		String istruzione; 
	
		this.IO.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		do		
	
			istruzione = IO.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if(comandoDaEseguire.getNome()!=null) {
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(); 
				return true;
			} else if (comandoDaEseguire.getNome().equals("vai"))
				this.vai(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("aiuto"))
				this.aiuto();
			else if(comandoDaEseguire.getNome().equals("prendi"))
				this.prendiAttrezzo(comandoDaEseguire.getParametro());
			else if(comandoDaEseguire.getNome().equals("posa"))
				this.posaAttrezzo(comandoDaEseguire.getParametro());
			else 
				IO.mostraMessaggio("Comando sconosciuto");
			if (this.partita.vinta()) {
				IO.mostraMessaggio("Hai vinto!");
				return true;
			} else
				return false;
		}else return false;

	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		String Elenco= new String();
		for(int i=0; i< elencoComandi.length; i++) 
			Elenco = Elenco +" " +elencoComandi[i];
		IO.mostraMessaggio(Elenco);
		
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null){
			IO.mostraMessaggio("Dove vuoi andare ?");
			Scanner nuovadirezione= new Scanner(IO.leggiRiga());
			if(nuovadirezione.hasNext())
				direzione= nuovadirezione.next();
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			IO.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
		}
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	/**
	 * Comando "Prendi Attrezzo".
	 */
	private void prendiAttrezzo(String nomeattrezzo) {
		if(!this.partita.getStanzaCorrente().hasAttrezzo(nomeattrezzo))
			return;
		Attrezzo a=this.partita.getStanzaCorrente().getAttrezzo(nomeattrezzo);
		this.partita.getGiocatore().getBorsa().addAttrezzo(a);
		this.partita.getStanzaCorrente().removeAttrezzo(a);
		IO.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio("Hai preso un/a :"+this.partita.getGiocatore().getBorsa().getAttrezzo(nomeattrezzo));
		IO.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	/**
	 * Comando "Prendi Attrezzo".
	 */
	private void posaAttrezzo(String nomeattrezzo) {
		if(!this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeattrezzo))
			return;
		Attrezzo a=this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeattrezzo);
		this.partita.getStanzaCorrente().addAttrezzo(a);
		IO.mostraMessaggio("Hai posato un/a :"+this.partita.getStanzaCorrente().getAttrezzo(nomeattrezzo));
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole IO = new IOConsole();
		DiaDia gioco = new DiaDia(IO);
		IO.mostraMessaggio("hey");
		gioco.gioca();
	}
}