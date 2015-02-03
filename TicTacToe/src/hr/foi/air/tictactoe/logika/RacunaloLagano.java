package hr.foi.air.tictactoe.logika;

import java.util.Random;

/**
 * Predstavlja lakseg racunalnog protivnika
 * @author Arwen
 *
 */
public class RacunaloLagano extends AbstractRacunalo {

	@Override
	int biraj(char[][] stanjeIgre) {
		Random generator = new Random();
		while (true) {
			int brojGumba = generator.nextInt(9) + 1;
			if (daLiJePrazno(stanjeIgre, brojGumba) == true) {
				return brojGumba;
			}
		}
	}

}
