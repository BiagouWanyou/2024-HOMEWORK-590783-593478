package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{
	
	@Override
	public AbstractComando costruisciComando(String istruzione) {
		 Scanner scannerDiParole  = new Scanner(istruzione);
		 String nomeComando = null;
		 AbstractComando comando = null;
		 String parametro = null;
		 if(scannerDiParole.hasNext())
			nomeComando=scannerDiParole.next();
		 if(scannerDiParole.hasNext())
			parametro =scannerDiParole.next();
		 
		 try {
			 StringBuilder  location= new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			 location.append(Character.toUpperCase(nomeComando.charAt(0)));
			 location.append(nomeComando.substring(1));
			 comando = (AbstractComando)Class.forName(location.toString()).newInstance();
		} catch (Exception e) {
			comando = new ComandoNonValido();
		}
		 comando.setParametro(parametro);
		 return comando;
	}
}
