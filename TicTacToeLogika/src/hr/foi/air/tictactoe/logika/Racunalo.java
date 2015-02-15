package hr.foi.air.tictactoe.logika;

/**
 * Sucelje koje mora zadovoljiti svaki racunalno upravljani igrac
 */
public interface Racunalo {
	/**
	 * Metoda vraca poziciju na koju racunalo zeli odigrati potez
	 * @return
	 */
	public int odigrajPotez(Igra igra);
	
	/**
	 * Postavlja potez kojim igra racunalo
	 * @param potez koje predstavlja racunalnog igraca
	 */
	public void postaviPotez(Potez potez);
	
	/**
	 * Dohvaca potez kojim igra racunalo
	 * @return potez koje predstavlja racunalnog igraca
	 */
	public Potez dohvatiPotez();
}
