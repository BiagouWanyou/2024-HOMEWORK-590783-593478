package it.uniroma3.diadia.ambienti;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{
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
		s1.impostaStanzaAdiacente(direzione, s2);

		
		return this;
	}
	public LabirintoBuilder addStanza(String s) {
		if(this.getStanza(s)==null)
			stanze.add(new Stanza(s));
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
	@Override
	public void creaStanze() {

		/* crea gli attrezzi */
		
		/* crea stanze del labirinto */
		/*Stanza atrio = new Stanza("Atrio")*/this.addStanzaIniziale("Atrio")
		.addAttrezzo("osso", 1)
		.addAttrezzo("passepartout", 1)
		.addStanzaBloccata("Aula N11","est","passepartout")
		.addStanzaMagica("Aula N10", 3)
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
