package hr.foi.air.tictactoe;

import hr.foi.air.tictactoe.logika.Potez;
import hr.foi.air.tictactoe.logika.Racunalo;
import hr.foi.air.tictactoe.logika.RacunaloLagano;

import org.junit.Assert;
import org.junit.Test;

public class Testiranje {

	@Test
	public void testirajPoteze() {
		Racunalo lagano = new RacunaloLagano();
		lagano.postaviPotez(Potez.igrac1);
		Assert.assertNotEquals(lagano.dohvatiPotez(), Potez.igrac2);
	}

}
