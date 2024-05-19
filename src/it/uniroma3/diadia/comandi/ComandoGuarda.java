package it.uniroma3.diadia.comandi;

import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuarda implements Comando{
	private String nome;
	private String parametro;
	@Override
	public void esegui(Partita partita){
		if(parametro==null) {
			partita.getIO().mostraMessaggio("Dove vuoi guardare?"+"Devi specificare un parametro.");
			return;
		}
		if(parametro.equals("borsa")) {
			List<Attrezzo> lista =partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso();
			StringBuilder msg = new StringBuilder("[");
			for(Attrezzo a:lista) {
				msg.append(a.getNome()+",");
			}
			if(msg.charAt(msg.length()-1)==',')
				msg.deleteCharAt(msg.length()-1);
			msg.append("]");
			partita.getIO().mostraMessaggio(msg.toString());
			msg.setLength(0);
			msg.append("{");
			Set<Attrezzo> set=partita.getGiocatore().getBorsa().getContenutoOrdinatoPerNome();
			for(Attrezzo a: set) {
				msg.append(a.getNome()+",");
			}
			
			if(msg.charAt(msg.length()-1)==',')
				msg.deleteCharAt(msg.length()-1);
			msg.append("}");
			partita.getIO().mostraMessaggio(msg.toString());
			msg.setLength(0);
			Map<Integer,Set<Attrezzo>> mappa = partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerPeso();
			Set<Integer> setkey= mappa.keySet();
			for(int i : setkey) {
				msg.append("("+i+",{");
				for(Attrezzo a : mappa.get(i)) {
					msg.append(a.getNome()+",");
				}
				if(msg.charAt(msg.length()-1)==',')
					msg.deleteCharAt(msg.length()-1);
				msg.append("});");
			}
			if(msg.length()!=0&&msg.charAt(msg.length()-1)==';')
				msg.deleteCharAt(msg.length()-1);
			partita.getIO().mostraMessaggio(msg.toString());
		}
		if(this.parametro.equals("stanza"))
			partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	@Override
	public void setParametro(String parametro){
		this.parametro=parametro;
	}
	
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
