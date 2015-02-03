package hr.foi.air.tictactoe.logika;

/**
 * Predstavlja tezeg racunalnog protivnika
 *
 */
public class RacunaloTesko extends AbstractRacunalo {

	@Override
	int biraj(char[][] stanjeIgre) {

		int brojGumba;
		
		brojGumba = pronadjiPobjedu(stanjeIgre, 'o');
		if(brojGumba != 0)
			return brojGumba;
				
		brojGumba = pronadjiPobjedu(stanjeIgre, 'x');
		if(brojGumba != 0)
			return brojGumba;
		
		if (daLiJePrazno(stanjeIgre, 5)) return 5;

		if (daLiJePrazno(stanjeIgre, 1)) return 1;
		if (daLiJePrazno(stanjeIgre, 3)) return 3;
		if (daLiJePrazno(stanjeIgre, 9)) return 9;
		if (daLiJePrazno(stanjeIgre, 7)) return 7;

		if (daLiJePrazno(stanjeIgre, 2)) return 2;
		if (daLiJePrazno(stanjeIgre, 6)) return 6;
		if (daLiJePrazno(stanjeIgre, 8)) return 8;
		if (daLiJePrazno(stanjeIgre, 4)) return 4;

		return 0;
	}
	
	/**
	 * Metoda trazi poziciju na koju da odigra pobjednicki potez
	 * @param stanjeIgre trentuno stanje igre
	 
	 * @return
	 */
	int pronadjiPobjedu(char[][] stanjeIgre, char znakZaProvjeru) {
		int brojGumba = 0;		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				brojGumba++;
				if (stanjeIgre[i][j] == 'N') {
					stanjeIgre[i][j] = znakZaProvjeru;
					if (status(stanjeIgre, znakZaProvjeru) == true){
						stanjeIgre[i][j] = 'N';
						return brojGumba;
					}
					stanjeIgre[i][j] = 'N';
				}
			}
		}
		return 0;
	}

	/**
	 * Provjerava je li doslo do pobjede
	 
	 */
	boolean status(char[][] stanjeIgre, char znakZaProvjeru) {
		boolean pobjeda = false;

		// provjera za redove
		if (stanjeIgre[0][0] == znakZaProvjeru && stanjeIgre[0][1] == znakZaProvjeru && stanjeIgre[0][2] == znakZaProvjeru)
			pobjeda = true;
		else if (stanjeIgre[1][0] == znakZaProvjeru && stanjeIgre[1][1] == znakZaProvjeru && stanjeIgre[1][2] == znakZaProvjeru)
			pobjeda = true;
		else if (stanjeIgre[2][0] == znakZaProvjeru && stanjeIgre[2][1] == znakZaProvjeru && stanjeIgre[2][2] == znakZaProvjeru)
			pobjeda = true;
		// provjera za stupce
		else if (stanjeIgre[0][0] == znakZaProvjeru && stanjeIgre[1][0] == znakZaProvjeru && stanjeIgre[2][0] == znakZaProvjeru)
			pobjeda = true;
		else if (stanjeIgre[0][1] == znakZaProvjeru && stanjeIgre[1][1] == znakZaProvjeru && stanjeIgre[2][1] == znakZaProvjeru)
			pobjeda = true;
		else if (stanjeIgre[0][2] == znakZaProvjeru && stanjeIgre[1][2] == znakZaProvjeru && stanjeIgre[2][2] == znakZaProvjeru)
			pobjeda = true;
		// provjera za dijagonalu
		else if (stanjeIgre[0][0] == znakZaProvjeru && stanjeIgre[1][1] == znakZaProvjeru && stanjeIgre[2][2] == znakZaProvjeru)
			pobjeda = true;
		else if (stanjeIgre[0][2] == znakZaProvjeru && stanjeIgre[1][1] == znakZaProvjeru && stanjeIgre[2][0] == znakZaProvjeru)
			pobjeda = true;
		else
			pobjeda = false;

		return pobjeda;
	}

}
