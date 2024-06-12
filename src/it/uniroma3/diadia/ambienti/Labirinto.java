package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import it.uniroma3.diadia.ambienti.Stanza.Direction;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public Labirinto(String nomeFile) throws FileNotFoundException, Exception {
		CaricatoreLabirinto c =
				new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
		}
	private Labirinto() {}
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	public void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo passepartout = new Attrezzo("passepartout",1);
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new StanzaBloccata("Aula N11","est","passepartout");
		Stanza aulaN10 = new StanzaMagica("Aula N10");
		Stanza laboratorio = new StanzaBuia("Laboratorio Campus","lanterna");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		atrio.addAttrezzo(passepartout);

		// il gioco comincia nell'atrio
		stanzaIniziale = atrio;  
		stanzaVincente = biblioteca;
	}
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	public void setStanzaIniziale(Stanza iniziale) {
		this.stanzaIniziale= iniziale;
	}
	public void setStanzaVincente(Stanza vincente) {
		this.stanzaVincente= vincente;
	}
	public static class LabirintoBuilder extends Labirinto{
		private List<Stanza> stanze;
		
		public LabirintoBuilder() {
			super();
			stanze = new LinkedList<>();
		}
		public LabirintoBuilder addStanzaIniziale(String iniziale) {
			Stanza in = new Stanza(iniziale);
			super.setStanzaIniziale(in);
			stanze.add(in);
			return this;
		}
		public LabirintoBuilder addStanzaVincente(String vincente) {
			Stanza vin = new Stanza(vincente);
			super.setStanzaVincente(vin);
			stanze.add(vin);
			return this;
		}
		public LabirintoBuilder getLabirinto() {
			return this;
		}
		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			if(!stanze.isEmpty()&&!stanze.get(stanze.size()-1).hasAttrezzo(nome))
				stanze.get(stanze.size()-1).addAttrezzo(new Attrezzo(nome,peso));
			return this;
		}
		public LabirintoBuilder addAttrezzo(String nome, int peso,String stanza) {
			Stanza s = this.getStanza(stanza);
			if(!stanze.isEmpty()&&!stanze.get(stanze.size()-1).hasAttrezzo(nome)&&s!=null)
				s.addAttrezzo(new Attrezzo(nome,peso));
			return this;
		}
		public Stanza getStanza(String s) {
			for(Stanza r: stanze) {
				if(r.getNome().equals(s))
					return r;
			}
			return null;
		}
		public LabirintoBuilder addAdiacenza(String n1, String n2,String direzione) {
			
			Stanza s1= getStanza(n1);
			Stanza s2= getStanza(n2);
			try {
			String opposta = Direction.valueOf(direzione.toUpperCase()).opposta().toString();
			s1.impostaStanzaAdiacente(direzione, s2);
			s2.impostaStanzaAdiacente(opposta, s1);}
			catch(IllegalArgumentException e) {
				System.out.println("Direzione errata");
			}

			
			return this;
		}
		public LabirintoBuilder addStanza(String s) {
			if(this.getStanza(s)==null)
				stanze.add(new Stanza(s));
			return this;
		}
		public LabirintoBuilder addStanza(Stanza s) {
			stanze.add(s);
			return this;
		}
		public LabirintoBuilder addStanzaBloccata(String nome, String direzione ,String nomeAttrezzo) {
			Stanza Bloccata = new StanzaBloccata(nome,direzione, nomeAttrezzo);
			stanze.add(Bloccata);
			return this;
		}
		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			Stanza magica = new StanzaMagica(nome, soglia);
			stanze.add(magica);
			return this;
		}
		public LabirintoBuilder addStanzaBuia(String nome, String nomeAttrezzo) {
			Stanza buia = new StanzaBuia(nome, nomeAttrezzo);
			stanze.add(buia);
			return this;
		}
		public List<Stanza> getListaStanze(){
			return stanze;
		}
		public  LabirintoBuilder addPersonaggio(AbstractPersonaggio pg) {
			this.stanze.get(stanze.size()-1).setPersonaggio(pg);
			return this;
		}
		public  LabirintoBuilder addPersonaggio(AbstractPersonaggio pg, Stanza stanza) {
			this.stanze.get(stanze.indexOf(stanza)).setPersonaggio(pg);
			return this;
		}
		@Override
		public void creaStanze() {

			/* crea gli attrezzi */
			
			/* crea stanze del labirinto */
			/*Stanza atrio = new Stanza("Atrio")*/
			Attrezzo spada = new Attrezzo("spada",3);
			Attrezzo scudo = new Attrezzo("scudo",4);
			this.addStanzaIniziale("Atrio")
			.addPersonaggio(new Mago("Abracaspadus","Sono il mago più potente del regno",spada))
			.addAttrezzo("osso", 1)
			.addAttrezzo("passepartout", 1)
			.addStanzaBloccata("Aula N11","est","passepartout")
			.addPersonaggio(new Cane("Cerberigno","GRRRRRRRR",scudo,"osso"))
			.addStanzaMagica("Aula N10", 3)
			.addPersonaggio(new Strega("Grincina","La Strega più prepotente del regno"))
			.addAttrezzo("lanterna", 3)
			.addStanzaBuia("Laboratorio Campus","lanterna")
			.addStanzaVincente("Biblioteca")
			.addAdiacenza("Atrio", "Biblioteca", "nord")
			.addAdiacenza("Atrio", "Aula N11", "est")
			.addAdiacenza("Atrio", "Aula N10", "sud")
			.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
			.addAdiacenza("Aula N11","Atrio","ovest")
			.addAdiacenza("Aula N11", "Laboratorio Campus", "est")
			.addAdiacenza("Aula N10", "Atrio", "nord")
			.addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
			.addAdiacenza("Laboratorio Campus", "Atrio", "est")
			.addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")
			.addAdiacenza("Biblioteca", "Atrio", "sud");
			

			
			
			
		}
	}

}
