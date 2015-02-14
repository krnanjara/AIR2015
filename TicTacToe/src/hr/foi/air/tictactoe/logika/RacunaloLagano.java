package hr.foi.air.tictactoe.logika;

import java.util.Random;

public class RacunaloLagano implements Racunalo {
	
	private Potez potez;

	@Override
	public int odigrajPotez(Igra igra) {
		Random generator = new Random();
		while(true) {
			int brojGumba = generator.nextInt(9) + 1;
			if(igra.dohvatiVrijednost(brojGumba) == Potez.neodigran) {
				return brojGumba;
			}
		}
	}

	@Override
	public void postaviPotez(Potez potez) {
		this.potez = potez;
	}

	@Override
	public Potez dohvatiPotez() {
		return this.potez;
	}

}
