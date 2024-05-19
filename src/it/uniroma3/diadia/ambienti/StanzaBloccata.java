package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private String AttrezzoUnlock;
	private String DirezioneLocked;
	
	public StanzaBloccata(String nome,String Direzione,String Attrezzo) {
		super(nome);
		this.DirezioneLocked=Direzione;
		this.AttrezzoUnlock= Attrezzo;
	}
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(DirezioneLocked)&&!this.hasAttrezzo(AttrezzoUnlock)) {
			System.out.println("hey");
			return this;

		}
		else 
			return super.getStanzaAdiacente(direzione);
	}
	@Override 
		public String getDescrizione() {
		if(!this.hasAttrezzo(AttrezzoUnlock))
			return super.getDescrizione() + "\nLa direzione "+DirezioneLocked+" è bloccata, sembra che "
				+ "un passepartout possa renderti libero di proseguire";
		else
			return super.getDescrizione();
		}


}
