package it.uniroma3.diadia.giocatore;

public class giocatore {
	private int cfu;
	static final private int CFU_INIZIALI = 20;
	private Borsa Borsa;
	
	public giocatore() {
		cfu=CFU_INIZIALI;
		this.Borsa =new Borsa();
	}
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	public Borsa getBorsa() {
		return this.Borsa;
	}

}
