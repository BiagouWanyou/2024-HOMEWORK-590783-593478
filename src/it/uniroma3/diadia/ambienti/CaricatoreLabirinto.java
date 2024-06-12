
package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza.Direction;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";
	private static final String STANZE_BUIE_MARKER = "Stanze Buie: ";
	private static final String STANZE_BLOCCATE_MARKER = "Stanze Bloccate: ";
	private static final String STANZE_MAGICHE_MARKER = "Stanze Magiche: ";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";
	/*prefisso delle righe conteneti i personaggi distinti per tipo*/
	private static final String MAGHI_MARKER = "Maghi: ";
	private static final String CANI_MARKER = "Cani: ";
	private static final String STREGHE_MARKER = "Streghe: ";
	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;
	private LabirintoBuilder labirinto;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		labirinto = new Labirinto.LabirintoBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaMaghi();
			this.leggiECollocaCani();
			this.leggiECollocaStreghe();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
		
			labirinto.addStanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, labirinto.getStanza(nomeStanza));
		}
	}
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		int soglia;
		for(String listmagiche : separaStringheAlleVirgole(nomiStanze)) {
			String nomeStanza = null; 
			String sogliaMagica = null; 
			try (Scanner scannerLinea = new Scanner(listmagiche)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la soglia magica di: "+nomeStanza+"."));
				sogliaMagica = scannerLinea.next();
			}
			soglia =  Integer.parseInt(sogliaMagica);
			labirinto.addStanzaMagica(nomeStanza, soglia);
			this.nome2stanza.put(nomeStanza, labirinto.getStanza(nomeStanza));
		}
	}
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);

		for(String listmagiche : separaStringheAlleVirgole(nomiStanze)) {
			String nomeStanza = null; 
			String nomeAttrezzo = null; 
			try (Scanner scannerLinea = new Scanner(listmagiche)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l' attrezzo di stanza buia: "+nomeStanza+"."));
				nomeAttrezzo = scannerLinea.next();
			}

			labirinto.addStanzaBuia(nomeStanza, nomeAttrezzo);
			this.nome2stanza.put(nomeStanza, labirinto.getStanza(nomeStanza));
		}
	}
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);

		for(String listmagiche : separaStringheAlleVirgole(nomiStanze)) {
			String nomeStanza = null; 
			String direzione = null;
			String nomeAttrezzo = null; 
			try (Scanner scannerLinea = new Scanner(listmagiche)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza magica."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione di una stanza."));
				direzione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l' attrezzo di stanza bloccata: "+nomeStanza+"."));
				nomeAttrezzo = scannerLinea.next();
			}

			labirinto.addStanzaBloccata(nomeStanza,direzione, nomeAttrezzo);
			this.nome2stanza.put(nomeStanza, labirinto.getStanza(nomeStanza));
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = labirinto.getStanza(nomeStanzaIniziale);
		this.stanzaVincente = labirinto.getStanza(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();

			}	

			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}
	private void leggiECollocaMaghi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);

		for(String specificaMago : separaStringheAlleVirgole(specificheAttrezzi)) {
			Mago mago;
			String nomeMago= null;
			String pres = null;
			String nomeAttrezzo= null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaMago)) {

				scannerLinea.useDelimiter("; ");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del mago "+nomeMago+"."));
				pres = scannerLinea.next();
				
				scannerLinea.skip(";");
				scannerLinea.useDelimiter(" ");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo del mago"+nomeMago+"."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell' attrezzo del mago "+nomeMago+"."));
				pesoAttrezzo = scannerLinea.next();

				
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il mago "+nomeMago+"."));
				nomeStanza = scannerLinea.next();

			}	
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, Integer.parseInt(pesoAttrezzo));
			mago= new Mago(nomeMago,pres,attrezzo);
			labirinto.addPersonaggio(mago, labirinto.getStanza(nomeStanza));
		}
	}
	private void leggiECollocaCani() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(CANI_MARKER);

		for(String specificaMago : separaStringheAlleVirgole(specificheAttrezzi)) {
			Cane cane;
			String nomeCane= null;
			String pres = null;
			String nomeAttrezzo= null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			String preferito = null;
			try (Scanner scannerLinea = new Scanner(specificaMago)) {

				scannerLinea.useDelimiter("; ");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del cane "+nomeCane+"."));
				pres = scannerLinea.next();
				
			
				scannerLinea.skip(";");
				scannerLinea.useDelimiter(" ");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo del cane"+nomeCane+"."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell' attrezzo del cane "+nomeCane+"."));
				pesoAttrezzo = scannerLinea.next();

				
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il cibo preferito del cane "+nomeCane+"."));
				preferito = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il cane "+nomeCane+"."));
				nomeStanza = scannerLinea.next();
			}	
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, Integer.parseInt(pesoAttrezzo));
			cane= new Cane(nomeCane,pres,attrezzo,preferito);
			labirinto.addPersonaggio(cane, labirinto.getStanza(nomeStanza));}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);

			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			labirinto.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {

		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				StringBuilder stanzaDestinazioneBuild = new StringBuilder(scannerDiLinea.next());
				if(stanzaDestinazioneBuild.charAt(stanzaDestinazioneBuild.length()-1)==',')
					stanzaDestinazioneBuild.deleteCharAt(stanzaDestinazioneBuild.length()-1);
				String stanzaDestinazione= 	stanzaDestinazioneBuild.toString();

				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}
	private void leggiECollocaStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);

		for(String stregaSpec : separaStringheAlleVirgole(specificheStreghe)) {
			Strega strega;
			String nomeStrega= null;
			String pres = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(stregaSpec)) {

				scannerLinea.useDelimiter("; ");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del mago "+nomeStrega+"."));
				pres = scannerLinea.next();
			
				scannerLinea.skip(";");
				scannerLinea.useDelimiter(" ");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il mago "+nomeStrega+"."));
				nomeStanza = scannerLinea.next();

			}	
			
			strega= new Strega(nomeStrega,pres);
			labirinto.addPersonaggio(strega, labirinto.getStanza(nomeStanza));
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		labirinto.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
