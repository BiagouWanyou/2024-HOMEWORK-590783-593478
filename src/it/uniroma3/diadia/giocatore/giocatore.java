package it.uniroma3.diadia.giocatore;

import java.io.FileReader;
import java.util.Properties;

public class giocatore {
	private int cfu;
	static final private int CFU_INIZIALI = 1;
	private Borsa borsa;
	
	public giocatore() {
		try {
			Properties p= new Properties();
			p.load(new FileReader("resources/diadia.properties"));
			String cfuString = p.getProperty("cfu_iniziali");
			this.cfu= Integer.parseInt(cfuString);
			System.out.println("CFU  inizlai:"+this.cfu);
		}
		catch(Exception e) {
			System.out.println("errore");
			this.cfu= CFU_INIZIALI;
			}
		borsa= new Borsa();
	}
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	public Borsa getBorsa() {
		return this.borsa;
	}

}
