package hr.foi.air.tictactoe.logika;

/**
 * Sucelje koje mora zadovoljiti igra
 */
public interface Igra {
	/**
	 * Metoda pokusava odigrati (postaviti) potez na zadanu poziciju
	 * @param pozicija gdje potez treba odigrati
	 * @throws IndexOutOfBoundsException iznimka za slucaj da je na toj poziciji potez vec odigran ili
	 * da je pozicija nedozvoljena zbog velicine polja
	 */
	public void postaviVrijednost(int pozicija, Potez potez) throws IndexOutOfBoundsException;
	
	/**
	 * Metoda simulira postavljanje poteza na zadanu poziciju i vraca simulirano stanje igre
	 * nakon tog poteza, bez da taj potez doista postavi na zadanu poziciju.
	 * Ova je metoda potrebna za nasu strategiju naprednog racunalnog igraca
	 * @param pozicija
	 * @param potez
	 * @return simulirano stanje igre
	 * @throws IndexOutOfBoundsException iznimka za slucaj da je na toj poziciji potez vec odigran ili
	 * da je pozicija nedozvoljena zbog velicine polja
	 */
	public StanjeIgre simulirajPostaviVrijednost(int pozicija, Potez potez) throws IndexOutOfBoundsException;
	
	/**
	 * Dohvaca potez koji je odigran na zadanoj poziciji
	 * @param pozicija za koju se dohvaca potez
	 * @throws IndexOutOfBoundsException iznimka ukoliko je pozicija nedozvoljena zbog velicine polja
	 * @return potez na navedenoj poziciji
	 */
	public Potez dohvatiVrijednost(int pozicija) throws IndexOutOfBoundsException;
	
	/**
	 * Dohvaca trenutno stanje igre (provjerava uvjete pobjede) i pomice stanje igre
	 * @return trenutno stanje igre
	 */
	public StanjeIgre dohvatiStanjeIgre();
}
