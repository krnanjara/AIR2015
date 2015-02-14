package hr.foi.air.tictactoe.logika;

public class RacunaloTesko implements Racunalo {
	
	private Potez potez; // igrac1 ili igrac2 (X ili O)

	@Override
	public int odigrajPotez(Igra igra) {
		
		
		for(int i = 1; i < 10; i++) {
			try {
				switch(this.potez){
				case igrac1:
					if(igra.simulirajPostaviVrijednost(i, this.potez) == StanjeIgre.pobjedaIgrac1) return i;
					break;
				case igrac2:
					if(igra.simulirajPostaviVrijednost(i, this.potez) == StanjeIgre.pobjedaIgrac2) return i;
					break;
				default:
					break;
				}	
			}
			catch(IndexOutOfBoundsException e) { } // ignoriramo zauzeta mjesta
		}
		
		if(igra.dohvatiVrijednost(5) == Potez.neodigran) return 5;
		
		if(igra.dohvatiVrijednost(1) == Potez.neodigran) return 1;
		if(igra.dohvatiVrijednost(3) == Potez.neodigran) return 3;
		if(igra.dohvatiVrijednost(9) == Potez.neodigran) return 9;
		if(igra.dohvatiVrijednost(7) == Potez.neodigran) return 7;
		
		if(igra.dohvatiVrijednost(2) == Potez.neodigran) return 2;
		if(igra.dohvatiVrijednost(6) == Potez.neodigran) return 6;
		if(igra.dohvatiVrijednost(8) == Potez.neodigran) return 8;
		if(igra.dohvatiVrijednost(4) == Potez.neodigran) return 4;
		
		return 0;
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
